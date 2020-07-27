package com.simile.plan.janusgraph.gremlin;

import org.apache.commons.lang3.StringUtils;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by yitao on 2020/05/25
 */
public enum FieldType {
    STRING("字符串"),
    LONG("整数"),
    DOUBLE("浮点数"),
    DATETIME("日期"),
    UNKNOWN("UNKNOWN");


    private static final Map<String, FieldType> codeLookup = new ConcurrentHashMap(6);
    private String label;

    private FieldType(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    public boolean isNumber() {
        return this.equals(DOUBLE) || this.equals(LONG);
    }

    public boolean isText() {
        return this.equals(STRING) || this.equals(DATETIME);
    }

    public boolean isDate() {
        return this.equals(DATETIME);
    }

    public static FieldType fromCode(String code) {
        if (code == null) {
            return UNKNOWN;
        } else {
            FieldType data = (FieldType)codeLookup.get(code.toLowerCase());
            return data == null ? UNKNOWN : data;
        }
    }

    public static FieldType getFieldType(String value) {
        try {
            return StringUtils.isEmpty(value) ? STRING : (DateUtils.isDate(value) ? DATETIME : (StringUtils.isNumeric(value) ? LONG : (StringUtils.isNumeric(value.replace(".", "").replaceAll(",", "")) ? DOUBLE : STRING)));
        } catch (Exception var2) {
            return STRING;
        }
    }

    static {
        Iterator var0 = EnumSet.allOf(FieldType.class).iterator();

        while(var0.hasNext()) {
            FieldType type = (FieldType)var0.next();
            codeLookup.put(type.name().toLowerCase(), type);
        }

    }
}
