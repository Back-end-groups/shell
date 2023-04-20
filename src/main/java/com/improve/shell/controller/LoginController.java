package com.improve.shell.controller;

import com.improve.shell.handler.NoAuth;
import com.improve.shell.pojo.vo.UserVO;
import com.improve.shell.service.UserService;
import com.improve.shell.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-19  18:51
 * @Description: 处理登录注册
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /*
     * @description: 通过用户名+密码登录
     * @author: fengxin
     * @date: 2023/4/20 10:39
     * @param: [uservo]：{username,password}
     * @return: com.improve.shell.util.Result
     **/
    @NoAuth
    @PostMapping("/loginByUser")
    public Result login(@RequestBody UserVO uservo){
        log.info("传过来的uservo==>{}",uservo);
        return userService.loginByUser(uservo);
    }


    /* 注册用户名+并设置密码
     * @description:
     * @author: fengxin
     * @date: 2023/4/20 10:38
     * @param: [uservo]：{username,password}
     * @return: com.improve.shell.util.Result
     **/
    @NoAuth
    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody UserVO uservo){
        log.info("传过来的uservo==>{}",uservo);
        return userService.register(uservo);
    }

}
