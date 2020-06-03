package cn.luyijun.fitness.api.service.service;

import cn.luyijun.fitness.api.service.entity.UserInfo;

public interface LoginService {

    void insert(UserInfo userInfo);

    UserInfo query(UserInfo userInfo);

}
