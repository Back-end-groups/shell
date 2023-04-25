package com.improve.shell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.HouseImagesMapper;
import com.improve.shell.mapper.HouseMapper;
import com.improve.shell.pojo.po.House;
import com.improve.shell.pojo.po.HouseImages;
import com.improve.shell.service.HouseImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseImagesServiceImp extends ServiceImpl<HouseImagesMapper,HouseImages> implements HouseImagesService {

    @Autowired
    private HouseImagesMapper houseImagesMapper;

    /**
     * 返回房屋对应的所有图片集
     * @param houseId
     * @return
     */
    @Override
    public List<String> getAllImages(String houseId) {
        LambdaQueryWrapper<HouseImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseImages::getHouseId,houseId).select(HouseImages::getImageUrl);
        List<HouseImages> houseImages = houseImagesMapper.selectList(queryWrapper);
        ArrayList<String> images = new ArrayList<>();
        for(HouseImages houseImage : houseImages){
            images.add(houseImage.getImageUrl());
        }
        return images;
    }


}
