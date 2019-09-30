package com.tcj.lock.zookeeperlock.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZookeeperLock2 implements Lock {

    private InterProcessMutex lock;//可重入锁实现类
    private String lockPAth = "/lock/shareLock";

    //zookeeper集群地址
    public static final String ZOOKEEPERSTRING = "h100:2181,h101:2181,h102:2181";

    public ZookeeperLock2() {
        init();
    }


    public void init(){
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZOOKEEPERSTRING, new ExponentialBackoffRetry(1000, 3));
        client.start();
        lock = new InterProcessMutex(client, lockPAth);
    }



    @Override
    public void lock() {
        try{
            System.out.println("获取锁了..............");
            lock.acquire();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void unlock() {
        try {
            System.out.println("释放锁了..............");
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
