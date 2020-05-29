package com.simile.plan.gdb.gremlin;

/**
 * created by yitao on 2020/05/21
 */
public class Test {

    public static void main(String[] args) {

        StringBuffer inputBuffer = new StringBuffer();

        for (int i = 0; i < 10; i++) {
            if(i==5){
                inputBuffer.setLength(10);
            }
            inputBuffer.append(i);
            System.out.println(inputBuffer.toString());
        }
    }
}
