package cn.luyijun.fitness.api.service.service;

import cn.luyijun.fitness.api.service.entity.MallShopActivity;

import java.io.FileNotFoundException;
import java.util.List;

public interface MallShopActivityService {

    List<MallShopActivity> getMallShopActivityList() throws FileNotFoundException;
}
