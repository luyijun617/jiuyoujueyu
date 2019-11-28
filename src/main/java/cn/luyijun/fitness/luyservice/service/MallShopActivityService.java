package cn.luyijun.fitness.luyservice.service;

import cn.luyijun.fitness.entity.MallShopActivity;

import java.io.FileNotFoundException;
import java.util.List;

public interface MallShopActivityService {

    List<MallShopActivity> getMallShopActivityList() throws FileNotFoundException;
}
