package com.simile.plan.gdb.janus;

import com.haizhi.atlas.common.base.constant.GOperation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by chengmo on 2019/3/5.
 */
@Data
@NoArgsConstructor
public class GdbSuo {
    private String graph;
    private String schema;
    private SchemaType type;
    private String direction;
    private GOperation operation;
    private Map<String, SchemaField> schemaFields;
    private List<Map<String, Object>> rows;

    public Map<String, FieldType> getFields() {
        Map<String, FieldType> fields = new HashMap<>(16);
        if (Objects.isNull(schemaFields) || schemaFields.isEmpty()) {
            return fields;
        }
        for (Map.Entry<String, SchemaField> entry : schemaFields.entrySet()) {
            fields.put(entry.getKey(), entry.getValue().getType());
        }
        return fields;
    }
}
