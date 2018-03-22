package com.oneup.cosmin.xheart.processing.pulse;

import java.sql.Timestamp;
import java.util.Date;

public class PulseProcessor {
    private static final PulseProcessor instance = new PulseProcessor();
    public static PulseProcessor getInstance() {
        return instance;
    }
    private PulseProcessor() {

    }

    private int pulseThreshold;
    private boolean pulseIsOngoing = false;
    private static final int PULSE_PAUSE_VALUE = 127; //TODO [ANALYZE] set pulse value
    Timestamp[] timestamps;

    public int getPulseThreshold() { //use pulse threshold
        return pulseThreshold;
    }
    public void setPulseThreshold(int pulseThreshold) {
        this.pulseThreshold = pulseThreshold;
    }

    public Timestamp[] process(int value){
        /*
        if(goingUp && lastValueRegistered < value) registered = false;
        else if(goingUp && value == PULSE_PAUSE_VALUE){
            goingUp = false;
            registered = false;
        }
        //!goingUp && lastValueRegistered < value && value > pulseThreshold
        else if(){
            goingUp = true;
            registered = true;
        }*/
        Timestamp[] toReturn = null;

        if(!pulseIsOngoing && value != PULSE_PAUSE_VALUE){
            pulseIsOngoing = true;
            timestamps = new Timestamp[2];
            timestamps[0] = new Timestamp(new Date().getTime());
        } else if(pulseIsOngoing && value == PULSE_PAUSE_VALUE) {
            pulseIsOngoing = false;
            timestamps[1] = new Timestamp(new Date().getTime());
            toReturn = timestamps;
        }

        return toReturn;
    }
}
