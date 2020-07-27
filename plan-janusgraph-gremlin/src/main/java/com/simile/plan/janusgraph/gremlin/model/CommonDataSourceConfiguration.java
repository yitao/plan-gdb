package com.simile.plan.janusgraph.gremlin.model;

import com.simile.plan.janusgraph.api.model.DataSourceConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonDataSourceConfiguration implements DataSourceConfiguration {
    private String[] hosts;
    private int port = 8182;
    private String user;
    private String password;

    private int maxConnectionPoolSize = 10;
    private int maxContentLength = 6553500;
    private int maxWaitForConnection = 3000;
    private int workerPoolSize = 4;

    public CommonDataSourceConfiguration(String[] hosts, int port, String user, String password) {
        this.hosts = hosts;
        this.port = port;
        this.user = user;
        this.password = password;
    }
}
