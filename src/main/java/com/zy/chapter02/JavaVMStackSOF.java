package com.zy.chapter02;

/**
 * @author zhouyu
 * VM Args:-Xss128k
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        System.out.println("stack length is:" + stackLength);
        stackLeak();
    }
    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try{
            sof.stackLeak();
        }catch (Exception ex){
            System.out.println("stack length is:" + sof.stackLength);
            throw ex;
        }
    }
}
