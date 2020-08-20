package com.simile.plan.arango.api.service;

import com.simile.plan.arango.api.model.ArangoDataSourceConfiguration;
import com.simile.plan.arango.api.model.SearchData;
import com.simile.plan.arango.api.model.SearchResult;

/**
 * created by yitao on 2020/07/17
 */
public interface ArangoSearchService {

    ArangoSearchService init(ArangoDataSourceConfiguration dataSourceConfig);

    SearchResult findByPage(SearchData searchData);


}
