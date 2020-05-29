package com.simile.plan.gdb.open;

import java.util.Set;

/**
 * created by yitao on 2020/05/29
 */
public interface GdbDbAdmin {

    boolean existsDatabase(GdbDatabaseInspection inspection);

    Set<String> databases();

    GdbDatabaseOutcome createDatabase(GdbDatabaseDefinition definition);

    GdbDatabaseOutcome dropDatabase(GdbDatabaseDefinition definition);

}
