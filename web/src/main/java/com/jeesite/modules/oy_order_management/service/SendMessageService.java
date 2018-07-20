/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_order_management.service;

import com.jeesite.modules.oy_order_management.entity.OyTaskAudit;
import com.jeesite.modules.utils.MyExtendThreadPoolExecutor;
import com.jeesite.modules.utils.sendMessageTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * SendMessageService
 * 在订单审核操作之后，新建线程池和线程队列
 * 执行发送信息的业务
 * @author chf
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class SendMessageService{

    MyExtendThreadPoolExecutor pool = new MyExtendThreadPoolExecutor(5, 7,3600, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()); //创建线程池;

    public void startSend(OyTaskAudit oyTaskAudit){
        sendMessageTask smTask=new sendMessageTask(oyTaskAudit); //创建发送短息任务
        pool.execute(smTask);//将任务加入到线程池中

        System.out.println("线程池中线程数目："+pool.getPoolSize()+"，队列中等待执行的任务数目："+
                pool.getQueue().size()+"，执行中的任务数目："+pool.getWorkBlockingQueue().size()+
                ", 已执行完的任务数目："+pool.getCompletedTaskCount());
    }

    public MyExtendThreadPoolExecutor getPool() {
        return pool;
    }
}