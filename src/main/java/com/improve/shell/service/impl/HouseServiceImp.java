package com.improve.shell.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.HouseMapper;
import com.improve.shell.pojo.po.House;
import com.improve.shell.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImp extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Override
    public List<House> getAllHouse() {
        List<House> rooms = houseMapper.selectList(null);
        return rooms;
    }
}
