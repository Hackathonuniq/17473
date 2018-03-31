package com.example.asmashaikh.myforegroundserviceapplication;

public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = "com.example.asmashaikh.foregroundservice.action.main";
        public static String STARTFOREGROUND_ACTION = "com.example.asmashaikh.foregroundservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.example.asmashaikh.foregroundservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}