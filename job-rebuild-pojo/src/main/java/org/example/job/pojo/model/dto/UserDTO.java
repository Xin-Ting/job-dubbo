package org.example.job.pojo.model.dto;


import java.io.Serializable;

/**
 * @author 85217
 */
public class UserDTO implements Serializable {
    /**
     * 用户名
     */
     private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
