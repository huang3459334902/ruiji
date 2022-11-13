package com.huang.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.huang.common.BaseContest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill....");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);

        if (metaObject.hasGetter("createUser")) {
            metaObject.setValue("createUser", BaseContest.getCurrentId());
        }

        if (metaObject.hasGetter("updateUser")) {
            metaObject.setValue("updateUser", BaseContest.getCurrentId());
        }

        if (metaObject.hasGetter("userId")) {
            metaObject.setValue("userId", BaseContest.getCurrentId());
        }



    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill....");
        this.setFieldValByName("updateTime",new Date(),metaObject);

        if (metaObject.hasGetter("updateUser")) {
            metaObject.setValue("updateUser", BaseContest.getCurrentId());
        }

    }
}
