package cn.luyijun.fitness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private  int i = 100;
    public static int j = 100;
//    private int a = 100;
//    public int b = 100;

    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        TestController testController = new TestController();
        System.out.println(testController.i);
        testController.i = 50;
        System.out.println(testController.i);
        System.out.println(i);
        System.out.println(testController.i == i);
//        testController = new TestController();
//        testController.i ++;
//        System.out.println(i);
//        TestController.i--;
//        System.out.println(i);
    }
}
