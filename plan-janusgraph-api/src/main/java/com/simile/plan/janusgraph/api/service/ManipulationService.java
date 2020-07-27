package com.simile.plan.janusgraph.api.service;

import com.simile.plan.janusgraph.api.model.ManipulationResultSet;

/**
 * DML 服务定义
 * insert/update/delete
 * @author yitao
 * @since 2020-07-24
 */
public interface ManipulationService {

    ManipulationResultSet insert();

    ManipulationResultSet update();

    ManipulationResultSet upsert();

    ManipulationResultSet delete();

}
