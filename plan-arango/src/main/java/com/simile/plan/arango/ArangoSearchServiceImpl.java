package com.simile.plan.arango;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.model.AqlQueryOptions;
import com.simile.plan.arango.api.model.ArangoDataSourceConfiguration;
import com.simile.plan.arango.api.model.SearchData;
import com.simile.plan.arango.api.model.SearchResult;
import com.simile.plan.arango.api.service.ArangoSearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by yitao on 2020/07/21
 */
public class ArangoSearchServiceImpl
        implements ArangoSearchService {

    private ArangoDataSource dataSource;
    private ArangoDB arangoDB;

    public ArangoSearchServiceImpl(ArangoDataSourceConfiguration dataSourceConfig) {
        init(dataSourceConfig);
    }

    @Override
    public ArangoSearchService init(ArangoDataSourceConfiguration dataSourceConfig) {
        ArangoDataSource dataSource = new ArangoDataSource(dataSourceConfig);
        this.dataSource = dataSource;
        this.arangoDB = dataSource.getArangoDB();
        return this;
    }

    @Override
    public SearchResult findByPage(SearchData searchData) {
        ArangoDatabase adb = getArangoDatabase(searchData.getDatabase());
        ArangoCollection ac = getArangoCollection(searchData.getDatabase(), searchData.getTable());
        AqlQueryOptions queryOptions = new AqlQueryOptions();
        queryOptions.fullCount(true);
        Map<String, Object> bindVars = new HashMap<>();
        bindVars.put("@schema", searchData.getTable());
        bindVars.put("from", (searchData.getPageNo() - 1) * searchData.getPageSize());
        bindVars.put("size", searchData.getPageSize());
        StringBuilder sb = new StringBuilder("FOR doc IN @@schema ");
//        sb.append("filter doc.object_key=='471413189417353118'");
//        sb.append("FILTER NOT LIKE(doc.object_key,'%47%',false) AND doc.xm!='赵莉莎'");
        sb.append("FILTER doc.zy!=null and doc.zy!=''");
        sb.append("LIMIT @from,@size ");
        sb.append("RETURN doc");
        ArangoCursor cursor = adb.query(sb.toString(), bindVars, queryOptions, Map.class);

        Long total = cursor.getStats() == null ? null :
                (cursor.getStats().getFullCount() == null ? null : cursor.getStats().getFullCount());
        List<Map<String, Object>> rows = new ArrayList<>();
        while (cursor.hasNext()) {
            rows.add((Map) cursor.next());
        }
        SearchResult searchResult = new SearchResult(rows, total);
        return searchResult;
    }

    private ArangoDatabase getArangoDatabase(String database) {
        return this.arangoDB.db(database);
    }

    private ArangoCollection getArangoCollection(String database, String collection) {
        return getArangoDatabase(database).collection(collection);
    }


}
