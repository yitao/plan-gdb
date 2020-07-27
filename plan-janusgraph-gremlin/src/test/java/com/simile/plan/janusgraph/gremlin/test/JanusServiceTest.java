package com.simile.plan.janusgraph.gremlin.test;

import com.alibaba.fastjson.JSON;
import com.simile.plan.janusgraph.gremlin.model.CommonDataSourceConfiguration;
import com.simile.plan.janusgraph.gremlin.JanusRepository;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * created by yitao on 2020/07/22
 */
public class JanusServiceTest {
    public static final String LABEL_V = "label_v";

    public static final String LABEL_E = "label_e";

    @Test
    public void testDatabases() throws ExecutionException, InterruptedException {
        String[] hosts = {"192.168.1.244","192.168.1.245","192.168.1.246"};
        CommonDataSourceConfiguration dataSourceConfig = new CommonDataSourceConfiguration(hosts,8182,"haizhi","123456");
        JanusRepository janusRepository = new JanusRepository(dataSourceConfig);
        List<String> databases = janusRepository.graphs();
        System.out.println(databases);
        //[limintest_6, limintest_4, teststr12, teststr13, limintest_5, limintest_3, test_non, poc_fry_14, poc_fry_13, cdh514_280, poc_fry_12, poc_fry_11, poc_fry_10, zmb007, opc_aace642f0cfc45db9775c5d692f18ac6, janus_big_data, zmb008, etldataloader52, zmb009, etldataloader50, zmb003, zmb004, opc_a893689c0aa6488ca669c4d6b0de67ee, etldataloader53, zmb005, zmb006, opc_184992dd7b5c48a49ca725416b64f1ec, zmb001, etldataloader57, zmb002, janus_app_3, opc_dc64e976d32a486ea6b88e3a0a2bfb77, janus_app_2, janus_app_1, janus_app_7, zmb00_new_index2, janus_app_5, janus_app_4, poc_test_3, opc_7371d08bcf5d4734af96a9662475b969, poc_fry_20, etldataloader40, opc_4a5e7afd75784973a7fa56129f9d27a5, zmb014, zmb00_new_index4, poc_302, zmb00_new_index3, zmb015, poc_303, etldataloader42, poc_300, poc_301, etldataloader43, zmb010, zmb011, zmb012, zmb013, test_gql, etldataloader47, test_xml, Janusgraph_bigdata, opc_c040626e58a84c4198d806b5ed670e53, min_janusgraph, opc_157c3708901a4699af9a911c1be6b786, opc_025922aa1014437d854e6a133f74d4fc, hzgraph4, etldataloader73, opc_6f2b2c0dfcac4994a7fabee03ef6fba6, poc_110, cs03, poc_117, poc_118, teststr03, teststr04, teststr01, teststr02, teststr07, teststr08, teststr05, janusimport2, Janusgraph_147, teststr06, fry001, test001, opc_9dadd5cea1c34c5285c83f2ae1422bc4, fry002, teststr09, fry003, custom_mapping_haizhi_1, opc_4bfe39e430a44e1e8e4042d9cf18b555, fry004, opc_49e409ea757146698b5392ef92d0ed39, opc_d28864fcb1804e5a8ad55a02bbb9fe73, poc_fry, big_janusgraph, etldataloader62, etldataloader63, test_es_flag, poc_401, poc_400, poc_405, opc_0a420116fc7745809e29a22429dab3bc, arango, teststr10, opc_c7fd0e4ff79a41d890015eb7d5fa516d, teststr11, opc_b237b8f40c1b47caa1fe711c411d7c51, poc_403, janusimport, poc_404, gongkai_xinjian, test_hive_etl, janua_app_6, opc_006, opc_005, opc_008, opc_007, custom_mapping, opc_e604a11cfabe472ea20ee949bf68877a, janus_poc_1, zmb_test_index_1, etl_janus, janus_bulk_loading, lwtest001, etldataloader83, etldataloader1, test_datetime_janus, etldataloader2, graphbase_001, cdh514_janus, deploy_3, deploy_2, etldataloader30, etldataloader1000001, deploy_1, etldataloader1000002, Janusgraph, etldataloader33, haizhi-graph-3, etldataloader1000000, haizhi-graph-2, haizhi-graph-1, etldataloader32, opc_fa12ebe2d7024fa1883c00455dec8bbc, etldataloader37, zmb024, haizhi_mapping1, opc_4ba8d30d46db414580b755d652416727, poc_1001, janusgraphtest, haizhi_mapping9, release_cdh514_280, haizhi_mapping8, opc_63577dece8d64d5186de5f36e6e3fc57, haizhi_mapping7, haizhi_mapping5, dev_001, summary_test, poc_200, etldataloader22, poc_203, etldataloader23, opc_5bb6adb3e4aa4027a4950174c2cc734f, poc_201, etldataloader21, dev_002, poc_202, poc_207, etldataloader27, etldataloader24, poc_205, zmb034, zmb_new_index, poc_206]

        List<String> vertexes = janusRepository.vertexes("janus_big_data");
        System.out.println(vertexes);
        //limintest_6   [Company, Person]
        //poc_fry_14    []
        //janusgraphtest    [company, Company]
        //janus_big_data    [Company, Person]
    }

