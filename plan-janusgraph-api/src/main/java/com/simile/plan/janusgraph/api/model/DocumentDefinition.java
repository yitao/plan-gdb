package com.simile.plan.janusgraph.api.model;

/**
 * @author yitao
 * @since 2020-07-24
 */
public interface DocumentDefinition {

    String getGraph();

    String getSchema();

    boolean isVertex();

    boolean isOverride();

}
