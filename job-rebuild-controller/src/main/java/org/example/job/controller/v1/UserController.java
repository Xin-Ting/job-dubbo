package org.example.job.controller.v1;


import org.example.job.api.UserControllerApi;
import org.example.job.pojo.model.dto.AuthDTO;
import org.example.job.pojo.model.dto.UserDTO;
import org.example.job.pojo.model.result.Result;
import org.example.job.service.rl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author byZhao
 * @Modify 2021-11-16 22:34
 * @Description <p> </p>
 * <p> Talk is cheap . Show me the code! </p>
 */
@RestController
public class UserController implements UserControllerApi {

    @Autowired
    private UserService userService;


    @Override
    public Result register(UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @Override
    public Result login(AuthDTO authDTO) {
        return userService.login(authDTO);
    }
}
