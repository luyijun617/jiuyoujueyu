package cn.luyijun.fitness.luyservice.service;

import cn.luyijun.fitness.entity.UserInfo;

public interface LoginService {

    void insert(UserInfo userInfo);

    UserInfo query(UserInfo userInfo);

}
