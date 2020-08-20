package com.simile.plan.janusgraph.api.service;

import com.simile.plan.janusgraph.api.model.DefinitionResultSet;
import com.simile.plan.janusgraph.api.model.DocumentDefinition;
import com.simile.plan.janusgraph.api.model.GraphDefinition;

import java.util.List;

/**
 * DDL 服务定义
 * create/delete graph,create/delete vertex、edge,
 * @author yitao
 * @since 2020-07-24
 */
public interface DefinitionService {

    List<String> graphs();

    boolean existsGraph(String graph);

    DefinitionResultSet createGraph(GraphDefinition definition);

    DefinitionResultSet dropGraph(GraphDefinition definition);


    List<String> vertexes(String graph);

    List<String> edges(String graph);

    boolean existsSchema(String graph, String schema, boolean isVertex);

    DefinitionResultSet createVertex(DocumentDefinition definition);

    DefinitionResultSet dropVertex(DocumentDefinition definition);


    DefinitionResultSet createEdge(DocumentDefinition definition);

    DefinitionResultSet dropEdge(DocumentDefinition definition);

}
