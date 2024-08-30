package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
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
    private Long current;

    //每页的数量
    private Long size;

    //总记录数
    protected Long total;

    //结果集
    protected List<T> records = Collections.emptyList();
}
