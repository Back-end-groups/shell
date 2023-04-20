package com.improve.shell.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-19  18:46
 * @Description: 对应聊天记录表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecord {

    // 主键id：用户id+时间+对象id
    private String id;

    // 发送者id
    private String user01Id;

    // 接收者id
    private String user02Id;

    // 消息类型
    private String messageType;

    // 消息内容
    private String content;

    // 发送时间
    private String sendTime;
}
