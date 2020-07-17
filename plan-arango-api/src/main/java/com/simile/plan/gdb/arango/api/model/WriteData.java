package com.simile.plan.gdb.arango.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by yitao on 2020/07/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteData<T> {

    private String database;

    private String table;

    private List<T> rows;


}
