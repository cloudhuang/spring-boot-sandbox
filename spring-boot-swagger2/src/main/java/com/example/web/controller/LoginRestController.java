package com.example.web.controller;

import com.example.web.model.LoginParam;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "登录请求接口类", tags = "登录", description = "用户请求登录获取Token")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@RestController("/api/auth")
public class LoginRestController {
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiResponses( {
            @ApiResponse( code = 200, message = "登陆成功" ),
            @ApiResponse( code = 403, message = "用户名密码错误" )
    } )
    @PostMapping("/login")
    public ResponseEntity<?> login(@ApiParam(value = "登录参数") @RequestBody LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();

        if ("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }
    }
}
