package cn.luyijun.fitness.api.luyservice.controller;

import cn.luyijun.fitness.api.luyservice.entity.response.RespResult;
import cn.luyijun.fitness.api.luyservice.service.MallShopActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;

@Controller
@RequestMapping
public class MallShopActivityController {

    @Autowired
    MallShopActivityService mallShopActivityService;

    @RequestMapping("/getMallShopActivityList")
    @ResponseBody
    public RespResult getMallShopActivityList(){
        RespResult respResult = new RespResult();
        try {
            respResult.setData(mallShopActivityService.getMallShopActivityList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return respResult;
    }

    public RespResult downLoad(){
        RespResult respResult = new RespResult();
        try {
            respResult.setData(mallShopActivityService.getMallShopActivityList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return respResult;
    }
}
