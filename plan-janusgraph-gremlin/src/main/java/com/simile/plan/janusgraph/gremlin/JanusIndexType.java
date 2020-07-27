package com.simile.plan.janusgraph.gremlin;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum JanusIndexType {
    COMPOSITE_INDEX("Composite"),
    MIXED_INDEX("Mixed");

    private String value;

    private static Map<String, JanusIndexType> valueMap;

    static {
        valueMap = new HashMap<>(16);
        List<JanusIndexType> enumList = EnumUtils.getEnumList(JanusIndexType.class);
        enumList.forEach(indexType -> valueMap.put(indexType.getValue(), indexType));
    }

    public static JanusIndexType getByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return valueMap.get(value);
    }

    JanusIndexType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
