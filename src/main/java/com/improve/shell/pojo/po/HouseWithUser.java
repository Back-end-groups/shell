package com.improve.shell.pojo.po;

import lombok.Data;

//用户 和 用户发表房子 对应类
@Data
public class HouseWithUser {

    //房子Id
    private String houseId;
    //用户id
    private Long uid;
}
