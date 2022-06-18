package com.zy.javahigh2.chapter01;

import com.zy.javahigh2.common.Print;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.zy.javahigh2.common.Print;
import com.zy.javahigh2.common.ThreadUtil;

import static com.zy.javahigh2.common.ThreadUtil.getCurThreadName;
import static com.zy.javahigh2.common.ThreadUtil.sleepMilliSeconds;

public class CreateDemo4 {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 10000000;
    public static final ExecutorService pool = Executors.newFixedThreadPool(3);

    static class DemoThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0;i < MAX_TURN;i++){
                Print.cfo(Thread.currentThread().getName() + ",轮次="+i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReturnableTask implements Callable<Long>
    {
        //返回并发执行的时间
        public Long call() throws Exception
        {
            long startTime = System.currentTimeMillis();
            Print.cfo(getCurThreadName() + " 线程运行开始.");
            for (int j = 1; j < MAX_TURN; j++)
            {
                Print.cfo(getCurThreadName() + ", 轮次：" + j);
                sleepMilliSeconds(10);
            }
            long used = System.currentTimeMillis() - startTime;
            Print.cfo(getCurThreadName() + " 线程运行结束.");
            return used;
        }
    }


    public static void main(String[] args) {
        pool.execute(new DemoThread());
        pool.submit(new ReturnableTask());
    }
}
