package com.example.demo;

import com.example.annotation.Autowired;
import com.example.annotation.Controller;
import com.example.annotation.HttpMethod;
import com.example.annotation.RequestMapper;
import com.example.been.ResponseEntity;
import com.example.been.ShiinaContext;
@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @RequestMapper(value = "/hello")
    public ResponseEntity hello(ShiinaContext context) {
        return new ResponseEntity(10000, "hello " + context.getParam("name"));
    }

    @RequestMapper(value = "/world")
    public ResponseEntity world(ShiinaContext context) {
        return new ResponseEntity(10000, "hello world!");
    }

    @RequestMapper(value = "/world", method = HttpMethod.POST)
    public ResponseEntity postWorld(ShiinaContext context) {
        return mainService.yoyoyo();
    }
}
