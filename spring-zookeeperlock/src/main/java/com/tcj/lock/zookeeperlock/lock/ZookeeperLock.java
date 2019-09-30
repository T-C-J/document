package com.tcj.lock.zookeeperlock.lock;

import javafx.concurrent.Worker;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZookeeperLock implements Lock {

    private final static String WORKSPACE = "/lock-zookeeper";

    private String lockName;

    private CuratorFramework client;

//    private static final String ZOOKEEPERSERVER = "h100:2181";
    private static final String ZOOKEEPERSERVER = "h100:2181,h101:2181,h102:2181";

    public ZookeeperLock(String lockName) {
        this.lockName = lockName;
        init();
    }

    private void init(){
        // 初始化 zk 客户端对象

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10);
        client = CuratorFrameworkFactory.newClient(ZOOKEEPERSERVER,5000,1000,retryPolicy);
        client.start();
        // 创建 根路径
        createRoot();
    }

    private void createRoot() {
        try {
            if(client.checkExists().forPath(WORKSPACE) == null){

                // 创建永久节点
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(WORKSPACE);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void lock() {

        while (true){
            String lockPath = WORKSPACE + "/" + lockName;
            try {
                if(client.checkExists().forPath(lockPath) == null){
                    //创建节点
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                            .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                            .forPath(lockPath);
                    System.out.println("============== get lock success ==========");

                    return;
                }else{
                    listenerrWait();
                }
            }catch (Exception e){
                try {
                    listenerrWait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }


    }


    private void listenerrWait() throws Exception{

        // 创建阻塞类
        final CountDownLatch countDownLatch = new CountDownLatch(1);


        // 初始化节点监听器
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, WORKSPACE, true);
        pathChildrenCache.start();

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                if(event.getData().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED) &&
                    event.getData().getPath().endsWith(lockName)){
                    countDownLatch.countDown();
                }
            }
        });

        countDownLatch.await();
    }


    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        String lockPath = WORKSPACE + "/" + lockName;
        try {
            client.delete().forPath(lockPath);
            System.out.println("============ unlock ============");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Condition newCondition() {
        return null;
    }
}
