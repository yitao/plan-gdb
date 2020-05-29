package com.simile.plan.gdb.open;

import java.util.Set;

/**
 * created by yitao on 2020/05/29
 */
public interface GdbTableAdmin {

    boolean existsTable(GdbTableInspection inspection);

    Set<String> tables(GdbDatabaseInspection inspection);

    GdbTableOutcome createTable(GdbTableDefinition definition);

    GdbTableOutcome dropTable(GdbTableDefinition definition);

    GdbIndexOutcome createIndex(GdbIndexDefinition definition);

    GdbIndexOutcome dropIndex(GdbIndexDefinition definition);

}
