package cn.luyijun.fitness.enums;

import cn.luyijun.fitness.entity.response.RespResult;
import org.apache.log4j.Logger;


public class BusinessException extends RuntimeException {

    private static Logger logger = Logger.getLogger(BusinessException.class);

    private ErrorCodes errorCodes;

    public BusinessException(ErrorCodes errorCode) {
        this.errorCodes = errorCode;
    }

    public BusinessException(RespResult respResult) {
        respResult.setRespCode(this.errorCodes.getErrorCode());
        respResult.setRespMsg(this.errorCodes.getErrorMsg());
    }

    public BusinessException(RespResult respResult,ErrorCodes errorCode) {
        respResult.setRespCode(errorCode.getErrorCode());
        respResult.setRespMsg(errorCode.getErrorMsg());
    }

    public static void printErrorCode(RespResult respResult, ErrorCodes errorCode, Exception e) {
        respResult.setRespCode(errorCode.getErrorCode());
        respResult.setRespMsg(errorCode.getErrorMsg());
    }

}
