package com.improve.shell.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-19  18:56
 * @Description: 用于用户登录、身份验证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    // 主键
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 电话号码
    private String phone;

    // token令牌
    private String token;

}
