package org.example.job.api;


import org.example.job.pojo.model.dto.AuthDTO;
import org.example.job.pojo.model.dto.UserDTO;
import org.example.job.pojo.model.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author 85217
 * 对外暴露的接口！
 */
@RequestMapping("/user")
public interface UserControllerApi {
    /**
     * 用户注册接口
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    Result register(@RequestBody UserDTO userDTO);

    /**
     * 用户认证业务对象
     *
     * @param authDTO
     * @return
     */
    @PostMapping("/auth")
    Result login(@RequestBody AuthDTO authDTO);

}
