package com.demo.forest.Test;

public class test {

    public static void main(String[] args) {
        test1();
        test2();
    }
    public static void test1(){
        CEO instance = CEO.getInstance();
        instance.helloword();
    }

    public static void test2(){
        CEO instance = CEO.getInstance();
        instance.helloword();
    }
}
