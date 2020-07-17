package com.simile.plan.gdb.arango;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.CollectionEntity;
import com.simile.plan.gdb.arango.api.model.ArangoDataSourceConfiguration;
import com.simile.plan.gdb.arango.api.service.ArangoAdminService;
import com.simile.plan.gdb.open.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by yitao on 2020/07/17
 */
public class ArangoAdminServiceImpl implements ArangoAdminService {

    private ArangoDataSource dataSource;
    private ArangoDB arangoDB;

    @Override
    public ArangoAdminServiceImpl init(ArangoDataSourceConfiguration dataSourceConfig) {
        ArangoDataSource dataSource = new ArangoDataSource(dataSourceConfig);
        this.dataSource = dataSource;
        this.arangoDB = dataSource.getArangoDB();
        return this;
    }

    @Override
    public boolean existsDatabase(GdbDatabaseInspection inspection) {
        return arangoDB.db(inspection.getDatabase()).exists();
    }

    @Override
    public Set<String> databases() {
        return new HashSet<>(arangoDB.getDatabases());
    }

    @Override
    public GdbDatabaseOutcome createDatabase(GdbDatabaseDefinition definition) {
        if (arangoDB.db(definition.getDatabase()).exists()) {
            return SimpleDatabaseOutcome.builder().ok(false).build();
        }
        arangoDB.createDatabase(definition.getDatabase());
        return SimpleDatabaseOutcome.builder().ok(true).build();
    }

    @Override
    public GdbDatabaseOutcome dropDatabase(GdbDatabaseDefinition definition) {
        if (!arangoDB.db(definition.getDatabase()).exists()) {
            return SimpleDatabaseOutcome.builder().ok(false).build();
        }
        arangoDB.db(definition.getDatabase()).drop();
        return SimpleDatabaseOutcome.builder().ok(true).build();
    }


    @Override
    public boolean existsTable(GdbTableInspection inspection) {
        return getArangoDatabase(inspection.getDatabase())
                .collection(inspection.getTable()).exists();
    }

    @Override
    public Set<String> tables(GdbDatabaseInspection inspection) {
        return getArangoDatabase(inspection.getDatabase())
                .getCollections().stream()
                .map(CollectionEntity::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public GdbTableOutcome createTable(GdbTableDefinition definition) {
        if (getArangoDatabase(definition.getDatabase())
                .collection(definition.getTable()).exists()) {
            return SimpleTableOutcome.builder().ok(false).build();
        }
        getArangoDatabase(definition.getDatabase())
                .createCollection(definition.getTable());
        return SimpleTableOutcome.builder().ok(true).build();
    }

    @Override
    public GdbTableOutcome dropTable(GdbTableDefinition definition) {
        if (!getArangoDatabase(definition.getDatabase())
                .collection(definition.getTable()).exists()) {
            return SimpleTableOutcome.builder().ok(false).build();
        }
        getArangoDatabase(definition.getDatabase())
                .collection(definition.getTable()).drop();
        return SimpleTableOutcome.builder().ok(true).build();
    }

    @Override
    public GdbIndexOutcome createIndex(GdbIndexDefinition definition) {
        return null;
    }

    @Override
    public GdbIndexOutcome dropIndex(GdbIndexDefinition definition) {
        return null;
    }


    private ArangoDatabase getArangoDatabase(String database) {
        return this.arangoDB.db(database);
    }
}
