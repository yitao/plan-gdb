package com.simile.plan.gdb.arango.api.model;

import com.simile.plan.gdb.arango.constants.LoadBalancingStrategy;
import com.simile.plan.gdb.open.GdbDataSourceConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/05/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArangoDataSourceConfiguration implements GdbDataSourceConfiguration {

    private ArangoHost[] hosts;
    private String user;
    private String password;
    private LoadBalancingStrategy loadBalancingStrategy = LoadBalancingStrategy.ROUND_ROBIN;

    private Long connectionTtl = 5 * 60 * 1000L;
    private Boolean acquireHostList = Boolean.TRUE;

    public ArangoDataSourceConfiguration(String host, int port) {
        ArangoHost arangoHost = new ArangoHost(host, port);
        this.hosts = new ArangoHost[]{arangoHost};
    }
}
