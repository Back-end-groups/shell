package com.improve.shell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.improve.shell.pojo.po.HouseWithUser;
import com.improve.shell.pojo.vo.HouseVO;

import java.util.List;

public interface HouseWithUserService extends IService<HouseWithUser> {

    //查询房屋信息是否是用户本人发表，为后续的房屋信息删除提供判断条件
    public boolean isMyPublic(String houseId);

    //通过用户uid，查询用户发表的所有发表信息
    public List<HouseVO> getMyPublic(Long uid);

    //点击收藏
    public Boolean ClickCollect(String houseId,Long uid);

    //取消收藏
    public Boolean CancelCollect(String houseId,Long uid);

    //查看用户所有收藏信息
    public List<HouseVO> AllMyCollect(Long uid);
}
