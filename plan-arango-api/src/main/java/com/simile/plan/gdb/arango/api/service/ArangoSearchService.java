package com.simile.plan.gdb.arango.api.service;

import com.simile.plan.gdb.arango.api.model.*;

import java.util.function.Consumer;

/**
 * created by yitao on 2020/07/17
 */
public interface ArangoSearchService {

    ArangoSearchService init(ArangoDataSourceConfiguration dataSourceConfig);

    SearchResult findByPage(SearchData searchData);


}
