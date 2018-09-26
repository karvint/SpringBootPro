package com.ty.show.ws.service;

import com.ty.show.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/ws")
@Component
@Slf4j
public class WebSorcketService {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSorcketService> webSocketSet = new CopyOnWriteArraySet<WebSorcketService>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private File file;//文件

    private int line = 0;//e

    private FileReader f;

    private BufferedReader reader;

    private FileOutputStream fo;

    private PrintStream ps;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) throws IOException {
        file = new File("out.text");
        fo = new FileOutputStream(file);
        ps = new PrintStream(fo);
        line= 1;
        System.setOut(ps);
        System.setErr(ps);
        f = new FileReader(file);
        reader = new BufferedReader(f);
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage(Constants.CURRENT_WANGING_NUMBER.toString());
        } catch (IOException e) {
            log.error("IO异常",e.getStackTrace());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        this.closeStream();
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message) throws IOException {
        String s = reader.readLine();
        String readstr= "";
        while(s!=null){
            readstr += s+"\r\n";
            s = reader.readLine();
        }
        //群发消息
        if(readstr!=null&&!"".equals(readstr)){
            for (WebSorcketService item : webSocketSet) {
                try {
                    item.sendMessage(readstr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        line++;
    }

    /**
     * 发生错误时调用
     */
     @OnError
     public void onError(Session session, Throwable error) throws IOException {
         log.error("websocket发生错误"+error.getStackTrace());
         this.closeStream();
     }

     public void closeStream() throws IOException {
         reader.close();
         f.close();
         ps.close();
         fo.close();
     }


     public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
//     this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {
        for (WebSorcketService item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSorcketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSorcketService.onlineCount--;
    }
}
