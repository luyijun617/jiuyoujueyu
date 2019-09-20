package cn.luyijun.fitness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloWorld {

    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "activityAdd";
    }

    public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}
