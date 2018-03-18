package com.oneup.cosmin.xheart.processing.pulse;

public class PulseProcessor {
    private static final PulseProcessor instance = new PulseProcessor();
    public static PulseProcessor getInstance() {
        return instance;
    }
    private PulseProcessor() {

    }

    private int pulseThreshold;
    private int lastValueRegistered;
    private boolean goingUp = false;

    public int getPulseThreshold() {
        return pulseThreshold;
    }
    public void setPulseThreshold(int pulseThreshold) {
        this.pulseThreshold = pulseThreshold;
    }

    public boolean process(int value){
        boolean toReturn = false;
        if(goingUp && lastValueRegistered < value) toReturn = false;
        else if(goingUp && lastValueRegistered > value){
            goingUp = false;
            toReturn = false;
        }
        else if(!goingUp && lastValueRegistered < value && value > pulseThreshold){
            goingUp = true;
            toReturn = true;
        }
        lastValueRegistered = value;
        return toReturn;
    }
}
