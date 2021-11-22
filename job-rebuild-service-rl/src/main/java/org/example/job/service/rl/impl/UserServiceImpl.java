package org.example.job.service.rl.impl;


import org.apache.dubbo.config.annotation.Service;
import org.example.job.dal.UserRepository;
import org.example.job.dal.entity.UserEntity;
import org.example.job.interfaces.cache.Cache;
import org.example.job.interfaces.service.UserService;
import org.example.job.mail.MailUtil;
import org.example.job.pojo.constans.RedisConstants;
import org.example.job.pojo.constans.UserConstants;
import org.example.job.pojo.exception.BusinessException;
import org.example.job.pojo.model.dto.AuthDTO;
import org.example.job.pojo.model.dto.UserDTO;
import org.example.job.pojo.model.enums.ResultCode;
import org.example.job.pojo.model.result.Result;
import org.example.job.service.util.ValidateCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;
import java.util.Optional;


/**
 * @author 85217
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cache cache;
    @Autowired
    private MailUtil mailUtil;
    // 一般是公司企业邮箱！
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Result register(UserDTO userDTO) {
        Optional<UserDTO> optional = Optional.ofNullable(userDTO);
        // 1.校验用户输入参数是否为null,如果为null抛出业务异常.
        if (!optional.isPresent()) {
            throw new BusinessException(ResultCode.PARAM_INVALID);
        }
        UserDTO dto = optional.get();

        String username = dto.getUsername();

        // 2.判断用户名是否重复: 使用redis的set 集合判断.如果重复抛出业务异常.
        // exist true 用户名在set集合中不存在可以注册，  false 用户名在set集合中存在，不可以可以注册
        boolean exist = cache.isExist(RedisConstants.USER_REGISTER_USERNAME_UNIQUE_KEY, username);
        if (!exist) {
            throw new BusinessException(ResultCode.USERNMAE_EXIST);
        }
        //3.业务对象转成实体对象

        UserEntity userEntity = new UserEntity();
        //  可以把 dto中的同名属性拷贝到 userEntity中
        BeanUtils.copyProperties(dto, userEntity);
        userEntity.setCreateTime(new Date());
        userEntity.setUpdateTime(new Date());
        // 0 表示未激活
        userEntity.setIsActivate(UserConstants.USER_REGISTER_IS_NOT_ACTIVATE_STATUS);

        // 4.保存用户到数据库 jpa
        userRepository.save(userEntity);

        // 5. 生成激活码
        String code = ValidateCode.generateCode();

        // 6. 激活码存入Redis 并且设置有效期 10分钟

        cache.set(RedisConstants.USER_REGISTER_ACTIVATE_CODE_KEY + ":" + username, code, 60 * 10);

        SimpleMailMessage message = createMessage(userEntity.getEmail(), code);
        // 7.发送激活邮件
        mailUtil.sendMail(message);

        return Result.ok(ResultCode.SUCCESS_REGISTER);
    }

    /**
     * 生成邮件
     *
     * @param toEmail
     * @param code
     * @return
     */
    private SimpleMailMessage createMessage(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(from);
        message.setSubject("[注册激活邮件]");
        message.setText("激活码 :" + code);
        return message;
    }

    @Override
    public Result login(AuthDTO authDTO) {
        Optional<AuthDTO> optional = Optional.ofNullable(authDTO);

        //1.校验用户输入参数是否为null,如果为null抛出业务异常.
        if (!optional.isPresent()) {
            throw new BusinessException(ResultCode.PARAM_INVALID);
        }
        AuthDTO dto = optional.get();
        String username = dto.getUsername();
        String password = dto.getPassword();

        //2.判断用户名是否正确（使用redis的set 集合判断.如果重复即为存在，不重复则不存在，抛出异常用户名或密码错误）
        //exist 为false即为用户名正确，反之true为用户名不存在，抛出业务异常用户名或者密码错误
        boolean exist = cache.isExist(RedisConstants.USER_REGISTER_USERNAME_UNIQUE_KEY, username);
        if (exist) {
            throw new BusinessException(ResultCode.USERNAME_PASSWORD_ERROR);
        }

        //3.存在————根据用户名查找对象，获取密码进行比对，如果不一样抛出异常用户名或密码错误
        UserEntity userEntity = userRepository.findUserEntityByUsername(username);
        if (!userEntity.getPassword().equals(password)) {
            throw new BusinessException(ResultCode.USERNAME_PASSWORD_ERROR);
        }

        //4.判断是否激活用户，如果未激活提示用户激活，如果已经激活，则提示登录成功
        Integer isActivate = userEntity.getIsActivate();

        //如果激活状态是0，提示用户未激活
        if (isActivate == UserConstants.USER_REGISTER_IS_NOT_ACTIVATE_STATUS) {

            //更改激活状态（用户激活操作略，为了方便放在这里，实际开发并不是）
            userEntity.setIsActivate(UserConstants.USER_REGISTER_IS_ACTIVATE_STATUS);

            // 5.保存到数据库 jpa
            userRepository.save(userEntity);
            throw new BusinessException(ResultCode.USERNAME_IS_NOT_ACTIVATE);
        }


        return Result.ok(ResultCode.SUCCESS_LOGIN);
    }
}
