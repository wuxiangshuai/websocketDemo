package com.wxs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoController {

    @GetMapping("/test")
    public String index() {
        return "test";
    }

    @GetMapping("/test2")
    public String index2() {
        return "test2";
    }

}
