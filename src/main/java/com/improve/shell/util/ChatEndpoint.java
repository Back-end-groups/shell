package com.improve.shell.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.improve.shell.handler.UserThreadLocal;
import com.improve.shell.pojo.vo.Message;
import com.improve.shell.pojo.vo.UserVO;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: fengxin
 * @CreateTime: 2023-04-20  19:28
 * @Description: websocket实现聊天功能
 */
@Component
@ServerEndpoint("/chat")
public class ChatEndpoint {

    // 用来存储每一个客户端对象对应的 ChatEndpoint 对象
    private static Map<Long, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    // 声明Session对象，通过该对象可以发送消息给指定的用户
    private Session session;

    // 存储当前用户的登录信息
    private UserVO uservo;

    /*
     * @description: 聊天连接建立时被调用：将当前在线用户的id推送给所有的在线客户端
     * @author: fengxin
     * @date: 2023/4/20 19:33
     * @param: [session, config]
     * @return: void
     **/
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 1.将局部session对象赋值给成员session
        this.session = session;
        // 2.将当前用户登录信息赋值给成员uservo
        this.uservo = UserThreadLocal.get();
        // 3.将当前用户对象存储到容器中（通过id标识在线用户）
        onlineUsers.put(uservo.getId(),this);
        // 4.将当前在线用户的id推送给所有的在线客户端
        sendUsers();
    }

    /*
     * @description: 接收到客户端发送的数据被调用：实现用户与用户的消息传递
     * @author: fengxin
     * @date: 2023/4/20 19:33
     * @param: [message, session]
     * @return: void
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            // 1.将局部变量message转换成Message对象
            ObjectMapper objectMapper = new ObjectMapper();
            Message mess = objectMapper.readValue(message, Message.class);
            // 2.获取要将数据发送给的用户的id
            Long toId = mess.getToId();
            // 3.获取消息数据
            String messageData = mess.getMessage();
            // 4.封装成系统发送给用户的消息格式
            String resultMessage = MessageUtils.getMessage(false, uservo.getId(), messageData);
            // 5.服务器向指定（id）客户端发送数据
            // 5.1 获取接收消息用户id对应的ChatEndpoint对象
            ChatEndpoint chatEndpoint = onlineUsers.get(toId);
            // 5.2 通过ChatEndpoint对象给对应在线用户发送系统消息
            chatEndpoint.session.getBasicRemote().sendText(resultMessage);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @description: 连接关闭时被调用：将新的所有在线用户通知给所有在线用户
     * @author: fengxin
     * @date: 2023/4/20 19:34
     * @param: [session]
     * @return: void
     **/
    @OnClose
    public void onClose(Session session) {
        // 1.当前用户下线，则从在线用户中删除该用户
        onlineUsers.remove(uservo.getId());
        // 2.将新的所有在线用户通知给所有在线用户
        sendUsers();
    }

    /*
     * @description: 抽取方法：将当前在线用户的id推送给所有的在线客户端
     * @author: fengxin
     * @date: 2023/4/20 22:43
     * @param: []
     * @return: void
     **/
    private void sendUsers() {
        // 1. 获取所有在线用户的id
        Set<Long> ids = onlineUsers.keySet();
        // 2.封装成系统发送给用户的消息格式
        String resultMessage = MessageUtils.getMessage(true, null, ids);
        // 3.通过遍历所有的在线用户id
        for (Long id : ids) {
            // 获取每个在线用户的id，通过id拿到对应的ChatEndpoint对象
            ChatEndpoint chatEndpoint = onlineUsers.get(id);
            try {
                // 通过ChatEndpoint对象给对应在线用户发送系统消息
                chatEndpoint.session.getBasicRemote().sendText(resultMessage);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
