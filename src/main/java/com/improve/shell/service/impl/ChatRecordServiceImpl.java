package com.improve.shell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.ChatRecordMapper;
import com.improve.shell.pojo.po.ChatRecord;
import com.improve.shell.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-24  21:14
 * @Description: TODO
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements ChatRecordService {

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Override
    public HashMap<Long, List<ChatRecord>> getChatRecord(Long id) {
        // 最终返回给前端的数据（进行打包）
        HashMap<Long, List<ChatRecord>> result = new HashMap<>();
        // 1.通过用户id获取该用户所有的聊天记录
        List<ChatRecord> recordDb = chatRecordMapper.getChatRecordById(id);
        // 2.循环遍历每一条聊天记录
        for (ChatRecord chatRecord : recordDb) {
            // 2.1 得到和当前用户聊天用户的id，记录为toId（toId和id必然不相等）
            Long senderId = chatRecord.getSenderId();
            Long receiverId = chatRecord.getReceiverId();
            Long toId = Objects.equals(senderId, id) ? receiverId : senderId;
            // 2.2 以toId作为key，用户id和用户toId的聊天记录为value
            // 2.2.1 如果该key
            if (!result.containsKey(toId)) {
                // 不存在：
                // 创建一个list集合存储用户id和用户toId的聊天记录
                List<ChatRecord> storeChatRecord = new ArrayList<>();
                // 将当前这条记录存入
                storeChatRecord.add(chatRecord);
                result.put(toId,storeChatRecord);
            }else {
                // 存在：
                // 将当前这条记录存入
                result.get(toId).add(chatRecord);
            }
        }
        return result;
    }
}
