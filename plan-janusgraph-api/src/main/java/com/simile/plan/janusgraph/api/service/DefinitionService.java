package com.simile.plan.janusgraph.api.service;

import com.simile.plan.janusgraph.api.model.DefinitionResultSet;
import com.simile.plan.janusgraph.api.model.DocumentDefinition;
import com.simile.plan.janusgraph.api.model.GraphDefinition;

/**
 * DDL 服务定义
 * create/delete graph,create/delete vertex、edge,
 * @author yitao
 * @since 2020-07-24
 */
public interface DefinitionService {

    DefinitionResultSet createGraph(GraphDefinition definition);

    DefinitionResultSet dropGraph(GraphDefinition definition);


    DefinitionResultSet createVertex(DocumentDefinition definition);

    DefinitionResultSet dropVertex(DocumentDefinition definition);


    DefinitionResultSet createEdge(DocumentDefinition definition);

    DefinitionResultSet dropEdge(DocumentDefinition definition);

}
