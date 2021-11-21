package org.example.job.cache;

import java.util.List;


/**
 * @author 85217
 * Cache 接口面向应用程序，编程使用Cache接口编程！
 */
public interface Cache {

    boolean isExist(String setKey, String value);

    void set(String key, String value, long time);

    void  add(String key, List<String> values);

}
