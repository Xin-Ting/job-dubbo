package org.example.job.pojo.model.result;

import org.example.job.pojo.model.enums.ResultCode;

import java.io.Serializable;


/**
 * @author 85217
 */
public class Result implements Serializable {

    private int code;
    private String msg;

    /**
     *  提供一个构造，参数使用枚举：好处更加通用，封装！
     * @param resultCode  结果枚举类
     */
    public  Result (ResultCode resultCode){
        this.code = resultCode.getCode();
        this.msg =resultCode.getValue();
    }

    /**
     *  静态方法：通用成功！
     * @return
     */
    public static Result ok(){
        return new Result(ResultCode.SUCCESS);
    }

    /**
     *   静态方法 ： 根据具体业务返回成功信息！
     * @param resultCode
     * @return
     */
    public static Result ok(ResultCode resultCode){
        return new Result(resultCode);
    }
    /**
     *  静态方法：通用失败！
     * @return
     */
    public static Result fail(){
        return new Result(ResultCode.FAIL);
    }
    /**
     *   静态方法 ： 根据具体业务返回失败信息！
     * @param resultCode
     * @return
     */
    public static Result fail(ResultCode resultCode){
        return new Result(resultCode);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
