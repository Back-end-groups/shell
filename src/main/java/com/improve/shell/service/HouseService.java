package com.improve.shell.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.improve.shell.pojo.po.House;

import java.util.List;

public interface HouseService  extends IService<House> {
    public List<House> getAllHouse();

    // 整合es后的首页分页功能
    IPage<House> search(Page<House> page, String title, String introduction, String address, String between);



}
