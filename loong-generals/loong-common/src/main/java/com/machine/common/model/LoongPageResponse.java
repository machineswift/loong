package com.machine.common.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class LoongPageResponse<T> {
    public LoongPageResponse(long current,
                             long size,
                             long total) {
        this.current = current;
        this.size = size;
        this.total = total;
    }

    public LoongPageResponse(long current,
                             long size,
                             long total,
                             List<T> records) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.records = records;
    }

    //当前页
    private long current;

    //每页的数量
    private long size;

    //总记录数
    protected long total;

    //结果集
    protected List<T> records = Collections.emptyList();
}
