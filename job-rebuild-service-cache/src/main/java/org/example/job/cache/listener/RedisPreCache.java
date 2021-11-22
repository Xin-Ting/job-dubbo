package org.example.job.cache.listener;


import org.example.job.dal.UserRepository;
import org.example.job.dal.entity.UserEntity;
import org.example.job.interfaces.cache.Cache;
import org.example.job.pojo.constans.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 85217
 *
 *
 *    缓存预热功能！ 【严重功能缺陷：缓存不同步】
 *
 *    spring task 定时任务：【Timer  定时任务调度】
 *
 *
 */
@Component
public class RedisPreCache implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cache cache;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        preCache();
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void preCache() {

        System.out.println("............||||.........");

        List<String> usernameList = new ArrayList<String>();
        // 从数据库中读取所有用户名称
        Iterable<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            String username = user.getUsername();
            usernameList.add(username);
        }
        // 把用户名称放入缓存
        cache.add(RedisConstants.USER_REGISTER_USERNAME_UNIQUE_KEY,usernameList);
    }
}
