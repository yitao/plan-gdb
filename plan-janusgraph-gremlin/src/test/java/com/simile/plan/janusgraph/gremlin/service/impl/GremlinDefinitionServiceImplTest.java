package com.simile.plan.janusgraph.gremlin.service.impl;

import com.simile.plan.janusgraph.api.model.DocumentDefinition;
import com.simile.plan.janusgraph.api.model.GraphDefinition;
import com.simile.plan.janusgraph.api.service.DefinitionService;
import com.simile.plan.janusgraph.gremlin.JanusGraphClient;
import com.simile.plan.janusgraph.gremlin.builder.GraphDefinitionBuilder;
import com.simile.plan.janusgraph.gremlin.builder.JanusGraphClientBuilder;
import com.simile.plan.janusgraph.gremlin.model.CommonDataSourceConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yitao
 * @since 2020-07-30
 */
public class GremlinDefinitionServiceImplTest {

    private DefinitionService definitionService;

    private String graph = "yt_janus1";
    private String vertex = "person";
    private String edge = "money_flow";

    @Before
    public void initBean(){
        //研发环境
        String[] hosts = {"192.168.1.244","192.168.1.245","192.168.1.246"};
        CommonDataSourceConfiguration dataSourceConfig = new CommonDataSourceConfiguration(hosts,8182,"haizhi","123456");
        JanusGraphClient janusGraphClient = JanusGraphClientBuilder.buildJanusGraphClient(dataSourceConfig);
        definitionService = new GremlinDefinitionServiceImpl(janusGraphClient);
    }

    @Test
    public void existsGraph() {
        definitionService.existsGraph(graph);
    }

    @Test
    public void createGraph() {
        GraphDefinition def = GraphDefinitionBuilder.build(graph);
        definitionService.createGraph(def);
    }

    @Test
    public void dropGraph() {
        GraphDefinition def = GraphDefinitionBuilder.build(graph);
    }

    @Test
    public void vertexes() {
        definitionService.vertexes(graph);
    }

    @Test
    public void edges() {
        definitionService.edges(graph);
    }

    @Test
    public void existsSchema() {
        definitionService.edges(graph);
    }

    @Test
    public void createVertex() {
        DocumentDefinition def = null;
//        definitionService.createVertex();
    }

    @Test
    public void dropVertex() {
    }

    @Test
    public void createEdge() {
        definitionService.edges(graph);
    }

    @Test
    public void dropEdge() {
    }
}