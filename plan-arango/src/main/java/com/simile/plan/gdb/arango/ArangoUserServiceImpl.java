package com.simile.plan.gdb.arango;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.DocumentImportEntity;
import com.arangodb.entity.MultiDocumentEntity;
import com.arangodb.model.DocumentImportOptions;
import com.simile.plan.gdb.arango.api.model.*;
import com.simile.plan.gdb.arango.api.service.ArangoUserService;

import java.util.function.Consumer;

/**
 * created by yitao on 2020/07/17
 */
public class ArangoUserServiceImpl implements ArangoUserService {

    private ArangoDataSource dataSource;
    private ArangoDB arangoDB;

    public ArangoUserServiceImpl(ArangoDataSourceConfiguration dataSourceConfig) {
        init(dataSourceConfig);
    }

    @Override
    public ArangoUserService init(ArangoDataSourceConfiguration dataSourceConfig) {
        ArangoDataSource dataSource = new ArangoDataSource(dataSourceConfig);
        this.dataSource = dataSource;
        this.arangoDB = dataSource.getArangoDB();
        return this;
    }

    @Override
    public WriteResult save(WriteData writeData) {
        DocumentImportOptions options = new DocumentImportOptions().waitForSync(true);
        options.onDuplicate(DocumentImportOptions.OnDuplicate.update);

        ArangoCollection ac = getArangoCollection(writeData.getDatabase(), writeData.getTable());
        //考虑分批写入数据，降低arango负载
        DocumentImportEntity res = ac.importDocuments(writeData.getRows(), options);

        WriteResult wr = new WriteResult(res.getCreated(), res.getErrors()
                , res.getEmpty(), res.getUpdated(), res.getIgnored());
        return wr;
    }

    @Override
    public void saveAsync(WriteData writeData, Consumer<WriteResult> callback) {
        DocumentImportOptions options = new DocumentImportOptions().waitForSync(false);
        options.onDuplicate(DocumentImportOptions.OnDuplicate.update);

        ArangoCollection ac = getArangoCollection(writeData.getDatabase(), writeData.getTable());
        //考虑分批写入数据，降低arango负载
        DocumentImportEntity res = ac.importDocuments(writeData.getRows());

        WriteResult wr = new WriteResult(res.getCreated(), res.getErrors()
                , res.getEmpty(), res.getUpdated(), res.getIgnored());
        callback.accept(wr);
    }

    @Override
    public DeleteResult delete(DeleteData deleteData) {
        ArangoCollection ac = getArangoCollection(deleteData.getDatabase(), deleteData.getTable());

        MultiDocumentEntity res = ac.deleteDocuments(deleteData.getKeys());

        DeleteResult dr = new DeleteResult(res.getErrors().size(), null, res.getDocuments().size());
        return dr;
    }


    private ArangoDatabase getArangoDatabase(String database) {
        return this.arangoDB.db(database);
    }

    private ArangoCollection getArangoCollection(String database, String collection) {
        return getArangoDatabase(database).collection(collection);
    }


}
