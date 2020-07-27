package com.simile.plan.janusgraph.gremlin.service.impl;

import com.simile.plan.janusgraph.api.model.Query;
import com.simile.plan.janusgraph.api.model.QueryResultSet;
import com.simile.plan.janusgraph.api.service.QueryService;

/**
 * @author yitao
 * @since 2020-07-24
 */
public class GremlinQueryServiceImpl<T> implements QueryService<T> {

    @Override
    public QueryResultSet<T> query(Query query) {
        return null;
    }
}
