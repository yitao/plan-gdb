package com.simile.plan.gdb.arango;

import com.arangodb.ArangoDatabase;
import lombok.Data;

/**
 * created by yitao on 2020/05/29
 */
@Data
public class ArangoRepository {

    private ArangoDataSource arangoDataSource;

    private ArangoDatabase arangoDatabase;

    public ArangoRepository(ArangoDataSource arangoDataSource,String database) {
        this.arangoDataSource = arangoDataSource;
        this.arangoDatabase = arangoDataSource.getArangoDB().db(database);
    }


}
