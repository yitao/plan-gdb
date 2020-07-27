package com.simile.plan.janusgraph.gremlin;

/**
 * created by yitao on 2020/05/22
 */
public class JanusGremlinBuilder {
    private StringBuffer stringBuffer;

    public JanusGremlinBuilder() {
        this.stringBuffer = new StringBuffer();
    }

    public static JanusGremlinBuilder getInstance() {
        return new JanusGremlinBuilder();
    }

    public JanusGremlinBuilder clear() {
        stringBuffer.setLength(0);
        return this;
    }

    public JanusGremlinBuilder getGraphNames() {
        stringBuffer.append("ConfiguredGraphFactory.getGraphNames();");
        return this;
    }

    public JanusGremlinBuilder openManagement(String graph) {
        stringBuffer.append("mgt = " + graph + ".openManagement();");
        return this;
    }

    public JanusGremlinBuilder showVertexLabels() {
        stringBuffer.append("mgt.getVertexLabels().collect{it.name()};");
        return this;
    }

    public JanusGremlinBuilder showEdgeLabels() {
        stringBuffer.append("mgt.getRelationTypes(EdgeLabel.class).collect{it.name()};");
        return this;
    }

    public JanusGremlinBuilder startWithTemplateConfiguration() {
        stringBuffer.append("map = ConfiguredGraphFactory.getTemplateConfiguration();");
        return this;
    }

    public JanusGremlinBuilder put2Map(String key, String value) {
        stringBuffer.append("map.put(\"" + key + "\",\"" + value + "\");");
        return this;
    }

    public JanusGremlinBuilder configMap() {
        stringBuffer.append("ConfiguredGraphFactory.createConfiguration(new MapConfiguration(map));");
        return this;
    }

    public JanusGremlinBuilder createGraph(String graph) {
        stringBuffer.append("ConfiguredGraphFactory.create(\"" + graph + "\");");
        return this;
    }

    public JanusGremlinBuilder openGraph(String graph) {
        stringBuffer.append(graph).append(" = ConfiguredGraphFactory.open('")
                .append(graph).append("');");
        return this;
    }

    public JanusGremlinBuilder dropGraph(String graph) {
        stringBuffer.append("ConfiguredGraphFactory.drop('").append(graph).append("');");
        return this;
    }


    public JanusGremlinBuilder containsSchemaLabel(String graph, String schema, SchemaType schemaType) {
        openGraph(graph);
        openManagement(graph);
        if (schemaType.isVertex()) {
            stringBuffer.append("mgt.containsVertexLabel('").append(schema).append("').make();");
        } else {
            stringBuffer.append("mgt.containsEdgeLabel('").append(schema).append("').make();");
        }
        return this;
    }


    public JanusGremlinBuilder makeSchemaLabel(String graph, String schema, SchemaType schemaType) {
        openGraph(graph);
        openManagement(graph);
        if (schemaType.isVertex()) {
            stringBuffer.append("mgt.makeVertexLabel('").append(schema).append("').make();");
        } else {
            stringBuffer.append("mgt.makeEdgeLabel('").append(schema).append("').make();");
        }
        stringBuffer.append("mgt.commit();");
        return this;
    }

    public JanusGremlinBuilder printPropertyKeys(String graph) {
        openGraph(graph);
        openManagement(graph);
        stringBuffer.append("mgt.printPropertyKeys();");
        return this;
    }

    public JanusGremlinBuilder printIndexes(String graph) {
        openGraph(graph);
        openManagement(graph);
        stringBuffer.append("mgt.printIndexes();");
        return this;
    }






    public String toString() {
        return stringBuffer.toString();
    }
}
