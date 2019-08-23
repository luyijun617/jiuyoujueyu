package cn.luyijun.fitness.service;

import cn.luyijun.fitness.entity.UserInfo;
import cn.luyijun.fitness.dao.LuyUserLoginDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LuyUserLoginServiceImpl implements LuyUserLoginService {

    @Resource
    private LuyUserLoginDao luyUserLoginDao;

    @Override
    public void insert(UserInfo userInfo) {
        luyUserLoginDao.insert(userInfo);
    }

    @Override
    public UserInfo query(UserInfo userInfo) {
        return null;
    }
}
