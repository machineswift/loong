package com.machine.service.data.leaf.service;

public interface ILoongLeafService {

    String getKqBatchNo(String type,
                        String remark);

    Long getLeafId(String bizTag,
                   Integer step,
                   Long expireTime,
                   String remark);
}
