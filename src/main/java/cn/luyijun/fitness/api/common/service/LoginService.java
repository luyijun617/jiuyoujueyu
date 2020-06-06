package cn.luyijun.fitness.api.common.service;

import cn.luyijun.fitness.api.common.entity.UserInfo;

public interface LoginService {

    void insert(UserInfo userInfo);

    UserInfo query(UserInfo userInfo);

}
