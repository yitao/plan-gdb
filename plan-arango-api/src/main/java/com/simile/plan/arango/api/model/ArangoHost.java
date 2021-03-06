package com.simile.plan.arango.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/05/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArangoHost {
    private String host;
    private int port;
}
