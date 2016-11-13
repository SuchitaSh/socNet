package com.socnet.web.restcontroller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 13.11.2016.
 */
@RestController
public class MessageController {

    @PostMapping(value = "/send-message", produces= MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendMessage (@RequestParam("text") String message) {
        System.out.println("Message is :" + message);
    }
}
