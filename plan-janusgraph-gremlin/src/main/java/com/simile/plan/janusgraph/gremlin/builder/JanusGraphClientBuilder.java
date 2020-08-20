package com.simile.plan.janusgraph.gremlin.builder;

import com.simile.plan.janusgraph.gremlin.JanusGraphClient;
import com.simile.plan.janusgraph.gremlin.model.CommonDataSourceConfiguration;

/**
 * @author yitao
 * @since 2020-07-30
 */
public class JanusGraphClientBuilder {

    public static JanusGraphClient buildJanusGraphClient(CommonDataSourceConfiguration janusDataSourceConfig) {
        return new JanusGraphClient(janusDataSourceConfig);
    }

}
