package com.improve.shell.controller;

import com.improve.shell.handler.UserThreadLocal;
import com.improve.shell.pojo.po.ChatRecord;
import com.improve.shell.pojo.vo.UserVO;
import com.improve.shell.service.ChatRecordService;
import com.improve.shell.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-24  20:58
 * @Description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/record")
public class ChatController {

    @Autowired
    private ChatRecordService chatRecordService;


    @GetMapping("/getChatRecord")
    public Result getChatRecord(){
        // 1.拿到当前用户
        UserVO userVO = UserThreadLocal.get();
        // 2.通过当前用户id拿到关于这个用户的所有聊天记录
        HashMap<Long, List<ChatRecord>> chatRecord = chatRecordService.getChatRecord(userVO.getId());
        return Result.success("获取和当前用户相关的所有聊天记录",chatRecord);
    }


}