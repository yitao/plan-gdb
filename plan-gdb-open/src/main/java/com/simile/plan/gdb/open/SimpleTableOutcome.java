package com.simile.plan.gdb.open;

import lombok.Builder;
import lombok.Data;

/**
 * created by yitao on 2020/05/29
 */
@Data
@Builder
public class SimpleTableOutcome implements GdbTableOutcome{

    private boolean ok;
}
