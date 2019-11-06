package cn.luyijun.fitness.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DBController {

    @Privilege(value = {"paas_staff_manage","manage"})
    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}
