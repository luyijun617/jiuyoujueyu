package cn.luyijun.fitness.api.common.service.Impl;

import cn.luyijun.fitness.api.common.entity.UserInfo;
import cn.luyijun.fitness.api.common.dao.LoginDao;
import cn.luyijun.fitness.api.common.service.LoginService;
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
