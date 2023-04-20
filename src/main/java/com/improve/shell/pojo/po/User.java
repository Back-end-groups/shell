package com.improve.shell.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 和数据库表属性一一对应
/**
 * @Author: fengxin
 * @CreateTime: 2023-04-15  09:30
 * @Description: 对应用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // 主键
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 性别
    private String sex;

    // 昵称
    private String nickname;

    // 头像
    private String avatar;

    // 电话号码
    private String phone;

    // 身份证号码
    private String idCard;

    // 注册时间
    private String createTime;

    // 角色身份
    private String role;

    // 账号状态
    private String status;

    //常住地
    private String address;

    //出生日期
    private String birthday;

    //个性签名
    private String description;

}
