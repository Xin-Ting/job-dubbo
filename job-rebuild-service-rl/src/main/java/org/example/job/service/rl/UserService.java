package org.example.job.service.rl;


import org.example.job.pojo.model.dto.AuthDTO;
import org.example.job.pojo.model.dto.UserDTO;
import org.example.job.pojo.model.result.Result;

/**
 * @author 85217
 */
public interface UserService {
    /**
     * 用户注册业务
     *
     * @param userDTO 业务对象
     * @return
     */
    Result register(UserDTO userDTO);

    /**
     * 用户认证业务对象
     *
     * @param authDTO
     * @return
     */
    Result login(AuthDTO authDTO);

}
