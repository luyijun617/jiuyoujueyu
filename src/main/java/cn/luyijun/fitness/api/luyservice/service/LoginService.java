package cn.luyijun.fitness.api.luyservice.service;

import cn.luyijun.fitness.api.luyservice.entity.UserInfo;

public interface LoginService {

    void insert(UserInfo userInfo);

    UserInfo query(UserInfo userInfo);

}
