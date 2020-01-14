package com.demo.forest.Test;

public class test {

    public static void main(String[] args) {
        test1();
        test2();
    }
    private static void test1(){
        CEO instance = CEO.getInstance();
        instance.helloWord();
    }

    private static void test2(){
        CEO instance = CEO.getInstance();
        instance.helloWord();
    }
}
