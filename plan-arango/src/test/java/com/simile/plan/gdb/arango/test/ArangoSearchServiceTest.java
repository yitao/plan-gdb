package com.simile.plan.gdb.arango.test;

import com.simile.plan.gdb.arango.ArangoSearchServiceImpl;
import com.simile.plan.gdb.arango.api.model.ArangoDataSourceConfiguration;
import com.simile.plan.gdb.arango.api.model.SearchData;
import com.simile.plan.gdb.arango.api.model.SearchResult;
import com.simile.plan.gdb.arango.api.service.ArangoSearchService;
import org.junit.Test;

/**
 * created by yitao on 2020/07/21
 */
public class ArangoSearchServiceTest {

    @Test
    public void testSearch() {
        ArangoSearchService arangoSearchService = new ArangoSearchServiceImpl();
        ArangoDataSourceConfiguration dataSourceConfig = new ArangoDataSourceConfiguration("192.168.1.36", 8529);
        arangoSearchService.init(dataSourceConfig);

        SearchData searchData = new SearchData("beijing_poc", "person_new", 1, 10);
        SearchResult searchResult = arangoSearchService.findByPage(searchData);
        System.out.println(searchData);
    }
}
