package com.demo.forest.config.webSocket;

import com.demo.forest.util.ResponseInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PushTest {

    @RequestMapping("/push")
    public ResponseInfo pushMessage(HttpServletResponse response) {
        try {
            WebSocketController.sendMessage("这是来自服务器的消息哦！", "10");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseInfo.SUCCESS("消息推送测试~~~~");
    }
}
