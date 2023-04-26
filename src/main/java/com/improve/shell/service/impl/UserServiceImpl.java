package com.improve.shell.service.impl;

import com.alibaba.fastjson2.JSON;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.UserMapper;
import com.improve.shell.pojo.po.User;
import com.improve.shell.pojo.vo.UserVO;
import com.improve.shell.service.UserService;
import com.improve.shell.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-19  22:43
 * @Description: TODO
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /* 注册用户名+并设置密码
     * @description:
     * @author: fengxin
     * @date: 2023/4/20 10:38
     * @param: [uservo]：{username,password}
     * @return: com.improve.shell.util.Result
     **/
    @Override
    public Result loginByUser(UserVO uservo) {
        // 通过用户名获取用户记录
        User userRecord = selectUserByUsername(uservo.getUsername());
        // 判断用户存不存在
        if (null != userRecord){
            //用户存在：
            //将密码进行MD5加密
            uservo.setPassword(MD5Utils.digest(uservo.getPassword()));
            if (Objects.equals(uservo.getPassword(), userRecord.getPassword())){
                //获取uid
                uservo.setId(userRecord.getId());
                return this.login(uservo);
            }else {
                return Result.fail("账号或密码错误！");
            }
        }else {
            //用户不存在：
            return Result.fail("该用户名还未注册！");
        }
    }

    /*
     * (抽取方法)：通过用户名从数据库获取用户记录
     * @param: [uservo]
     * @return: User：数据库中一条用户记录 或 null
     **/
    private User selectUserByUsername(String username) {
        // 1.使用MySQL Plus 的 QueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 2.写入查询条件：username
        queryWrapper.eq("username", username);
        // 3.查询并返回记录
        return userMapper.selectOne(queryWrapper);
    }


    /*
     * 签发token
     * @param: [uservo]
     * @return: com.improve.shell.util.Result
     **/
    @Override
    public Result login(UserVO uservo) {
        String token = JwtUtil.sign(uservo.getId());
        uservo.setToken(token);
        // 需要把token 存入redis，value存为uservo，下次用户访问登录资源时，可以根据token拿到用户的详细信息
        redisTemplate.opsForValue().set(RedisKey.TOKEN + token, JSON.toJSONString(uservo),7, TimeUnit.DAYS);
        return Result.success("登录成功",uservo);
    }

    /* 注册用户名+并设置密码
     * @description:
     * @author: fengxin
     * @date: 2023/4/20 10:38
     * @param: [uservo]：{username,password}
     * @return: com.improve.shell.util.Result
     **/
    @Override
    public Result register(UserVO uservo) {
        // 通过用户名查询数据库有没有已注册的用户记录
        User userRecord = selectUserByUsername(uservo.getUsername());
        // 判断用户存不存在
        if (null != userRecord){
            //用户存在：
            return Result.fail("该用户名已被注册！");
        }
        //用户不存在：
        User user = new User();
        //将密码进行MD5加密
        uservo.setPassword(MD5Utils.digest(uservo.getPassword()));
        // 将uservo的值全部赋值给user：为了用user存入数据库
        BeanUtils.copyProperties(uservo,user);
        // 获取并设置注册时间
        user.setCreateTime(TimeUtil.getNowTime());
        // 向数据库添加新注册的用户
        userMapper.insert(user);
        // 添加成功后通过设置uservo的id属性为数据库的id
        uservo.setId(user.getId());
        return Result.success("注册成功，请登录！");
    }
}
