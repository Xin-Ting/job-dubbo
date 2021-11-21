package org.example.job.pojo.exception;


import org.example.job.pojo.model.enums.ResultCode;

/**
 * @author 85217
 */
public class BusinessException extends BaseException{

    private ResultCode resultCode;

    public BusinessException() {
    }

    public BusinessException(ResultCode resultCode){
        this.resultCode =resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
