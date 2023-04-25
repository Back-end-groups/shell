package com.improve.shell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.improve.shell.pojo.po.House;
import com.improve.shell.pojo.po.HouseImages;

import java.util.List;

public interface HouseImagesService extends IService<HouseImages> {
    public List<String> getAllImages(String houseId);

}
