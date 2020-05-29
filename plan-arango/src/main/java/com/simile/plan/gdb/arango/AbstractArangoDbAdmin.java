package com.simile.plan.gdb.arango;

import com.simile.plan.gdb.open.GdbDatabaseDefinition;
import com.simile.plan.gdb.open.GdbDatabaseInspection;
import com.simile.plan.gdb.open.GdbDatabaseOutcome;
import com.simile.plan.gdb.open.GdbDbAdmin;

import java.util.Set;

/**
 * created by yitao on 2020/05/29
 */
public abstract class AbstractArangoDbAdmin implements GdbDbAdmin {

    @Override
    public boolean existsDatabase(GdbDatabaseInspection inspection) {
        return false;
    }

    @Override
    public GdbDatabaseOutcome createDatabase(GdbDatabaseDefinition definition) {
        return null;
    }

    @Override
    public GdbDatabaseOutcome dropDatabase(GdbDatabaseDefinition definition) {
        return null;
    }
}
