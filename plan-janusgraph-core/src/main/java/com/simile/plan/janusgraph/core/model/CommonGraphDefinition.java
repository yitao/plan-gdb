package com.simile.plan.janusgraph.core.model;

import com.simile.plan.janusgraph.api.model.GraphDefinition;

/**
 * @author yitao
 * @since 2020-07-27
 */
public class CommonGraphDefinition
        implements GraphDefinition {

    private String graph;

    public CommonGraphDefinition(String graph) {
        this.graph = graph;
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }
}
