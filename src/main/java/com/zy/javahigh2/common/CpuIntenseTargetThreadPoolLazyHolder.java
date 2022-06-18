package com.zy.javahigh2.common;


//import lombok.extern.slf4j.Slf4j;

import scala.sys.ShutdownHookThread;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.zy.javahigh2.common.ThreadUtil.*;

//懒汉式单例创建线程池：用于CPU密集型任务



public class CpuIntenseTargetThreadPoolLazyHolder {
    //线程池： 用于CPU密集型任务
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            MAXIMUM_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_SECONDS,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue(QUEUE_SIZE),
            new CustomThreadFactory("cpu"));


    public static ThreadPoolExecutor getInnerExecutor() {
        return EXECUTOR;
    }

    static {
//        log.info("线程池已经初始化");

        EXECUTOR.allowCoreThreadTimeOut(true);
        //JVM关闭时的钩子函数
//        Runtime.getRuntime().addShutdownHook(
//                new ShutdownHookThread("CPU密集型任务线程池", new Callable<Void>() {
//                    @Override
//                    public Void call() throws Exception {
//                        //优雅关闭线程池
//                        shutdownThreadPoolGracefully(EXECUTOR);
//                        return null;
//                    }
//                }));
    }
}
