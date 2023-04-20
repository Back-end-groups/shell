package com.improve.shell.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.improve.shell.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>{

//    //获取uid、nickname、avatar
//    @Select("select uid,nickname,avatar from tbl_user ")
//    List<User> getAllUser();
}

