package cn.luyijun.fitness.api.luyservice.service;

import cn.luyijun.fitness.api.luyservice.entity.MallShopActivity;

import java.io.FileNotFoundException;
import java.util.List;

public interface MallShopActivityService {

    List<MallShopActivity> getMallShopActivityList() throws FileNotFoundException;
}
