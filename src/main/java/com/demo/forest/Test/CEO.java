package com.demo.forest.Test;

/**
 * 单例模式DEMO,懒汉模式
 */
public class CEO {
    private static CEO ceo = null;
    private static Integer i = 0;

    //私有构造函数
    private CEO() {
    }

    public static CEO getInstance() {
        if (ceo == null) {
            return ceo = new CEO();
        }
        return ceo;
    }

    public void helloWord() {
        i++;
        System.out.println("Hello Word" + i);
    }
}
