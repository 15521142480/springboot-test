package com.lai.controller;

import com.lai.entity.sys.UserBean;
import com.lai.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: PC用户模板
 * @Author: laixues
 * @Date: 2020/11/30
 */
@RestController("userController")
@RequestMapping("pc/user")
@Api(tags = "pc用户模块")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "springboot启动!!!";
    }



}
