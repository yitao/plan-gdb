package com.simile.plan.arango.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/07/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResult {

    private Integer errors;
    private Integer empty;
    private Integer deleted;

}
