package com.tcj.lock.zookeeperlock;

import com.tcj.lock.zookeeperlock.repository.AppleRepository;
import com.tcj.lock.zookeeperlock.service.AppleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperlockApplicationTests {

    @Autowired
    private AppleRepository appleRepository;
    @Autowired
    private ApplicationContext applicationContext;


    public void contextLoads() {
        int i = appleRepository.selectAppleNumber();
        if(i>0){
            System.out.println("正在 -1");
            appleRepository.updateAppleNumber();
        }else{
            System.out.println("库存不足  不在减扣");
        }
    }

    @After
    public void after(){
        System.out.println("结束");
    }

    @Before
    public void before(){
        System.out.println("开始");
    }

    @Test
    public void buy() {
        int serverNum = 5;
        int requestSize = 20;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        List<Thread> threads = new ArrayList<>();


        for(int i = 0;i<serverNum;i++) {
            AppleService appleService = applicationContext.getBean(AppleService.class);

            for (int j = 0; j < requestSize; j++) {
                Thread thread = new Thread(() -> {
                    try {
                        countDownLatch.await();
                        appleService.buy();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                threads.add(thread);
                thread.start();
            }
        }

        countDownLatch.countDown();
        threads.forEach((e)->{
                try {
                    e.join();
        }catch (Exception ex){
                    ex.printStackTrace();
        }
        });
    }

}
