package cn.luyijun.fitness.luyservice.controller;

import cn.luyijun.fitness.annotation.RequestLog;
import cn.luyijun.fitness.entity.UserInfo;
import cn.luyijun.fitness.luyservice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@Controller
public class IndexController {

    @Resource
    LoginService loginService;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String index(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("string","12345");
        redisTemplate.expire("string",100, TimeUnit.SECONDS);

        ListOperations listOperations = redisTemplate.opsForList();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("list-1");
        strings.add("list-2");
        strings.add("list-3");
        strings.add("list-4");
        listOperations.leftPush("list",strings);
        List list = listOperations.range("list", 1, 2);
        System.out.println("listOperations.range: "+list.get(0));
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset","zsetvalue",1);
        return "index";
    }

    @RequestMapping("/console")
    @RequestLog(value = "value",name = "luyijun",age = 17)
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
