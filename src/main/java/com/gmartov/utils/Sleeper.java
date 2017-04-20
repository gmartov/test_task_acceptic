package com.gmartov.utils;

public class Sleeper {

    public static void sleep(long secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
