package com.example.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "登录接口参数")
@Data
public class LoginParam {
    @ApiModelProperty(value = "用户名", name = "username", example = "admin", required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;
}
