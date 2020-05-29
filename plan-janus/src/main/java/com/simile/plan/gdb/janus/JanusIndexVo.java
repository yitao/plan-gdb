package com.simile.plan.gdb.janus;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * created by yitao on 2020/05/25
 */
@Data
public class JanusIndexVo {
    private List<JanusIndex> vertexIndexes;

    private List<JanusIndex> edgeIndexes;

    private List<RelationIndex> relationIndices;

    public JanusIndexVo() {
        this.vertexIndexes = Lists.newArrayListWithExpectedSize(10);
        this.edgeIndexes = Lists.newArrayListWithExpectedSize(10);
        this.relationIndices = Lists.newArrayListWithExpectedSize(10);
    }

    public JanusIndexVo(List<JanusIndex> vertexIndexes, List<JanusIndex> edgeIndexes, List<RelationIndex> relationIndices) {
        this.vertexIndexes = vertexIndexes;
        this.edgeIndexes = edgeIndexes;
        this.relationIndices = relationIndices;
    }
}
