package com.zy.performancepractice.chapter04.masterworkerpattern;

import javax.management.Query;
import java.util.Map;
import java.util.Queue;

/**
 * @create 2022-01-28
 * @author robert
 * @desc 对应的worker进程实现代码
 */
public class Worker implements Runnable{
    //任务队列，用于获取子队列
    protected Queue<Object> workQueue;
    //子任务处理结果集
    protected Map<String,Object> resultMap;

    public void setQueue(Queue<Object> workQueue){
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String,Object> resultMap){
        this.resultMap = resultMap;
    }

    //子任务处理逻辑，具体在子类中实现
    public Object handle(Object input){
        return input;
    }


    @Override
    public void run() {
        while (true){
            Object input = workQueue.poll();
            if(input == null) break;
            Object re = handle(input);
            resultMap.put(Integer.toString(input.hashCode()),re);
        }
    }
}
