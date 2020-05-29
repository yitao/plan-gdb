package com.simile.plan.gdb.janus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by yitao on 2020/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JanusDataSourceConfig {
    private String[] hosts;
    private int port = 8182;
    private String user;
    private String password;

    private int maxConnectionPoolSize = 10;
    private int maxContentLength = 6553500;
    private int maxWaitForConnection = 3000;
    private int workerPoolSize = 4;
}
