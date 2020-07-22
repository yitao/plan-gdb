package com.simile.plan.gdb.arango.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * created by yitao on 2020/07/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    private List<Map<String,Object>> rows;
    private Long total;

}
