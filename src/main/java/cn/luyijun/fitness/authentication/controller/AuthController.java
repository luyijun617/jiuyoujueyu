package cn.luyijun.fitness.authentication.controller;

import cn.luyijun.fitness.authentication.service.AuthService;
import cn.luyijun.fitness.configuration.HotReLoadConfig;
import cn.luyijun.fitness.entity.AuthReq;
import cn.luyijun.fitness.entity.StaffInfo;
import cn.luyijun.fitness.entity.response.RespResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @RequestMapping(value = "token/{platform}", method = RequestMethod.POST)
    public RespResult token(@RequestBody AuthReq authReq, @PathVariable String platform, HttpServletRequest request, HttpServletResponse response) {
        RespResult<StaffInfo> restResult = new RespResult<>();
        try {
            // check params
//            if (StringUtils.isEmpty(authReq.getMobile()))
//                throw new CustomErrorException(ErrorCodes.AUTH_LOGIN_MOBILE_PARAM_REQUIRED);
//            if (StringUtils.isEmpty(authReq.getCaptchaVerifyCode()))
//                throw new CustomErrorException(ErrorCodes.AUTH_LOGIN_CAPTCHAVERIFYCODE_PARAM_REQUIRED);
//            if (StringUtils.isEmpty(authReq.getMobileVerifyCode()))
//                throw new CustomErrorException(ErrorCodes.AUTH_LOGIN_PASSWORD_OR_MOBILEVERIFYCODE_PARAM_REQUIRED);


            // get staff info
            StaffInfo staffInfo = authService.getStaffInfo(authReq);

            //check mobile verify code
            if (StringUtils.isNotEmpty(authReq.getMobileVerifyCode())) {
//                String key = LOGIN_SMS_KEY + authReq.getMobile();
//                String mobileCheckCode = this.cache.get(key, SerializationType.JSON);
                String mobileCheckCode = null;
                if (staffInfo.getPaasStatus() == 0) {
                    if (!authReq.getMobileVerifyCode().equals(HotReLoadConfig.map.get("mobileCheckCode"))) {
                        throw new RuntimeException();
                    }
                } else {
                    if (mobileCheckCode == null) {
                        if (!authReq.getMobileVerifyCode().equals(HotReLoadConfig.map.get("mobileCheckCode"))) {
                            throw new RuntimeException();
                        }
                    } else {
                        if (!authReq.getMobileVerifyCode().equals(mobileCheckCode)) {
//                            throw new CustomErrorException(ErrorCodes.MOBILE_VERIFY_CODE_ERROR);
                        }
//                        this.cache.delete(key);
                    }
                }
            } else {
//                throw new CustomErrorException(ErrorCodes.MOBILE_VERIFY_CODE_ERROR);
            }


            //last login time
//            new Thread(new UpdateLoginTime(this.authorizationService, new StaffInfo(staffInfo.getStaffId(), HttpTookit.getRealIpAddr(request)))).start();

//            this.loginResult(restResult, staffInfo, platform, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restResult;
    }

}
