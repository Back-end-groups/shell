package com.improve.shell.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-20  20:54
 * @Description: 浏览器发送给服务器的 websocket数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    // 发送给指定toId的用户对象
    private Long toId;

    // 发送信息的内容
    private String message;
}
