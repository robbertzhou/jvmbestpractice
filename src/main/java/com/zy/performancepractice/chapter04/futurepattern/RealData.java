package com.zy.performancepractice.chapter04.futurepattern;

import sun.jvm.hotspot.asm.sparc.SPARCRegister;

public class RealData implements Data {
    protected final String result;

    public RealData(String para){
        StringBuilder sb = new StringBuilder();
        for (int i =0;i<10;i++){
            try{
                Thread.sleep(100);
            }catch (InterruptedException ex){

            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
