package cn.luyijun.fitness.api.service.controller;

import cn.luyijun.fitness.api.service.annotation.RequestLog;
import cn.luyijun.fitness.api.service.entity.UserInfo;
import cn.luyijun.fitness.api.service.service.LoginService;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;


@Slf4j
@Controller
public class IndexController {

    @Resource
    LoginService loginService;


    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }


    @RequestMapping("/console")
    @RequestLog(value = "value", name = "luyijun", age = 17)
    public String console() {
        return "console";
    }

    @RequestMapping("/register")
    public String register() {
        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId("1");
        userInfo.setUserName("admin");
        userInfo.setPassword("123456");
        loginService.insert(userInfo);
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
