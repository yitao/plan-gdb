package com.simile.plan.gdb.arango.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/07/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchData {
    private String database;

    private String table;

    private int pageNo;

    private int pageSize;
}
