package com.oneup.cosmin.xheart.processing.ecg;

public class ECGProcessor {
    private static final ECGProcessor ourInstance = new ECGProcessor();
    public static ECGProcessor getInstance() {
        return ourInstance;
    }
    private ECGProcessor() {
    }

    public int process(int value){
        return value;
    }
}
