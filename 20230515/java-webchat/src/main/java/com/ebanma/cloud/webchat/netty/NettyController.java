package com.ebanma.cloud.webchat.netty;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NettyController {

    @GetMapping("/index.html")
    public String chat() {
        return "chat";
    }
    @GetMapping("/")
    public String index() {
        return "chat";
    }
}
