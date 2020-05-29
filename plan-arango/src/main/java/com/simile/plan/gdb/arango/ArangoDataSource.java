package com.simile.plan.gdb.arango;

import com.arangodb.ArangoDB;

/**
 * created by yitao on 2020/05/29
 */
public class ArangoDataSource {

    private ArangoDataSourceConfiguration dataSourceConfig;
    private ArangoDB arangoDB;

    public ArangoDataSource(ArangoDataSourceConfiguration dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
        initArangoDB();
    }

    private void initArangoDB() {
        ArangoDB.Builder builder = new ArangoDB.Builder();
        for (ArangoHost host : dataSourceConfig.getHosts()) {
            builder.host(host.getHost(), host.getPort());
        }
        this.arangoDB = builder
                .user(dataSourceConfig.getUser())
                .password(dataSourceConfig.getPassword())
                .acquireHostList(dataSourceConfig.getAcquireHostList())
                .loadBalancingStrategy(dataSourceConfig.getLoadBalancingStrategy())
                .connectionTtl(dataSourceConfig.getConnectionTtl())
                .build();
    }

    public ArangoDB getArangoDB() {
        return arangoDB;
    }
}
