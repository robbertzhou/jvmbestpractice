package com.zy.javahigh2.common;


import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.zy.javahigh2.common.ThreadUtil.IO_MAX;
import static com.zy.javahigh2.common.ThreadUtil.KEEP_ALIVE_SECONDS;
import static com.zy.javahigh2.common.ThreadUtil.QUEUE_SIZE;


//懒汉式单例创建线程池：用于IO密集型任务
public class IoIntenseTargetThreadPoolLazyHolder {
    //线程池： 用于IO密集型任务
    public static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            IO_MAX,
            IO_MAX,
            KEEP_ALIVE_SECONDS,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue(QUEUE_SIZE),
            new ThreadUtil.CustomThreadFactory("io"));

    public static ThreadPoolExecutor getInnerExecutor() {
        return EXECUTOR;
    }

    static {
//        log.info("线程池已经初始化");

        EXECUTOR.allowCoreThreadTimeOut(true);
        //JVM关闭时的钩子函数
//        Runtime.getRuntime().addShutdownHook(
//                new ShutdownHookThread("IO密集型任务线程池", new Callable<Void>() {
//                    @Override
//                    public Void call() throws Exception {
//                        //优雅关闭线程池
//                        shutdownThreadPoolGracefully(EXECUTOR);
//                        return null;
//                    }
//                }));
    }
}