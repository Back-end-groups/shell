package com.improve.shell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.improve.shell.pojo.po.House;

import java.util.List;

public interface HouseService  extends IService<House> {
    public List<House> getAllHouse();
}
