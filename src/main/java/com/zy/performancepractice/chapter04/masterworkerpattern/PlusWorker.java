package com.zy.performancepractice.chapter04.masterworkerpattern;

public class PlusWorker extends Worker{
    public Object handle(Object input){
        Integer i = (Integer) input;
        System.out.println("thread id:" + Thread.currentThread());
        return i++;
    }
}
