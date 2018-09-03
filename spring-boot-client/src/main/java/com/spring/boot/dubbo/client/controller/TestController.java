package com.spring.boot.dubbo.client.controller;

import com.spring.boot.dubbo.client.service.TestConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestConsumerService cityDubboConsumerService;

    @GetMapping("/test")
    public String test() {
        return cityDubboConsumerService.printCity().toString();
    }
}
