package org.example.job.pojo.exception;


import org.example.job.pojo.model.enums.ResultCode;

/**
 * @author 85217
 */
public class SystemException  extends BaseException{
    private ResultCode resultCode;

    public SystemException(){

    }

    public SystemException(ResultCode resultCode){
        this.resultCode =resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
