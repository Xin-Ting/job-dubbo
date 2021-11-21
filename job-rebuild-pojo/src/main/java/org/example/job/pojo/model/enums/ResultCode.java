package org.example.job.pojo.model.enums;


public enum ResultCode {
    // 定义通用成功和失败的枚举
    SUCCESS(20000, "操作成功!"),
    FAIL(30000, "操作失败!"),

    // 业务枚举
    PARAM_INVALID(40000, "参数无效!"),
    USERNMAE_EXIST(50000, "用户名存在!"),
    USERNAME_PASSWORD_ERROR(50001,"用户名或密码错误！"),
    USERNAME_IS_NOT_ACTIVATE(50003,"用户未激活！"),
    SUCCESS_REGISTER(20001, "用户注册成功!"),
    SUCCESS_LOGIN(20002,"用户登录成功！")
    // 可以增加.....
    ;
    private int code;
    private String value;

    private ResultCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
