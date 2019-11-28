package cn.luyijun.fitness.luyservice.controller;

import cn.luyijun.fitness.entity.UserInfo;
import cn.luyijun.fitness.luyservice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Slf4j
@Controller
public class IndexController {

    @Resource
    LoginService loginService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/console")
    public String console(){
        return "console";
    }

    @RequestMapping("/register")
    public String register(){
        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId("1");
        userInfo.setUserName("admin");
        userInfo.setPassword("123456");
        loginService.insert(userInfo);

        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/activityAdd")
    public String helloWorld(){
        return "activityAdd";
    }

}
