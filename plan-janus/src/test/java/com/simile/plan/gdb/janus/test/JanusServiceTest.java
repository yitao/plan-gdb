package com.simile.plan.gdb.janus.test;

import com.simile.plan.gdb.janus.JanusDataSourceConfig;
import com.simile.plan.gdb.janus.JanusRepository;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * created by yitao on 2020/07/22
 */
public class JanusServiceTest {

    @Test
    public void testDatabases() throws ExecutionException, InterruptedException {
        String[] hosts = {"192.168.1.244","192.168.1.245","192.168.1.246"};
        JanusDataSourceConfig dataSourceConfig = new JanusDataSourceConfig(hosts,8182,"haizhi","123456");
        JanusRepository janusRepository = new JanusRepository(dataSourceConfig);
        List<String> databases = janusRepository.databases();
        System.out.println(databases);
    }

}
