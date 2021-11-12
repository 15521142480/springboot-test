package com.lai.controller.mobile;

import com.lai.entity.sys.UserBean;
import com.lai.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: APP用户模板
 * @Author: laixues
 * @Date: 2021/1/30
 */
@RestController("mobileUserController")
@RequestMapping("mobile/user")
@Api(tags = "app用户模块")
public class MobileUserController {

    @Autowired
    UserService userService;


    @PostMapping("/userInfo")
    public Object userInfo(){

        List<UserBean> userList = userService.getUserList();

        StringBuilder str = new StringBuilder("");
        for (UserBean user : userList) {
            str.append(user.getNickName()).append("-");
        }

        return "用户名字列表：" + str.toString();
    }

}
