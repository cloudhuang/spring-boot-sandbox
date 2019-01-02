package com.example.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @ApiOperation(value = "欢迎接口", notes = "返回欢迎信息")
    @RequestMapping(path = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "Welcome!";
    }
}