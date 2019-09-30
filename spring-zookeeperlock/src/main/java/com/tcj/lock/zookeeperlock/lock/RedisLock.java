package com.tcj.lock.zookeeperlock.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisLock {

    public static RLock getLock(String lockKey){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://h100:6379");
        config.useSingleServer().setConnectionPoolSize(500);
        config.useSingleServer().setIdleConnectionTimeout(10000);
        config.useSingleServer().setConnectTimeout(30000);
        config.useSingleServer().setTimeout(3000);
        config.useSingleServer().setPingTimeout(30000);

        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock(lockKey);
        return lock;
    }
}
