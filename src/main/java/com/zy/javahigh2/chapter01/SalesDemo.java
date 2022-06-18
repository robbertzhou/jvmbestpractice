package com.zy.javahigh2.chapter01;

import java.util.concurrent.atomic.AtomicInteger;

public class SalesDemo {
    public final static int MAX_AMOUNT = 5; //商品数量
    static class StoreGoods extends Thread{
        StoreGoods(String name){
            super(name);
        }
        private int goodsAmount = MAX_AMOUNT;
        @Override
        public void run() {
            for (int i = 0;i<= MAX_AMOUNT;i++){
                if(goodsAmount > 0){
                    System.out.println(getName() + "卖出一件，还剩" + (--goodsAmount));
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MallGoods implements Runnable{
        private AtomicInteger goodsAmount = new AtomicInteger(MAX_AMOUNT);

        @Override
        public void run()
        {
            for (int i = 0; i <= MAX_AMOUNT; i++)
            {
                if (this.goodsAmount.get() > 0)
                {
                    System.out.println(Thread.currentThread().getName() + " 卖出一件，还剩："
                            + (goodsAmount.decrementAndGet()));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束.");
        }

    }

    public static void main(String[] args) throws Exception{

        for (int i = 1; i <= 2; i++)
        {
            Thread thread = null;
            thread = new StoreGoods("店员-" + i);
            thread.start();
        }

        Thread.sleep(1000);
        System.out.println("商场版本的销售");
        MallGoods mallGoods = new MallGoods();
        System.out.println("商店版本的销售");
        for (int i = 1; i <= 2; i++)
        {
            Thread thread = null;
            thread = new StoreGoods("店员-" + i);
            thread.start();
        }




    }
}
