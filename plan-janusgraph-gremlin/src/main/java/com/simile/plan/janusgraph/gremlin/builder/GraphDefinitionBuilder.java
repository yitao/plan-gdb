package com.simile.plan.janusgraph.gremlin.builder;


import com.simile.plan.janusgraph.core.model.CommonGraphDefinition;

/**
 * @author yitao
 * @since 2020-07-24
 */
public class GraphDefinitionBuilder {

    public static CommonGraphDefinition build(String graph){
        return new CommonGraphDefinition(graph);
    }
}
