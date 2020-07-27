package com.simile.plan.janusgraph.gremlin;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by yitao on 2020/05/25
 */
public enum SchemaTypeEnum implements SchemaType {
    VERTEX("实体", false, false),
    VERTEX_MAIN("主实体", false, false),
    EDGE("普通边", false, true),
    EDGE_DETAIL("明细边", false, true),
    EDGE_SUMMARY("汇总边", true, true),
    EDGE_DM("挖掘边", true, true);

    private String label;
    private boolean hidden;
    private boolean edge;
    private static final Map<String, SchemaTypeEnum> codeLookup = new ConcurrentHashMap(6);

    private SchemaTypeEnum(String label, boolean hidden, boolean edge) {
        this.label = label;
        this.hidden = hidden;
        this.edge = edge;
    }

    public boolean isEdge() {
        return this.edge;
    }

    public boolean isVertex() {
        return !this.edge;
    }

    public static boolean isVertex(SchemaTypeEnum type) {
        return !isEdge(type);
    }

    public static boolean isMainVertex(SchemaTypeEnum type) {
        return type == VERTEX_MAIN;
    }

    public static boolean isEdge(SchemaTypeEnum type) {
        return type.edge;
    }

    public static String label(SchemaTypeEnum type) {
        return type.label;
    }

    public static SchemaTypeEnum fromCode(String code) {
        return (SchemaTypeEnum)codeLookup.get(code);
    }

    static {
        Iterator var0 = EnumSet.allOf(SchemaTypeEnum.class).iterator();

        while(var0.hasNext()) {
            SchemaTypeEnum schemaType = (SchemaTypeEnum)var0.next();
            codeLookup.put(schemaType.name(), schemaType);
        }

    }
}
