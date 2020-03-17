package cn.luyijun.fitness.api.luyservice.controller;

import cn.luyijun.fitness.api.luyservice.annotation.RequestLog;
import cn.luyijun.fitness.api.luyservice.entity.UserInfo;
import cn.luyijun.fitness.api.luyservice.service.LoginService;
import cn.luyijun.fitness.api.test.thread.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Resource(name = "AbstractFruit")
    Fruit fruit;

    @Resource
    Apple apple;

    @Resource
    Banana banana;

    @RequestMapping("/activityAdd")
    @ResponseBody
    public String helloWorld() {
        long beginTime = System.currentTimeMillis();
        File file = new File("C:\\Users\\jun\\Desktop\\from\\1578018330192.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                TestThreadPool.executorService.submit(new TestRunnable(line,fruit));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("===========================耗时：" + (endTime - beginTime));
        return "activityAdd";
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public String index(@PathVariable Integer id) {
        String f = fruit.getFruit(id);
        System.out.println(f);
        return f;
//        return "index";
    }

    @ResponseBody
    @RequestMapping("/redis")
    public String redis(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("string", "12345");
        redisTemplate.expire("string", 100, TimeUnit.SECONDS);
        ListOperations listOperations = redisTemplate.opsForList();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("list-1");
        strings.add("list-2");
        strings.add("list-3");
        strings.add("list-4");
        listOperations.leftPush("list", strings);
        List list = listOperations.range("list", 1, 2);
        System.out.println("listOperations.range: " + list.get(0));
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset", "zsetvalue", 1);
        return "redis";
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
