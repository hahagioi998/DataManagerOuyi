package com.jeesite.modules.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * MyExtendThreadPoolExecutor
 * 新建线程池，并把线程队列添加到
 * 线程池中管理
 * @author chf
 * @version 2018-07-12
 */

public class MyExtendThreadPoolExecutor extends ThreadPoolExecutor{

    /**
     * 记录运行中的任务
     */
    private LinkedBlockingQueue<Runnable> workBlockingQueue=new LinkedBlockingQueue<Runnable>();

    public MyExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                      long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        //传进来的五个参数有corePoolSize（核心池大小），maximumPoolSize（最大线程数），keepAliveTime（线程保持时间）
        // unit（keepAliveTime的时间单位），workQueue（阻塞线程队列）
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        workBlockingQueue.add(r);//保存在运行的任务
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        workBlockingQueue.remove(r);//移除关闭的任务
    }

    /**
     *
     * Description: 正在运行的任务
     * @return LinkedBlockingQueue<Runnable><br>
     * @author lishun
     */
    public LinkedBlockingQueue<Runnable> getWorkBlockingQueue() {
        return workBlockingQueue;
    }

}
