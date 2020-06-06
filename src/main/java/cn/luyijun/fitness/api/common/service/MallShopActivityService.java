package cn.luyijun.fitness.api.common.service;

import cn.luyijun.fitness.api.common.entity.MallShopActivity;

import java.io.FileNotFoundException;
import java.util.List;

public interface MallShopActivityService {

    List<MallShopActivity> getMallShopActivityList() throws FileNotFoundException;
}
