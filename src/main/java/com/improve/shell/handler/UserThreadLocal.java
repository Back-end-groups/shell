package com.improve.shell.handler;

import com.improve.shell.pojo.vo.UserVO;

public class UserThreadLocal {


    private static final ThreadLocal<UserVO> LOCAL = new ThreadLocal<>();

    public static void put(UserVO uservo){
        LOCAL.set(uservo);
    }

    public static UserVO get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
