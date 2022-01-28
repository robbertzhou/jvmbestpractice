package com.zy.performancepractice.chapter04.masterworkerpattern;

public class MasterClient {
    public static void main(String[] args) {
        Master master = new Master(new PlusWorker(),5);
        for (int i = 0;i<100;i++){
            master.submit(i);
        }
        master.execute();
    }
}
