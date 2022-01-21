package com.zy.performancepractice.chapter04.futurepattern;

public class FutureData implements Data{
    protected RealData realData = null;  //对realdata包装

    protected volatile boolean isReady = false;
    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }
    @Override
    public String getResult() {
        while (!isReady){
            try{
                wait();
            }catch (InterruptedException ex){

            }
        }
        return realData.getResult();
    }
}
