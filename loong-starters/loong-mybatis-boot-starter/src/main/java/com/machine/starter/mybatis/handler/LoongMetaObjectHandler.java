package com.machine.starter.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class LoongMetaObjectHandler implements MetaObjectHandler {
    private static final String CREATE_BY_FIELD_NAME = "createBy";
    private static final String CREATE_TIME_FIELD_NAME = "createTime";
    private static final String UPDATE_BY_FIELD_NAME = "updateBy";
    private static final String UPDATE_TIME_FIELD_NAME = "updateTime";
    /**
     * 使用mp实现添加操作,这个方法会执行
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值
        Long currentTimeMillis = System.currentTimeMillis();
        this.setFieldValByName(CREATE_TIME_FIELD_NAME, currentTimeMillis, metaObject);
        this.setFieldValByName(UPDATE_TIME_FIELD_NAME, currentTimeMillis, metaObject);

        this.setFieldValByName(CREATE_BY_FIELD_NAME, "system", metaObject);
        this.setFieldValByName(UPDATE_BY_FIELD_NAME, "system", metaObject);
    }

    /**
     * 使用mp实现修改操作,这个方法会执行
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_TIME_FIELD_NAME, System.currentTimeMillis(), metaObject);
    }
}
