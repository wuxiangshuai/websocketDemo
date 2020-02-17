package com.wxs.demo.service;

import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocketTest/{userId}")
@Component
public class WebSocketDemo {

    private static String userId;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketDemo> user = new CopyOnWriteArraySet<WebSocketDemo>();

    //连接时执行
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        this.userId = userId;
        this.session = session;
        user.add(this);
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        System.out.println(this.session.getId()+" close...");
        user.remove(this);
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.printf("收到用户 ? 的消息 ?",this.userId,message);
//        session.getBasicRemote().sendText("收到 "+this.userId+" 的消息 :" + message); //回复用户
        //群发消息
        for (WebSocketDemo myWebSocket : user) {
            myWebSocket.session.getBasicRemote().sendText(session.getId()+"说："+message);
        }
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println(this.session.getId()+" error...");
        error.printStackTrace();
    }

}
