package com.simile.plan.gdb.gremlin;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.MessageSerializer;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ser.GraphBinaryMessageSerializerV1;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0;
import org.apache.tinkerpop.gremlin.driver.ser.binary.TypeSerializerRegistry;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;
import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * created by yitao on 2020/05/20
 */
public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] ips = {"192.168.1.244","192.168.1.245","192.168.1.246"};
        int port = 8182;
        String user = "haizhi";
        String password = "123456";
//        MessageSerializer serializer = new GraphBinaryMessageSerializerV1(TypeSerializerRegistry.INSTANCE);
        GryoMapper.Builder builder = GryoMapper.build().addRegistry(JanusGraphIoRegistry.getInstance());
        MessageSerializer serializer = new GryoMessageSerializerV3d0(builder);
        Cluster cluster = Cluster.build().addContactPoints(ips)
                .port(port)
                .maxConnectionPoolSize(10)
                .maxContentLength(6553500)
                .maxWaitForConnection(3000)
                .workerPoolSize(4)
                .serializer(serializer)
                .credentials(user, password).create();
        String sessionId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
//        Client client = cluster.connect(sessionId);
        Client client = cluster.connect();
//        String openGraph = "yt_graph = ConfiguredGraphFactory.open('yt_graph');";
        String mgt = "yt_graph.openManagement();";

        List<Result> results = client.submit("ConfiguredGraphFactory.getGraphNames();").all().get();
        for (Result result : results) {
            System.out.println(result.getPath());
        }
    }

}