    @Test
    public void testDatabases2() throws ExecutionException, InterruptedException {
        String[] hosts = {"192.168.1.183","192.168.1.184","192.168.1.185"};
        CommonDataSourceConfiguration dataSourceConfig = new CommonDataSourceConfiguration(hosts,22381,"gremlinserver","gremlinclient");
        JanusRepository janusRepository = new JanusRepository(dataSourceConfig);
        List<String> databases = janusRepository.graphs();
        System.out.println(databases);
    }

    @Test
    public void testProp() throws ExecutionException, InterruptedException {
        String[] hosts = {"192.168.1.244", "192.168.1.245", "192.168.1.246"};
        CommonDataSourceConfiguration dataSourceConfig = new CommonDataSourceConfiguration(hosts, 8182, "haizhi", "123456");
        JanusRepository janusRepository = new JanusRepository(dataSourceConfig);
        String graph = "janus_big_data";
        String schema = "Company";
        StringBuilder gql = new StringBuilder();
        gql.append(graph).append(" = ConfiguredGraphFactory.open('").append(graph).append("');");
        gql.append("mgt = ").append(graph).append(".openManagement();");
        gql.append("mgt.printPropertyKeys();");
        List<Result> results = janusRepository.submit(gql.toString());
        System.out.println(results.size());
    }

    @Test
    public void testFindPage() throws ExecutionException, InterruptedException {
        String[] hosts = {"192.168.1.244","192.168.1.245","192.168.1.246"};
        CommonDataSourceConfiguration dataSourceConfig = new CommonDataSourceConfiguration(hosts,8182,"haizhi","123456");
        JanusRepository janusRepository = new JanusRepository(dataSourceConfig);
        String graph = "janus_big_data";
        String schema = "Company";
        int pageNo = 1;
        int pageSize = 10;
        boolean isV = true;
        StringBuilder gql = new StringBuilder();
        gql.append(graph).append(" = ConfiguredGraphFactory.open('").append(graph).append("');");
//        gql.append("g = ").append(graph).append(";");
        gql.append("g = ").append(graph).append(".traversal();");
        if (isV) {
            gql.append("g.V()");
        } else {
            gql.append("g.E()");
        }
        gql.append(".hasLabel('").append(schema).append("')");
//        gql.append(".skip(" + ((pageNo - 1) * pageSize) + ")");
//        gql.append(".limit(" + pageSize + ")");
//        gql.append(".where(eq('DCC6677F34011BAED0BB6FBEDE62BDA9')).by('object_key')"); no
//        gql.append(".properties('object_key').where(eq('DCC6677F34011BAED0BB6FBEDE62BDA9'))");no
//        gql.append(".has('object_key',eq('DCC6677F34011BAED0BB6FBEDE62BDA9'))");  //ok
        gql.append(".or(has('address',TextP.containing('山西省')),has('address',TextP.containing('广州市')))");//ok
//        gql.append(".or(has('address',not(TextP.containing('山西省'))))");
//        gql.append(".or(has('address',textContainsRegex('.*县.*')))");
//        gql.append(".has('address',textContainsRegex('.*县.*'))");
//        gql.append(".has('address',org.janusgraph.core.attribute.Text.textFuzzy('县'))"); //ok
        gql.append(".range(" + ((pageNo - 1) * pageSize) + ","+ (pageNo * pageSize) + ")");
        gql.append(";");
        List<Result> results = janusRepository.submit(gql.toString());
        System.out.println(results.size());
        for (Result result : results) {
            Long id = (Long) result.getVertex().id();
            Map<String,Object> map = result.getVertex().keys().stream().map(key->{
                VertexProperty p = result.getVertex().property(key);
                Map.Entry<String,Object> entry = new AbstractMap.SimpleEntry<>(p.label(),p.value());
                return entry;
            }).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
            System.out.println(JSON.toJSONString(map));
        }
        //address 安吉县溪龙乡黄杜村安吉中盛农业发展有限公司内1019室
        //object_key DCC6677F34011BAED0BB6FBEDE62BDA9
    }

