package cn.luyijun.fitness.api.luyservice.service.Impl;

import cn.luyijun.fitness.api.luyservice.entity.UserInfo;
import cn.luyijun.fitness.api.luyservice.dao.LoginDao;
import cn.luyijun.fitness.api.luyservice.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDao loginDao;

    @Override
    public void insert(UserInfo userInfo) {
        loginDao.insert(userInfo);
    }

    @Override
    public UserInfo query(UserInfo userInfo) {
        return null;
    }
}
