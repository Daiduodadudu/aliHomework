package com.ebanma.cloud.usertestall.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author : 连峰
 * @version $ Id: CaffeineCacheConfig, v 0.1 2023/03/29 13:43 banma- Exp $
 */

//使用redis缓存 这个配置类不能启用。
//@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    public static final Logger logger = LoggerFactory.getLogger(CaffeineCacheConfig.class);

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        //缓存集合
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        //对缓存key属性做设置
        caches.add(new CaffeineCache("users-cache",
                Caffeine.newBuilder()
                        //指定key下最大缓存数据量
                        .maximumSize(1000)
                        //最后一次访问之后 120秒 过期
                        .expireAfterAccess(123, TimeUnit.SECONDS)
                        .build()
        ));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

//    // spring 自带的缓存 使用map改造的
//    @Bean("cacheManager")
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("users-cache")));
//        return cacheManager;
//    }

}
































