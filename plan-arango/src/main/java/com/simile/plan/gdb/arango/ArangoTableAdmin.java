package com.simile.plan.gdb.arango;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.CollectionEntity;
import com.simile.plan.gdb.open.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by yitao on 2020/05/29
 */
public class ArangoTableAdmin implements GdbTableAdmin {
    private ArangoDataSource dataSource;
    private ArangoDB arangoDB;
    private ArangoDatabase arangoDatabase;

    public ArangoTableAdmin(ArangoDataSource dataSource, String database) {
        this.dataSource = dataSource;
        this.arangoDB = dataSource.getArangoDB();
        this.arangoDatabase = this.arangoDB.db(database);
    }

    @Override
    public boolean existsTable(GdbTableInspection inspection) {
        return arangoDatabase.collection(inspection.getTable()).exists();
    }

    @Override
    public Set<String> tables(GdbDatabaseInspection inspection) {
        return arangoDatabase.getCollections().stream()
                .map(CollectionEntity::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public GdbTableOutcome createTable(GdbTableDefinition definition) {
        if (arangoDatabase.collection(definition.getTable()).exists()) {
            return SimpleTableOutcome.builder().ok(false).build();
        }
        arangoDatabase.createCollection(definition.getTable());
        return SimpleTableOutcome.builder().ok(true).build();
    }

    @Override
    public GdbTableOutcome dropTable(GdbTableDefinition definition) {
        if (!arangoDatabase.collection(definition.getTable()).exists()) {
            return SimpleTableOutcome.builder().ok(false).build();
        }
        arangoDatabase.collection(definition.getTable()).drop();
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
}
