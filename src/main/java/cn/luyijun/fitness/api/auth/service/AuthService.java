package cn.luyijun.fitness.api.auth.service;

import cn.luyijun.fitness.api.service.entity.AuthReq;
import cn.luyijun.fitness.api.service.entity.StaffInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Yao on 2016/12/9.
 */
@Service
public class AuthService {

//    @Autowired
//    private AuthorizationMapper authorizationMapper;

    public StaffInfo getStaffInfo(AuthReq authReq){
//        StaffInfo staffInfo = this.authorizationMapper.getStaffInfo(authReq);
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setStaffId(1);
        staffInfo.setCrtName("ptd");
        staffInfo.setStaffMobile("18912345678");
        staffInfo.setLastLoginTime(new Date());
        return staffInfo;
    }

//    @WritingDataSource
//    public void saveLoginLog(InternalLoginLog log){
//        authorizationMapper.saveLoginLog(log);
//    }

//    public StaffInfo getStaffInfoByOwnerId(String ownerId) throws CustomErrorException {
//        StaffInfo staffInfo = this.authorizationMapper.getStaffInfoByOwnerId(ownerId);
//        if (staffInfo == null) {
//            throw new CustomErrorException(ErrorCodes.AUTH_LOGIN_ERROR);
//        }
//        return staffInfo;
//    }

//    @WritingDataSource
//    public int updateLastLoginTime(StaffInfo staffInfo) {
//        return this.authorizationMapper.updateLastLoginTime(staffInfo);
//    }

}
