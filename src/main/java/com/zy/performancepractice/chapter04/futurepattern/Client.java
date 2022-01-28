package com.zy.performancepractice.chapter04.futurepattern;

import java.util.concurrent.Future;

public class Client {
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData("");

            }
        }.start();
        return future;
    }
}
