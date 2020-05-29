package com.simile.plan.gdb.arango;

import com.arangodb.entity.LoadBalancingStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/05/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArangoDataSourceConfiguration {

    private ArangoHost[] hosts;
    private String user;
    private String password;
    private LoadBalancingStrategy loadBalancingStrategy = LoadBalancingStrategy.ROUND_ROBIN;

    private Long connectionTtl = 5*60*1000L;
    private Boolean acquireHostList = Boolean.TRUE;


}
