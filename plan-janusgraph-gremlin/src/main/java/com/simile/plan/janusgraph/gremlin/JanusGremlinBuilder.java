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

    public static final String DEFAULT_MGT_VAR = "mgt";
    public static final String DEFAULT_CONFIG_VAR = "map";

    public JanusGremlinBuilder clear() {
        stringBuffer.setLength(0);
        return this;
    }

    public JanusGremlinBuilder getGraphNames() {
        stringBuffer.append("ConfiguredGraphFactory.getGraphNames();");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder openManagement(String graph) {
        stringBuffer.append("mgt = " + graph + ".openManagement();");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder showVertexLabels() {
        stringBuffer.append("mgt.getVertexLabels().collect{it.name()};");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder showEdgeLabels() {
        stringBuffer.append("mgt.getRelationTypes(EdgeLabel.class).collect{it.name()};");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder startWithTemplateConfiguration() {
        stringBuffer.append("map = ConfiguredGraphFactory.getTemplateConfiguration();");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder put2Map(String key, String value) {
        stringBuffer.append("map.put(\"" + key + "\",\"" + value + "\");");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder configMap() {
        stringBuffer.append("ConfiguredGraphFactory.createConfiguration(new MapConfiguration(map));");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder openGraph(String graph) {
        stringBuffer.append(graph).append(" = ConfiguredGraphFactory.open('")
                .append(graph).append("');");
        return this;
    }

    @Deprecated
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

    @Deprecated
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

    @Deprecated
    public JanusGremlinBuilder printPropertyKeys(String graph) {
        openGraph(graph);
        openManagement(graph);
        stringBuffer.append("mgt.printPropertyKeys();");
        return this;
    }

    @Deprecated
    public JanusGremlinBuilder printIndexes(String graph) {
        openGraph(graph);
        openManagement(graph);
        stringBuffer.append("mgt.printIndexes();");
        return this;
    }

    public JanusGraphSegment openJanusGraph(String graph, String graphVar) {
        return new JanusGraphSegment(graph, graphVar);
    }

    public JanusGremlinBuilder createGraph(String graph) {
        stringBuffer.append("ConfiguredGraphFactory.create(\"" + graph + "\");");
        return this;
    }

    public JanusGremlinBuilder dropGraph(String graph) {
        stringBuffer.append("ConfiguredGraphFactory.drop('").append(graph).append("');");
        return this;
    }

    public JanusGremlinBuilder createGraph(String graph,String graphVar,String configVar) {
        String preGql = this.openJanusGraph(graph, graph)
                .getTemplateConfiguration(JanusGremlinBuilder.DEFAULT_CONFIG_VAR)
                .put("index.search.index-name", graph.toLowerCase())
                .put("graph.graphname", graph)
                .createConfiguration()
                .toString();
        stringBuffer.append(preGql);
        stringBuffer.append("ConfiguredGraphFactory.create(\"" + graph + "\");");
        return this;
    }

    public String toString() {
        return stringBuffer.toString();
    }


    public static class TemplateConfigurationSegment {
        private StringBuffer stringBuffer;
        private String graph;
        private String configVar;

        public TemplateConfigurationSegment(String graph, String configVar) {
            stringBuffer = new StringBuffer();
            this.graph = graph;
            this.configVar = configVar;
            stringBuffer.append(this.configVar);
            stringBuffer.append("=ConfiguredGraphFactory.getTemplateConfiguration();");
        }

        public TemplateConfigurationSegment put(String key, String value) {
            stringBuffer.append(this.configVar);
            stringBuffer.append(".put(\"" + key + "\",\"" + value + "\");");
            return this;
        }

        public TemplateConfigurationSegment createConfiguration() {
            stringBuffer.append("ConfiguredGraphFactory.createConfiguration(new MapConfiguration(");
            stringBuffer.append(this.configVar);
            stringBuffer.append("));");
            return this;
        }

        public String toString() {
            return stringBuffer.toString();
        }
    }

    public static class JanusGraphManagementSegment {
        private StringBuffer stringBuffer;
        private String graphVar;
        private String mgtVar;

        protected JanusGraphManagementSegment(String graphVar, String mgtVar, String openGraph) {
            stringBuffer = new StringBuffer();
            this.graphVar = graphVar;
            this.mgtVar = mgtVar;
            stringBuffer.append(openGraph);

            stringBuffer.append(this.mgtVar);
            stringBuffer.append("=");
            stringBuffer.append(this.graphVar);
            stringBuffer.append(".openManagement();");
        }

        public JanusGraphManagementSegment showVertexLabels() {
            stringBuffer.append(this.mgtVar);
            stringBuffer.append(".getVertexLabels().collect{it.name()};");
            return this;
        }

        public JanusGraphManagementSegment showEdgeLabels() {
            stringBuffer.append(this.mgtVar);
            stringBuffer.append(".getRelationTypes(EdgeLabel.class).collect{it.name()};");
            return this;
        }

        public JanusGraphManagementSegment containsSchemaLabel(String schema, boolean isVertex) {
            stringBuffer.append(this.mgtVar);
            if (isVertex) {
                stringBuffer.append(".containsVertexLabel('").append(schema).append("').make();");
            } else {
                stringBuffer.append(".containsEdgeLabel('").append(schema).append("').make();");
            }
            return this;
        }

        public JanusGraphManagementSegment makeSchemaLabel(String schema, boolean isVertex) {
            stringBuffer.append(this.mgtVar);
            if (isVertex) {
                stringBuffer.append(".makeVertexLabel('").append(schema).append("').make();");
            } else {
                stringBuffer.append(".makeEdgeLabel('").append(schema).append("').make();");
            }
            stringBuffer.append(this.mgtVar);
            stringBuffer.append(".commit();");
            return this;
        }

        public JanusGraphManagementSegment printPropertyKeys() {
            stringBuffer.append(this.mgtVar);
            stringBuffer.append(".printPropertyKeys();");
            return this;
        }

        public JanusGraphManagementSegment printIndexes() {
            stringBuffer.append(this.mgtVar);
            stringBuffer.append(".printIndexes();");
            return this;
        }

        public String toString() {
            return stringBuffer.toString();
        }

        public JanusGraphSegment openJanusGraph(String graph, String graphVar) {
            return new JanusGraphSegment(graph, graphVar);
        }

    }

    public static class JanusGraphSegment {
        private StringBuffer stringBuffer;
        private JanusGraphManagementSegment janusGraphManagementSegment;
        private TemplateConfigurationSegment templateConfigurationSegment;
        private String graph;
        private String graphVar;

        protected JanusGraphSegment(String graph, String graphVar) {
            this.graph = graph;
            this.graphVar = graphVar;
            stringBuffer = new StringBuffer();
            stringBuffer.append(this.graphVar);
            stringBuffer.append("=ConfiguredGraphFactory.open('")
                    .append(this.graph).append("');");
        }


        public JanusGraphManagementSegment openManagement(String mgtVar) {
            this.janusGraphManagementSegment = new JanusGraphManagementSegment(graphVar, mgtVar, stringBuffer.toString());
            return this.janusGraphManagementSegment;
        }

        public TemplateConfigurationSegment getTemplateConfiguration(String configVar) {
            this.templateConfigurationSegment = new TemplateConfigurationSegment(this.graph, configVar);
            return this.templateConfigurationSegment;
        }

        public String toString() {
            return stringBuffer.toString();
        }

    }

}
