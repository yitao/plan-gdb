package com.simile.plan.janusgraph.api.model;

/**
 * 查询定义
 * @author yitao
 * @since 2020-07-24
 */
public interface Query {

    Query setCondition(Condition condition);

    Condition getCondition();


    Query setRange(Range range);

    Range getRange(Range range);
}
