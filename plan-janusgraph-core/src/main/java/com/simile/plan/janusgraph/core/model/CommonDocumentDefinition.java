package com.simile.plan.janusgraph.core.model;

import com.simile.plan.janusgraph.api.model.DocumentDefinition;

/**
 * @author yitao
 * @since 2020-07-27
 */
public class CommonDocumentDefinition
        implements DocumentDefinition {
    protected String graph;
    protected String schema;
    protected boolean vertex;
    protected boolean override;

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public boolean isVertex() {
        return vertex;
    }

    public void setVertex(boolean vertex) {
        this.vertex = vertex;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
}
