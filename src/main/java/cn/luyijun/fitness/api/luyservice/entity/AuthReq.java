package cn.luyijun.fitness.api.luyservice.entity;

/**
 * @description
 * @author lyj
 * @date 2019/11/26 16:05
 */
public class AuthReq {
    private String mobile;
    private String captchaVerifyCode;
    private String mobileVerifyCode;
    private Integer type;
    private String ownerId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptchaVerifyCode() {
        return captchaVerifyCode;
    }

    public void setCaptchaVerifyCode(String captchaVerifyCode) {
        this.captchaVerifyCode = captchaVerifyCode;
    }

    public String getMobileVerifyCode() {
        return mobileVerifyCode;
    }

    public void setMobileVerifyCode(String mobileVerifyCode) {
        this.mobileVerifyCode = mobileVerifyCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
