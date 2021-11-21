package org.example.job.cache.impl;


import org.example.job.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author 85217
 */
@Component
public class RedisCache implements Cache {

    @Autowired
    private StringRedisTemplate client;

    @Override
    public boolean isExist(String setKey, String value) {
        SetOperations<String, String> setOps = client.opsForSet();
        Long rs = setOps.add(setKey, value);
        if (rs == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void set(String key, String value, long time) {
        client.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void add(String key, List<String> values) {
        String[] usernames = new String[values.size()] ;
        for (int i = 0; i < values.size(); i++) {
            usernames[i] =values.get(i).toString();
        }
        System.out.println(Arrays.toString(usernames));
        client.opsForSet().add(key,usernames);
    }

}
