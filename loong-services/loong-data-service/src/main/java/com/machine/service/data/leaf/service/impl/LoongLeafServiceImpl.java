package com.machine.service.data.leaf.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.machine.common.exception.data.DataBusinessException;
import com.machine.service.data.leaf.dao.ILoongLeafDao;
import com.machine.service.data.leaf.dao.mapper.entity.LoongLeafEntity;
import com.machine.service.data.leaf.service.ILoongLeafService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

@Slf4j
@Service
public class LoongLeafServiceImpl implements ILoongLeafService {

    @Autowired
    private ILoongLeafDao leafDao;

    @Override
    public String getKqBatchNo(String type,
                               String remark) {

        //过期时间(号段是以日期开头)
        Long expireTime = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000;
        String bizTag = type + DateUtil.format(LocalDateTime.now(), PURE_DATE_PATTERN);


        String value = getLeafId(bizTag, 100, expireTime, remark).toString();
        while (value.length() < 8) {
            value = "0" + value;
        }
        return bizTag + value;
    }


    @Override
    public Long getLeafId(String bizTag,
                          Integer step,
                          Long expireTime,
                          String remark) {
        if (StrUtil.isBlank(bizTag)) {
            throw new InvalidParameterException("bizTag 不能为空");
        }

        if (null != step && step < 1) {
            throw new InvalidParameterException("step 不能为空小于1");
        }

        LeafData leafData = leafMap.get(bizTag);
        //初始化 LeafData
        if (null == leafData) {
            leafData = new LeafData(expireTime);
            leafMap.putIfAbsent(bizTag, leafData);
            leafData = leafMap.get(bizTag);
        }

        Long id = leafData.poll();
        if (null == id) {
            LoongLeafEntity entity = new LoongLeafEntity();
            entity.setBizTag(bizTag);
            entity.setStep(step);
            entity.setExpireTime(expireTime);
            entity.setRemark(remark);
            LoongLeafEntity leafAlloc = leafDao.updateMaxId(entity);
            if (null == leafAlloc) {
                throw new DataBusinessException("leaf 取号失败");
            }

            if (leafAlloc.getStep() > 1) {
                id = leafAlloc.getMaxId() + 1;
                for (int i = 1; i < leafAlloc.getStep(); i++) {
                    leafData.add(leafAlloc.getMaxId() + i + 1);
                }
            } else if (leafAlloc.getStep() == 1) {
                id = leafAlloc.getMaxId() + 1;
            } else {
                throw new InvalidParameterException("获取leaf数据失败,step 小于1");
            }
        }
        return id;
    }


    @Data
    @NoArgsConstructor
    private static class LeafData {
        private Long expireTime;
        private Queue<Long> queue = new ConcurrentLinkedQueue<>();

        public LeafData(Long expireTime) {
            this.expireTime = expireTime;
        }

        void add(Long id) {
            this.queue.add(id);
        }

        Long poll() {
            return this.queue.poll();
        }
    }


    /**
     * 清理过期leaf数据（每天早上6点执行一次）
     */
    @Scheduled(cron = "0 6 * * * ?")
    private void loadTenantInfo() {
        Iterator<Map.Entry<String, LeafData>> it = leafMap.entrySet().iterator();
        Long currentTimeMillis = System.currentTimeMillis();
        while (it.hasNext()) {
            Map.Entry<String, LeafData> entry = it.next();
            LeafData leafData = entry.getValue();
            if (null != leafData && null != leafData.getExpireTime()) {
                if (leafData.getExpireTime().compareTo(currentTimeMillis) < 0) {
                    // 使用迭代器的remove()方法删除元素
                    it.remove();
                }
            }
        }
    }

    private static final Map<String, LeafData> leafMap = new ConcurrentHashMap<>();

}
