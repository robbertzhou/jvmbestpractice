package com.zy.depthjvm.chapter02;

/**
 * @author zhouyu
 * VM Args:-Xss2m
 */
public class JavaStackVMOOm {
    public void dontStop(){
        while (true){}
    }

    public void stackLeakByThread(){
        while (true){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            };
            new Thread(runnable).start();
        }
    }

    public static void main(String[] args) {
        JavaStackVMOOm oom = new JavaStackVMOOm();
        try{
            oom.stackLeakByThread();
        }catch (Exception ex){

        }
    }
}
