package com.simile.plan.janusgraph.gremlin.service.impl;

import com.simile.plan.janusgraph.api.model.DefinitionResultSet;
import com.simile.plan.janusgraph.api.model.DocumentDefinition;
import com.simile.plan.janusgraph.api.model.GraphDefinition;
import com.simile.plan.janusgraph.api.service.DefinitionService;

/**
 * @author yitao
 * @since 2020-07-24
 */
public class GremlinDefinitionServiceImpl implements DefinitionService {

    @Override
    public DefinitionResultSet createGraph(GraphDefinition definition) {
        return null;
    }

    @Override
    public DefinitionResultSet dropGraph(GraphDefinition definition) {
        return null;
    }

    @Override
    public DefinitionResultSet createVertex(DocumentDefinition definition) {
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
