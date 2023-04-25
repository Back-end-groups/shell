package com.improve.shell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.improve.shell.pojo.po.User;
import com.improve.shell.pojo.vo.UserVO;
import com.improve.shell.util.Result;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-19  19:01
 * @Description: 用户服务类接口
 */
public interface UserService extends IService<User> {

    /*
     * @description: 通过用户名+密码登录
     * @author: fengxin
     * @date: 2023/4/20 10:39
     * @param: [uservo]
     * @return: com.improve.shell.util.Result
     **/
    Result loginByUser(UserVO uservo);

    /*
     * 签发token
     * @param: [uservo]
     * @return: UserVO（含token）
     **/
    Result login(UserVO uservo);

    /*
     * 注册用户名
     * @param: [uservo]
     * @return:
     **/
    Result register(UserVO uservo);
}
