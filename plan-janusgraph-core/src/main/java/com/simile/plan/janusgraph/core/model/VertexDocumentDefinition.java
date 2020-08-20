package com.simile.plan.janusgraph.core.model;

import com.simile.plan.janusgraph.api.model.DocumentDefinition;

/**
 * @author yitao
 * @since 2020-07-27
 */
public class VertexDocumentDefinition
        extends CommonDocumentDefinition
        implements DocumentDefinition {

    @Override
    public boolean isVertex() {
        this.vertex = true;
        return true;
    }

    @Override
    public void setVertex(boolean vertex) {
    }
}
