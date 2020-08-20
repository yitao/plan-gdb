package com.simile.plan.janusgraph.gremlin.service.impl;

import com.simile.plan.janusgraph.api.model.ManipulationResultSet;
import com.simile.plan.janusgraph.api.service.ManipulationService;
import com.simile.plan.janusgraph.gremlin.JanusGraphClient;

/**
 * @author yitao
 * @since 2020-07-24
 */
public class GremlinManipulationServiceImpl implements ManipulationService {

    private JanusGraphClient janusGraphClient;

    @Override
    public ManipulationResultSet insert() {
        return null;
    }

    @Override
    public ManipulationResultSet update() {
        return null;
    }

    @Override
    public ManipulationResultSet upsert() {
        return null;
    }

    @Override
    public ManipulationResultSet delete() {
        return null;
    }
}
