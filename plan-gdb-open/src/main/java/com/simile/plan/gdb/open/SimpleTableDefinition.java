package com.simile.plan.gdb.open;

import lombok.Builder;
import lombok.Data;

/**
 * created by yitao on 2020/05/29
 */
@Data
@Builder
public class SimpleTableDefinition implements GdbTableDefinition {
    private String database;
    private String table;
}
