package com.simile.plan.janusgraph.gremlin;

import com.simile.plan.janusgraph.api.exception.JanusGraphException;
import com.simile.plan.janusgraph.gremlin.model.CommonDataSourceConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tinkerpop.gremlin.driver.*;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;
import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * created by yitao on 2020/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JanusGraphClient {
    public static final String LINE_SEPARATOR = "\n";
    public static final String CELL_SEPARATOR = "|";
    public static final String FIELD_SEPARATOR = ":";
    public static final String VERTEX_INDEX_NAME = "Vertex Index Name";
    public static final String EDGE_INDEX_NAME = "Edge Index (VCI) Name";
    public static final String RELATION_INDEX = "Relation Index";
    public static final int INDEX_LINE_LENGTH = 5;
    public static final String VERTEX_INDEX_SIGN = "vertex";
    public static final String EDGE_INDEX_SIGN = "edge";
    public static final String LABEL_V = "label_v";
    public static final String LABEL_E = "label_e";
    public static final String COMMA_SYMBOL = ",";
    public static final String FROM_KEY = "from_key";
    public static final String TO_KEY = "to_key";
    public static final String OBJECT_KEY = "object_key";
    public static final String ID_KEY = "id";
    // 通过MD5->LONG自动转换的
    public static final String UNDERLINE_KEY = "_key";
    public static final String UNDERLINE_FROM = "_from";
    public static final String UNDERLINE_TO = "_to";
    public static final String UNDERLINE_ID = "_id";

    private static String indexEsName = "search";

    /**
     * 创建的index模板
     * 1、图名称
     * 2、表名称
     * 3、字段名称
     * 4、实体/表类型：vertex/edge
     * 5、索引类型，组合索引：COMPOSITE_INDEX，混合索引：MIXED_INDEX
     */
    private static final String INDEX_TEMPLATE = "%s_%s_%s";


    private Cluster cluster;

    private Client client;

    private CommonDataSourceConfiguration janusDataSourceConfig;

    private JanusGremlinBuilder gb;

    public JanusGraphClient(CommonDataSourceConfiguration janusDataSourceConfig) {
        this.janusDataSourceConfig = janusDataSourceConfig;
        gb = JanusGremlinBuilder.getInstance();
        this.buildCluster();
        this.buildClient();
    }

    private void buildCluster() {
        GryoMapper.Builder builder = GryoMapper.build().addRegistry(JanusGraphIoRegistry.getInstance());
        MessageSerializer serializer = new GryoMessageSerializerV3d0(builder);
        this.cluster = Cluster.build().addContactPoints(janusDataSourceConfig.getHosts())
                .port(janusDataSourceConfig.getPort())
                .maxConnectionPoolSize(janusDataSourceConfig.getMaxConnectionPoolSize())
                .maxContentLength(janusDataSourceConfig.getMaxContentLength())
                .maxWaitForConnection(janusDataSourceConfig.getMaxWaitForConnection())
                .workerPoolSize(janusDataSourceConfig.getWorkerPoolSize())
                .serializer(serializer)
                .credentials(janusDataSourceConfig.getUser(), janusDataSourceConfig.getPassword()).create();
    }

    private String genSessionId() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    private void buildSessionClient(String sessionId) {
        this.client = cluster.connect(sessionId);
    }

    private void buildClient() {
        this.client = cluster.connect();
    }

    public List<Result> submit(String gql) {
        try {
            return client.submit(gql).all().get();
        } catch (Exception e) {
            throw new JanusGraphException(e);
        }
    }

    public List<Result> submit(JanusGremlinBuilder builder) throws ExecutionException, InterruptedException {
        return client.submit(builder.toString()).all().get();
    }

    public ResultSet submit2(JanusGremlinBuilder builder) throws ExecutionException, InterruptedException {
        return client.submit(builder.toString());
    }

    public List<String> result2String(List<Result> results) {
        return results.stream().map(Result::getString).collect(Collectors.toList());
    }

    public boolean testConnect() throws ExecutionException, InterruptedException {
        submit(gb.clear().getGraphNames());
        return true;
    }

    public List<String> graphs() throws ExecutionException, InterruptedException {
        return result2String(
                submit(gb.clear().getGraphNames())
        );
    }

    public boolean existsGraph(final String graph) throws ExecutionException, InterruptedException {
        return graphs().stream()
                .anyMatch(item -> graph.equals(item));
    }

    public List<String> vertexes(String graph) throws ExecutionException, InterruptedException {
        return result2String(
                submit(gb.clear().openManagement(graph).showVertexLabels())
        );
    }

    public List<String> edges(final String graph) throws ExecutionException, InterruptedException {
        return result2String(
                submit(gb.clear().openManagement(graph).showEdgeLabels())
        );
    }


    public boolean existsSchema(String graph, String schema, SchemaType schemaType) throws ExecutionException, InterruptedException {
        return submit(gb.clear().containsSchemaLabel(graph, schema, schemaType).toString()).get(0).getBoolean();
    }

    public boolean createGraph(String graph) throws ExecutionException, InterruptedException {
        List<Result> results = submit(gb.clear().startWithTemplateConfiguration()
                .put2Map("index.search.index-name", graph.toLowerCase())
                .put2Map("graph.graphname", graph)
                .configMap().createGraph(graph)
        );
        return true;
    }

    public boolean dropGraph(String graph) throws ExecutionException, InterruptedException {
        ResultSet resultSet = submit2(gb.clear().dropGraph(graph));
        return true;
    }

    public boolean createSchema(GdbSuo suo)
            throws ExecutionException, InterruptedException {
        String graph = suo.getGraph();
        String schema = suo.getSchema();
        SchemaType schemaType = suo.getType();
        if (!existsSchema(graph, schema, schemaType)) {
            submit(gb.clear().makeSchemaLabel(graph, schema, schemaType));
        }
        // create property
        List<Result> results = submit(gb.clear().printPropertyKeys(graph));
        Set<String> existFields = results.stream().map(item -> parseFields(item.toString()))
                .flatMap(Set::stream).collect(Collectors.toSet());
        // create index
        List<Result> indexResult = submit(gb.clear().printIndexes(graph));
        JanusIndexVo janusIndexVo = getExistIndexes(indexResult);
        List<JanusIndex> indexFields = getIndexFields(suo, janusIndexVo);
        String propertyAndIndexGql = buildCreatePropertyAndIndexGql(suo, existFields, indexFields);
        client.submit(propertyAndIndexGql).all().get();
        return true;
    }

    public static String buildCreatePropertyAndIndexGql(GdbSuo suo, Set<String> existFields, List<JanusIndex> indexFields) {
        StringBuilder gql = new StringBuilder();
        gql.append("mgt = ").append(suo.getGraph()).append(".openManagement();");
        Map<String, FieldType> fields = suo.getFields();
        if (Objects.nonNull(fields)) {
            for (Map.Entry<String, FieldType> entry : fields.entrySet()) {
                String field = entry.getKey();
                FieldType fieldType = entry.getValue();
                if (existFields.contains(field)) {
                    gql.append(field).append(" = mgt.getPropertyKey('").append(field).append("');");
                } else {
                    gql.append(field).append(" = mgt.makePropertyKey('")
                            .append(field).append("').dataType(Class.forName('")
                            .append(getClassName(fieldType))
                            .append("')).cardinality(org.janusgraph.core.Cardinality.SINGLE).make();");
                }
            }
            if (suo.getType().isVertex()) {
                gql.append(suo.getSchema()).append(" = mgt.getVertexLabel(\"").append(suo.getSchema()).append("\");");
            } else {
                gql.append(suo.getSchema()).append(" = mgt.getEdgeLabel(\"").append(suo.getSchema()).append("\");");
            }
            gql.append("mgt.addProperties(").append(suo.getSchema()).append(',').append(StringUtils.join(fields.keySet(), ",")).append(");");
        }
        boolean vertex = suo.getType().isVertex();
        if (CollectionUtils.isNotEmpty(indexFields)) {
            gql.append(suo.getGraph()).append(".tx().rollback();");
            for (JanusIndex indexField : indexFields) {
                gql.append(indexField.getKey()).append(" = mgt.getPropertyKey('").append(indexField.getKey()).append("');");
                if (indexField.getType() == JanusIndexType.COMPOSITE_INDEX) {
                    if (OBJECT_KEY.equals(indexField.getKey())) {
                        gql.append("mgt.buildIndex('")
                                .append(getIndexName(indexField.getKey(), SchemaTypeEnum.VERTEX, JanusIndexType.COMPOSITE_INDEX))
                                .append("',").append("Vertex").append(".class).addKey(")
                                .append(indexField.getKey()).append(")").append(".buildCompositeIndex();");
                        gql.append("mgt.buildIndex('")
                                .append(getIndexName(indexField.getKey(), SchemaTypeEnum.EDGE, JanusIndexType.COMPOSITE_INDEX))
                                .append("',").append("Edge").append(".class).addKey(")
                                .append(indexField.getKey()).append(")").append(".buildCompositeIndex();");
                    } else {
                        // 解决不同类型表主字段索引失效问题
                        gql.append("mgt.buildIndex('")
                                .append(getIndexName(indexField.getKey(), SchemaTypeEnum.VERTEX, JanusIndexType.COMPOSITE_INDEX))
                                .append("',").append("Vertex").append(".class).addKey(")
                                .append(indexField.getKey()).append(")").append(".buildCompositeIndex();");
                        gql.append("mgt.buildIndex('")
                                .append(getIndexName(indexField.getKey(), SchemaTypeEnum.EDGE, JanusIndexType.COMPOSITE_INDEX))
                                .append("',").append("Edge").append(".class).addKey(")
                                .append(indexField.getKey()).append(")").append(".buildCompositeIndex();");
                    }
                }
                if (indexField.getType() == JanusIndexType.MIXED_INDEX) {
                    // 解决不同类型表主字段索引失效问题
                    gql.append("mgt.buildIndex('")
                            .append(getIndexName(indexField.getKey(), SchemaTypeEnum.VERTEX, JanusIndexType.MIXED_INDEX))
                            .append("',").append("Vertex").append(".class).addKey(")
                            .append(indexField.getKey()).append(getIndexMapping(indexField.getFieldType()))
                            .append(").buildMixedIndex('").append(indexEsName).append("');");
                    gql.append("mgt.buildIndex('")
                            .append(getIndexName(indexField.getKey(), SchemaTypeEnum.EDGE, JanusIndexType.MIXED_INDEX))
                            .append("',").append("Edge").append(".class).addKey(")
                            .append(indexField.getKey()).append(getIndexMapping(indexField.getFieldType()))
                            .append(").buildMixedIndex('").append(indexEsName).append("');");
                }
            }
        }
        gql.append("mgt.commit();");
        return gql.toString();
    }

    /**
     * 获取字段索引名称
     *
     * @param field
     * @param schemaType
     * @param indexType
     * @return
     */
    public static String getIndexName(String field, SchemaType schemaType, JanusIndexType indexType) {
        return String.format(INDEX_TEMPLATE, field,
                schemaType.isVertex() ? VERTEX_INDEX_SIGN : EDGE_INDEX_SIGN,
                StringUtils.lowerCase(indexType.name())
        );
    }

    /**
     * 根据不同类型的字段类型在es中创建不同的类型的索引mapping
     *
     * @param fieldType
     * @return
     */
    public static String getIndexMapping(FieldType fieldType) {
        if (fieldType.isDate()) {
            return COMMA_SYMBOL + "org.janusgraph.core.schema.Mapping.STRING.asParameter()";
        } else if (fieldType.isText()) {
            return COMMA_SYMBOL + "org.janusgraph.core.schema.Mapping.TEXTSTRING.asParameter()";
        } else {
            return COMMA_SYMBOL + "org.janusgraph.core.schema.Mapping.DEFAULT.asParameter()";
        }
    }

    public static String getClassName(FieldType fieldType) {
        if (fieldType == FieldType.STRING) {
            return String.class.getName();
        } else if (fieldType == FieldType.LONG) {
            return Long.class.getName();
        } else if (fieldType == FieldType.DOUBLE) {
            return Double.class.getName();
        } else if (fieldType == FieldType.DATETIME) {
            return String.class.getName();
        } else if (fieldType == FieldType.UNKNOWN) {
            // 处理index label,先占用
            return String.class.getName();
        }
        return null;
    }

    private JanusIndexVo getExistIndexes(List<Result> indexResult) {
        return formatIndexes(String.valueOf(indexResult.get(0)));
    }


    public static List<JanusIndex> getIndexFields(GdbSuo suo, JanusIndexVo janusIndexVo) {
        Map<String, SchemaField> schemaFields = suo.getSchemaFields();
        List<JanusIndex> needIndexes = new ArrayList<>(10);
        for (Map.Entry<String, SchemaField> entry : schemaFields.entrySet()) {
            String field = entry.getKey();
            SchemaField schemaField = entry.getValue();
            if (LABEL_V.equals(schemaField.getField()) || LABEL_E.equals(schemaField.getField())) {
                if (needIndex(suo.getType(), schemaField, janusIndexVo, JanusIndexType.MIXED_INDEX)) {
                    needIndexes.add(new JanusIndex(field, JanusIndexType.MIXED_INDEX, schemaField.getType()));
                }
            }
            if (schemaField.isMain()) {
                if (needIndex(suo.getType(), schemaField, janusIndexVo, JanusIndexType.COMPOSITE_INDEX)) {
                    needIndexes.add(new JanusIndex(field, JanusIndexType.COMPOSITE_INDEX, schemaField.getType()));
                }
                if (needIndex(suo.getType(), schemaField, janusIndexVo, JanusIndexType.MIXED_INDEX)) {
                    needIndexes.add(new JanusIndex(field, JanusIndexType.MIXED_INDEX, schemaField.getType()));
                }
            } else if (fixedNeedIndexField(suo.getType(), field)) {
                if (needIndex(suo.getType(), schemaField, janusIndexVo, JanusIndexType.COMPOSITE_INDEX)) {
                    needIndexes.add(new JanusIndex(field, JanusIndexType.COMPOSITE_INDEX, schemaField.getType()));
                }
            }
        }
        return needIndexes;
    }


    public static boolean fixedNeedIndexField(SchemaType type, String field) {
        if (type.isVertex()) {
            return StringUtils.equals(field, OBJECT_KEY) || StringUtils.equals(field, UNDERLINE_KEY);
        } else {
            return StringUtils.equals(field, OBJECT_KEY) ||
                    StringUtils.equals(field, UNDERLINE_KEY) ||
                    StringUtils.equals(field, FROM_KEY) ||
                    StringUtils.equals(field, UNDERLINE_FROM) ||
                    StringUtils.equals(field, TO_KEY) ||
                    StringUtils.equals(field, UNDERLINE_TO);
        }
    }

    public static boolean needIndex(SchemaType schemaType, SchemaField schemaField, JanusIndexVo janusIndexVo, JanusIndexType indexType) {
        List<JanusIndex> janusIndices;
        if (schemaType.isVertex()) {
            janusIndices = janusIndexVo.getVertexIndexes();
        } else {
            janusIndices = janusIndexVo.getEdgeIndexes();
        }
        for (JanusIndex janusIndex : janusIndices) {
            if (StringUtils.equals(janusIndex.getKey(), schemaField.getField())
                    && indexType == janusIndex.getType()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 这是获取property返回的数据
     * ---------------------------------------------------------------------------------------------------
     * Property Key Name              | Cardinality | Data Type                                          |
     * ---------------------------------------------------------------------------------------------------
     * name                           | SINGLE      | class java.lang.String                             |
     * long_field                     | SINGLE      | class java.lang.Long                               |
     * object_key                     | SINGLE      | class java.lang.String                             |
     * double_field                   | SINGLE      | class java.lang.Double                             |
     * date_field                     | SINGLE      | class java.lang.String                             |
     * ---------------------------------------------------------------------------------------------------
     *
     * @param result
     * @return
     */
    public static Set<String> parseFields(String result) {
        Set<String> existFields = new HashSet<>(16);
        String[] rows = StringUtils.split(result, LINE_SEPARATOR);
        for (int i = 0; i < rows.length; i++) {
            //表头三行和表尾一行不进行处理
            if (i < 3 || i >= (rows.length - 2)) {
                continue;
            }
            String row = rows[i];
            String[] cells = StringUtils.split(row, CELL_SEPARATOR);
            existFields.add(StringUtils.trim(cells[0]));
        }
        return existFields;
    }


    /**
     * 对以下字符串进行解析，将索引信息提取出来
     * ---------------------------------------------------------------------------------------------------
     * Vertex Index Name              | Type        | Unique    | Backing        | Key:           Status |
     * ---------------------------------------------------------------------------------------------------
     * key1_test_index                | Composite   | false     | internalindex  | key1:       INSTALLED |
     * key1_test_index1               | Composite   | false     | internalindex  | key1:       INSTALLED |
     * key2_vertex_index              | Composite   | true      | internalindex  | key2:       INSTALLED |
     * vertex_mixted_key3_zmb001      | Mixed       | false     | search         | key3:       INSTALLED |
     * ---------------------------------------------------------------------------------------------------
     * Edge Index (VCI) Name          | Type        | Unique    | Backing        | Key:           Status |
     * ---------------------------------------------------------------------------------------------------
     * key1_edge_test_index           | Composite   | false     | internalindex  | key1:       INSTALLED |
     * edge_mixed_key3_zmb001         | Mixed       | false     | search         | key3:       INSTALLED |
     * ---------------------------------------------------------------------------------------------------
     * Relation Index                 | Type        | Direction | Sort Key       | Order    |     Status |
     * ---------------------------------------------------------------------------------------------------
     *
     * @param result
     * @return
     */
    public static JanusIndexVo formatIndexes(String result) {
        JanusIndexVo janusIndexVo = new JanusIndexVo();
        if (StringUtils.isBlank(result)) {
            return janusIndexVo;
        }
        String[] rows = StringUtils.split(result, LINE_SEPARATOR);
        List<JanusIndex> vertexIndexes = new ArrayList<>(10);
        List<JanusIndex> edgeIndexes = new ArrayList<>(10);
        JanusIndexResultType type = null;
        for (String row : rows) {
            String[] cells = StringUtils.split(row, CELL_SEPARATOR);
            if (cells.length != INDEX_LINE_LENGTH) {
                continue;
            }
            if (StringUtils.equals(VERTEX_INDEX_NAME, StringUtils.trim(cells[0]))) {
                type = JanusIndexResultType.VERTEX_INDEX;
                continue;
            }
            if (StringUtils.equals(EDGE_INDEX_NAME, StringUtils.trim(cells[0]))) {
                type = JanusIndexResultType.EDGE_INDEX;
                continue;
            }
            if (StringUtils.equals(RELATION_INDEX, StringUtils.trim(cells[0]))) {
                type = JanusIndexResultType.RELATION_INDEX;
                continue;
            }
            if (type == JanusIndexResultType.VERTEX_INDEX) {
                vertexIndexes.add(getJanusIndex(cells));
            }
            if (type == JanusIndexResultType.EDGE_INDEX) {
                edgeIndexes.add(getJanusIndex(cells));
            }
            if (type == JanusIndexResultType.RELATION_INDEX) {

            }
        }
        janusIndexVo.setVertexIndexes(vertexIndexes);
        janusIndexVo.setEdgeIndexes(edgeIndexes);
        return janusIndexVo;
    }

    public static JanusIndex getJanusIndex(String[] cells) {
        JanusIndex janusIndex = new JanusIndex();
        janusIndex.setIndexName(StringUtils.trim(cells[0]));
        janusIndex.setType(JanusIndexType.getByValue(StringUtils.trim(cells[1])));
        janusIndex.setUnique(Boolean.valueOf(StringUtils.trim(cells[2])));
        janusIndex.setBacking(StringUtils.trim(cells[3]));
        String[] splits = cells[4].split(FIELD_SEPARATOR);
        janusIndex.setKey(StringUtils.trim(splits[0]));
        janusIndex.setStatus(StringUtils.trim(splits[1]));
        return janusIndex;
    }

}
