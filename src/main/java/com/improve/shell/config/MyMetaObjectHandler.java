package com.improve.shell.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充字段，将表的共同属性共同赋值
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("listing", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        long id = Thread.currentThread().getId();
//        log.info("线程id: {}",id);
//         metaObject.setValue("updateTime",LocalDateTime.now());


    }
}
