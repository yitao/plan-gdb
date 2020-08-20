package com.simile.plan.janusgraph.gremlin.model;

import com.simile.plan.janusgraph.api.model.GraphMeta;

/**
 * @author yitao
 * @since 2020-07-30
 */
public class CommonGraphMeta
        implements GraphMeta {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
