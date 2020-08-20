package com.simile.plan.arango;

import com.arangodb.ArangoDB;
import com.arangodb.entity.LoadBalancingStrategy;
import com.simile.plan.arango.api.model.ArangoDataSourceConfiguration;
import com.simile.plan.arango.api.model.ArangoHost;

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
        LoadBalancingStrategy loadBalancingStrategy;
        try {
            loadBalancingStrategy = LoadBalancingStrategy
                    .valueOf(dataSourceConfig.getLoadBalancingStrategy().name());
        } catch (Exception e) {
            loadBalancingStrategy = LoadBalancingStrategy.ROUND_ROBIN;
        }

        this.arangoDB = builder
                .user(dataSourceConfig.getUser())
                .password(dataSourceConfig.getPassword())
                .acquireHostList(dataSourceConfig.getAcquireHostList())
                .loadBalancingStrategy(loadBalancingStrategy)
                .connectionTtl(dataSourceConfig.getConnectionTtl())
                .build();
    }

    public ArangoDB getArangoDB() {
        return arangoDB;
    }
}
