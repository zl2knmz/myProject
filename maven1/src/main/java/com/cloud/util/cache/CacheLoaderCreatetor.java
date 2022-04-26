package com.cloud.util.cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.cloud.model.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zl
 * @date 2019/10/18 18:41
 */
//@Slf4j
public class CacheLoaderCreatetor {
    public static CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String key) throws Exception {
//                log.info("加载创建key:" + key);
                return new Employee(key, key + "dept", key + "id");
            }
        };
    }

    public static CacheLoader<String, Employee> createNullCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String key) throws Exception {
//                log.info("加载创建key:" + key);
                if (key.equals("null")) {
                    return null;
                }
                return new Employee(key, key + "dept", key + "id");
            }
        };
    }

    public static CacheLoader<String, Optional<Employee>> createNullValueUseOptionalCacheLoader() {
        return new CacheLoader<String, Optional<Employee>>() {
            @Override
            public Optional<Employee> load(String key) throws Exception {
//                log.info("加载创建key:" + key);
                if (key.equals("null")) {
                    return Optional.fromNullable(null);
                } else {
                    return Optional.fromNullable(new Employee(key, key + "dept", key + "id"));
                }
            }
        };
    }
}
