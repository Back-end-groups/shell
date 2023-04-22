package com.improve.shell.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-19  18:28
 * @Description: 对应房源表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    // 主键id：上架房源时间+用户id
    private String id;

    // 房源信息标题
    private String title;

    // 房源信息简介
    private String introduction;

    // 房源单价
    private Long price;

    // 首付价格
    private Long downPayment;

    // 房源总价
    private String totalPrice;

    // 房型（例如：三室两厅）
    private String houseType;

    // 房源首图
    private String firstPicture;

    // 房子总面积
    private int area;

    // 房子建成时间
    private int years;

    // 挂牌时间
    private LocalDateTime listing;

    // 朝向
    private String toward;

    // 房源编号
    private String propertyNumber;

    // 房源地址
    private String address;

    // 楼型
    private String buildType;

    // 楼层
    private String floor;

    // 是否有空调
    private boolean isElevator;

    // 是否有电梯
    private boolean isAirCondition;

    // 是否装修
    private boolean isDecoration;


}
