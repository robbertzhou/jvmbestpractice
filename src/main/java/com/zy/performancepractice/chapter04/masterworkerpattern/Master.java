package com.zy.performancepractice.chapter04.masterworkerpattern;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @create 2022-01-28
 * @author robert
 * @desc 主机类
 */
public class Master {
    static volatile int count = 0;
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedDeque<>();
    //worker线程队列
    protected Map<String,Thread> threadMap = new HashMap<String,Thread>();
    //子任务处理结果
    protected Map<String,Object> resultMap = new HashMap<>();

    public boolean isComplete(){
        for (Map.Entry<String,Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState() !=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    //Master的构造函数work进程和进程数量
    public Master(Worker worker,int countWorker){
        worker.setQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0;i<countWorker;i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    public void submit(Object job){
        workQueue.add(job);
    }

    public Map<String,Object> getResultMap(){
        return resultMap;
    }

    //开始运行所有子任务
    public void execute(){
        for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
            entry.getValue().start();
            System.out.println(count++);
        }
    }
}
