package com.improve.shell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.HouseWithUserMapper;
import com.improve.shell.pojo.po.HouseWithUser;
import com.improve.shell.service.HouseWithUserService;
import org.springframework.stereotype.Service;

@Service
public class HouseWithUserServiceImp extends ServiceImpl<HouseWithUserMapper, HouseWithUser> implements HouseWithUserService {
}
