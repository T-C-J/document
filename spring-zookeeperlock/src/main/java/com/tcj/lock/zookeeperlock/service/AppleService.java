package com.tcj.lock.zookeeperlock.service;

import com.tcj.lock.zookeeperlock.lock.RedisLock;
import com.tcj.lock.zookeeperlock.lock.ZookeeperLock2;
import com.tcj.lock.zookeeperlock.repository.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.locks.Lock;


@Service
@Scope("prototype")
public class AppleService {
    @Autowired
    private AppleRepository appleRepository;

//    Lock lock = new ReentrantLock();

//    Lock lock = new ZookeeperLock("apple");
//    Lock lock = new ZookeeperLock2();
    Lock lock = RedisLock.getLock("lock");

    @Transactional
    public /*synchronized*/ void buy(){
        lock.lock();
        try {
            int i = appleRepository.selectAppleNumber();
            if(i>0){
                System.out.println("购买成功 库存 -1");
                appleRepository.updateAppleNumber();
            }else{
                System.out.println("库存不足 不在减扣");
            }
        }finally {
            lock.unlock();
        }

    }
}
