package com.simile.plan.gdb.arango;

import com.arangodb.ArangoDB;
import com.simile.plan.gdb.open.*;

import java.util.HashSet;
import java.util.Set;

/**
 * created by yitao on 2020/05/29
 */
public class ArangoDbAdmin implements GdbDbAdmin {

    private ArangoDataSource dataSource;
    private ArangoDB arangoDB;

    public ArangoDbAdmin(ArangoDataSource dataSource) {
        this.dataSource = dataSource;
        this.arangoDB = dataSource.getArangoDB();
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
}
