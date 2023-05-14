package com.improve.shell.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.HouseMapper;
import com.improve.shell.mapper.repository.HouseRepository;
import com.improve.shell.pojo.po.House;
import com.improve.shell.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HouseServiceImp extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public List<House> getAllHouse() {
        List<House> rooms = houseMapper.selectList(null);
        return rooms;
    }

    @Override
    public IPage<House> search(Page<House> page, String title, String introduction, String address, String between) {
        if (title==null) title = "";
        if (introduction==null) introduction = "";
        if (address==null) address = "";
        int start = 0, end = Integer.parseInt(null);
        if (between != null) {
            String[] split = between.split("-");
            start = Integer.decode(split[0]);
            end = Integer.decode(split[1]);
        }
        log.info("title = {},introduction = {},address = {}, start = {},end = {} ",title,introduction,address,start,end);
        return houseRepository.findByTitleContainingOrIntroductionContainingOrAddressContainingOrTotalPriceBetween(
                page, title, introduction, address, start, end
        );

    }


}
