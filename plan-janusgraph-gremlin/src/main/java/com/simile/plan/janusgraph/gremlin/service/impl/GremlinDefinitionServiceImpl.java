package com.simile.plan.janusgraph.gremlin.service.impl;

import com.simile.plan.janusgraph.api.exception.JanusGraphException;
import com.simile.plan.janusgraph.api.model.DefinitionResultSet;
import com.simile.plan.janusgraph.api.model.DocumentDefinition;
import com.simile.plan.janusgraph.api.model.GraphDefinition;
import com.simile.plan.janusgraph.api.service.DefinitionService;
import com.simile.plan.janusgraph.gremlin.*;
import com.simile.plan.janusgraph.gremlin.model.CommonDataSourceConfiguration;
import org.apache.tinkerpop.gremlin.driver.Result;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author yitao
 * @since 2020-07-24
 */
public class GremlinDefinitionServiceImpl implements DefinitionService {

    private JanusGraphClient janusGraphClient;

    public GremlinDefinitionServiceImpl(JanusGraphClient janusGraphClient) {
        this.janusGraphClient = janusGraphClient;
    }

    @Override
    public List<String> graphs() {
        try {
            return janusGraphClient.result2String(
                    janusGraphClient.submit(JanusGremlinBuilder.getInstance().getGraphNames())
            );
        } catch (Exception e) {
            throw new JanusGraphException(e);
        }
    }

    @Override
    public boolean existsGraph(final String graph) {
        try {
            return graphs().stream()
                    .anyMatch(item -> graph.equals(item));
        } catch (Exception e) {
            throw new JanusGraphException(e);
        }
    }


    @Override
    public DefinitionResultSet createGraph(GraphDefinition definition) {
        String graph = definition.getGraph();
        JanusGremlinBuilder builder = JanusGremlinBuilder.getInstance();
        String gql = builder.createGraph(graph).toString();
        try {
            List<Result> results = janusGraphClient.submit(gql);
            System.out.println(results);
        } catch (Exception e) {
            throw new JanusGraphException(e);
        }
        return null;
    }


    @Override
    public DefinitionResultSet dropGraph(GraphDefinition definition) {
        String graph = definition.getGraph();
        JanusGremlinBuilder builder = JanusGremlinBuilder.getInstance();
        String gql = builder.dropGraph(graph).toString();
        try {
            List<Result> results = janusGraphClient.submit(gql);
            System.out.println(results);
        } catch (Exception e) {
            throw new JanusGraphException(e);
        }
        return null;
    }

    @Override
    public List<String> vertexes(String graph) {
        JanusGremlinBuilder builder = JanusGremlinBuilder.getInstance();
        String gql = builder.openJanusGraph(graph, graph)
                .openManagement(JanusGremlinBuilder.DEFAULT_MGT_VAR)
                .showVertexLabels().toString();
        return janusGraphClient.result2String(
                janusGraphClient.submit(gql)
        );
    }

    @Override
    public List<String> edges(final String graph) {
        JanusGremlinBuilder builder = JanusGremlinBuilder.getInstance();
        String gql = builder.openJanusGraph(graph, graph)
                .openManagement(JanusGremlinBuilder.DEFAULT_MGT_VAR)
                .showEdgeLabels().toString();
        return janusGraphClient.result2String(
                janusGraphClient.submit(gql)
        );
    }

    @Override
    public boolean existsSchema(String graph, String schema, boolean isVertex) {
        JanusGremlinBuilder builder = JanusGremlinBuilder.getInstance();
        String gql = builder.openJanusGraph(graph, graph)
                .openManagement(JanusGremlinBuilder.DEFAULT_MGT_VAR)
                .containsSchemaLabel(schema, isVertex).toString();
        return janusGraphClient.submit(gql).get(0).getBoolean();
    }

    @Override
    public DefinitionResultSet createVertex(DocumentDefinition definition) {

        String graph = definition.getGraph();
        String schema = definition.getSchema();
        boolean isVertex = definition.isVertex();
        JanusGremlinBuilder builder = JanusGremlinBuilder.getInstance();

        if (!existsSchema(graph, schema, isVertex)) {
            String gql = builder.openJanusGraph(graph, graph)
                    .openManagement(JanusGremlinBuilder.DEFAULT_MGT_VAR)
                    .makeSchemaLabel(schema, isVertex)
                    .toString();
            janusGraphClient.submit(gql);
        }

        //创建schema标签
        //创建property,index
        String gql = builder.openJanusGraph(graph, graph)
                .openManagement(JanusGremlinBuilder.DEFAULT_MGT_VAR)
                .printPropertyKeys()
                .toString();
        List<Result> propertyResults = janusGraphClient.submit(gql);
        Set<String> existFields = propertyResults.stream().map(item -> JanusGraphClient.parseFields(item.toString()))
                .flatMap(Set::stream).collect(Collectors.toSet());

        gql = builder.openJanusGraph(graph, graph)
                .openManagement(JanusGremlinBuilder.DEFAULT_MGT_VAR)
                .printIndexes()
                .toString();

        List<Result> indexResult = janusGraphClient.submit(gql);
        String indexResultStr = String.valueOf(indexResult.get(0));
        JanusIndexVo janusIndexVo = JanusGraphClient.formatIndexes(indexResultStr);

//        List<JanusIndex> indexFields = JanusGraphClient.getIndexFields(suo, janusIndexVo);
//        String propertyAndIndexGql = buildCreatePropertyAndIndexGql(suo, existFields, indexFields);

        return null;
    }

    @Override
    public DefinitionResultSet dropVertex(DocumentDefinition definition) {
        return null;
    }

    @Override
    public DefinitionResultSet createEdge(DocumentDefinition definition) {
        return null;
    }

    @Override
    public DefinitionResultSet dropEdge(DocumentDefinition definition) {
        return null;
    }
}
