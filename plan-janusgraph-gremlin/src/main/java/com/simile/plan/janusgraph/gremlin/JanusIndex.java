package com.simile.plan.janusgraph.gremlin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/05/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JanusIndex {
    private String indexName;
    private JanusIndexType type;
    private Boolean unique;
    private String backing;
    private String key;
    private String status;
    private FieldType fieldType;

    public JanusIndex(String key, JanusIndexType type, FieldType fieldType) {
        this.type = type;
        this.key = key;
        this.fieldType = fieldType;
    }

}
