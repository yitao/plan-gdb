package com.simile.plan.arango.api.service;

import com.simile.plan.arango.api.model.*;

import java.util.function.Consumer;

/**
 * created by yitao on 2020/07/17
 */
public interface ArangoUserService {

    ArangoUserService init(ArangoDataSourceConfiguration dataSourceConfig);

    WriteResult save(WriteData writeData);

    void saveAsync(WriteData writeData, Consumer<WriteResult> callback);

    DeleteResult delete(DeleteData deleteData);

}
