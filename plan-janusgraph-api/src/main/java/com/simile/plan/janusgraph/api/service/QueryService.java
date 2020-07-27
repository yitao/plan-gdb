package com.simile.plan.janusgraph.api.service;

import com.simile.plan.janusgraph.api.model.Query;
import com.simile.plan.janusgraph.api.model.QueryResultSet;

/**
 * DQL 服务定义
 * @author yitao
 * @since 2020-07-24
 */
public interface QueryService<T> {

    QueryResultSet<T> query(Query query);

}
