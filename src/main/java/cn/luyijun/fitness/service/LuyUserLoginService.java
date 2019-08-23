package cn.luyijun.fitness.service;

import cn.luyijun.fitness.entity.UserInfo;

public interface LuyUserLoginService {

    void insert(UserInfo userInfo);

    UserInfo query(UserInfo userInfo);

}
