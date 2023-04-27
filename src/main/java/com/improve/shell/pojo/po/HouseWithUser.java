package com.improve.shell.pojo.po;

import lombok.Data;

//用户 和 用户发表房子 对应类
@Data
public class HouseWithUser {

    //房子Id
    private String houseId;
    //用户id
    private Long uid;

    //判断是否是用户自己发表的
    private Boolean isOwn;

    //判断用户是否收藏这个房屋信息
    private Boolean isCollect;
}