    @Test
    public void testUpdate() throws ExecutionException, InterruptedException {
        //{"enterprise_type":"有限合伙企业","capital":"40010000.00","registered_address":"安吉县市场监督管理局","is_listed":"false","address":"安吉县溪龙乡黄杜村安吉中盛农业发展有限公司内1019室","business_status":"存续","object_key":"DCC6677F34011BAED0BB6FBEDE62BDA9","utime":"2019-06-2101:11:15","city":"浙江湖州","industry":"商务服务业","operation_startdate":"2015-11-16","label_v":"Company","province":"浙江","contact":"075582552495","name":"安吉钜金投资管理合伙企业（有限合伙）","ctime":"2019-06-2101:11:15","registered_capital_unit":"元"}
        String[] hosts = {"192.168.1.244","192.168.1.245","192.168.1.246"};
        CommonDataSourceConfiguration dataSourceConfig = new CommonDataSourceConfiguration(hosts,8182,"haizhi","123456");
        JanusRepository janusRepository = new JanusRepository(dataSourceConfig);
        String graph = "janus_big_data";
        String schema = "Company";
        int pageNo = 1;
        int pageSize = 10;
        boolean isV = true;
        Map<String,Object> record = JSON.parseObject("{\"enterprise_type\":\"YT有限合伙企业\",\"capital\":\"40010000.00\",\"registered_address\":\"YT安吉县市场监督管理局\",\"is_listed\":\"false\",\"address\":\"安吉县溪龙乡黄杜村安吉中盛农业发展有限公司内1019室\",\"business_status\":\"存续\",\"object_key\":\"DCC6677F34011BAED0BB6FBEDE62BDA9\",\"utime\":\"2019-06-2101:11:15\",\"city\":\"浙江湖州\",\"industry\":\"商务服务业\",\"operation_startdate\":\"2015-11-16\",\"label_v\":\"Company\",\"province\":\"浙江\",\"contact\":\"075582552495\",\"name\":\"安吉钜金投资管理合伙企业（有限合伙）\",\"ctime\":\"2019-06-2101:11:15\",\"registered_capital_unit\":\"元\"}").getInnerMap();
        StringBuilder gql = new StringBuilder();
        gql.append(graph).append(" = ConfiguredGraphFactory.open('").append(graph).append("');");
//        gql.append("g = ").append(graph).append(";");
        gql.append("g = ").append(graph).append(".traversal();");
        Long id = 4096L;
        String label = schema;
        gql.append(buildVertexScript(record,id,label));
        List<Result> results = janusRepository.submit(gql.toString());
        System.out.println(results.size());
    }


    /**
     * 构建实体导入脚本
     *
     * @param row
     * @param id
     * @param label
     * @return
     */
    private String buildVertexScript(Map<String, Object> row, Long id, String label) {
        StringBuffer singleBuild = new StringBuffer();
        // insertVertex
        if (Objects.isNull(id)) {
            singleBuild.append("g.addV(\"" + label + "\")");
            for (Map.Entry entry : row.entrySet()) {
                singleBuild.append(".property(\"" + entry.getKey() + "\",\"" + entry.getValue() + "\")");
            }
            // updateVertex
        } else {
            singleBuild.append("g.V(" + id + ")");
            for (Map.Entry entry : row.entrySet()) {
                singleBuild.append(".property(\"" + entry.getKey() + "\",\"" + entry.getValue() + "\")");
            }
        }
        singleBuild.append(".property('" + LABEL_V + "','" + label + "')");
        singleBuild.append(".next();");
        return singleBuild.toString();
    }

}
