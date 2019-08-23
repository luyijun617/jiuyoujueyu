package cn.luyijun.fitness.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>信用卡查询请求参数</p>
 *
 * @author changzhaoyu
 * @version 1.0: CreditCardSmsRequest.java, v0.1 2019-05-13 20:21 PM changzhaoyu Exp $
 */
@Data
public class CreditCardQueryRequest implements Serializable {

    private static final long serialVersionUID = -434648435689423402L;

    /**
     * 银行编码
     */
    private String            bankCode;
    /**
     * 证件号码
     */
    private String            idCard;
    /**
     * 手机号码
     */
    private String            mobileNum;
    /**
     * 姓名
     */
    private String            realName;
    /**
     *
     */
    private String              userId;

    /**
     * 图片验证码
     */
    private String              imgVerCode;

    /**
     * 短信验证码
     */
    private String              smsVerCode;


}
