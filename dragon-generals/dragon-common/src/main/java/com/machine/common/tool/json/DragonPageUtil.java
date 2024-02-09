package com.machine.common.tool.json;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class DragonPageUtil {

    public static <T1, T2> Page<T2> convertT1ToT2(IPage<T1> page, Class<T2> clazz) {
        List<T1> recordsT1 = page.getRecords();

        Page<T2> pageT2 = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        if (CollectionUtils.isEmpty(recordsT1)) {
            return pageT2;
        }
        pageT2.setRecords(JSONUtil.toList(JSONUtil.toJsonStr(recordsT1), clazz));
        return pageT2;
    }

}
