package cn.luyijun.fitness.api.service.controller;

import cn.luyijun.fitness.api.service.entity.response.RespResult;
import cn.luyijun.fitness.api.service.service.MallShopActivityService;
import cn.luyijun.fitness.api.test.template.Apple;
import cn.luyijun.fitness.api.test.thread.TestRunnable;
import cn.luyijun.fitness.api.test.thread.TestThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping
public class MallShopActivityController {

    @Resource
    private MallShopActivityService mallShopActivityService;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/getMallShopActivityList")
    @ResponseBody
    public RespResult getMallShopActivityList() {
        RespResult respResult = new RespResult();
        try {
            respResult.setData(mallShopActivityService.getMallShopActivityList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return respResult;
    }

    public RespResult downLoad() {
        RespResult respResult = new RespResult();
        try {
            respResult.setData(mallShopActivityService.getMallShopActivityList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return respResult;
    }

    @RequestMapping("/activityAdd")
    @ResponseBody
    public String helloWorld() {
        long beginTime = System.currentTimeMillis();
        File file = new File("C:\\Users\\jun\\Desktop\\from\\1578018330192.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                TestThreadPool.executorService.submit(new TestRunnable(line, new Apple()));
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


    @ResponseBody
    @RequestMapping("/redis")
    public String redis() {
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
}
