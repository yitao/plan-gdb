package com.simile.plan.arango.api.service;

import com.simile.plan.arango.api.model.ArangoDataSourceConfiguration;
import com.simile.plan.gdb.open.*;

import java.util.Set;

/**
 * created by yitao on 2020/07/17
 */
public interface ArangoAdminService extends GdbAdminService<ArangoDataSourceConfiguration> {

    ArangoAdminService init(ArangoDataSourceConfiguration dataSourceConfig);

    boolean existsDatabase(GdbDatabaseInspection inspection);

    Set<String> databases();

    GdbDatabaseOutcome createDatabase(GdbDatabaseDefinition definition);

    GdbDatabaseOutcome dropDatabase(GdbDatabaseDefinition definition);

    boolean existsTable(GdbTableInspection inspection);

    Set<String> tables(GdbDatabaseInspection inspection);

    GdbTableOutcome createTable(GdbTableDefinition definition);

    GdbTableOutcome dropTable(GdbTableDefinition definition);

    GdbIndexOutcome createIndex(GdbIndexDefinition definition);

    GdbIndexOutcome dropIndex(GdbIndexDefinition definition);

}
