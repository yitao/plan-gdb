package com.simile.plan.janusgraph.api.model;

import java.util.Map;

/**
 * 查询结果集
 * @author yitao
 * @since 2020-07-24
 */
public interface QueryResultSet<T> extends ResultSet{

    Map<String,Object> asMap();

}
