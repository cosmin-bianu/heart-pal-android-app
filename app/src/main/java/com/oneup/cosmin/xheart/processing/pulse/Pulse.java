package com.oneup.cosmin.xheart.processing.pulse;

import com.oneup.cosmin.xheart.processing.risk.RiskAnalysis;

import java.sql.Timestamp;

public class Pulse {
    private static int totalPulseNumber = 0;
    private static Timestamp lastPulse;
    private static int currentBPM;

    public static int getTotalPulseNumber(){ return totalPulseNumber;}
    public static int getCurrentBPM(){ return currentBPM;}

    private final int value;
    private final Timestamp time;
    private int deltaTime;
    private int bpm;
    private final RiskAnalysis riskAnalysisAtRegistration;

    public int getValue() {return value;}
    public int getBpm() { return bpm; }
    public int getDeltaTime() { return deltaTime;}

    public Pulse(int value, Timestamp time, RiskAnalysis riskAnalysisAtRegistration) {
        this.value = value;
        this.time = time;
        this.riskAnalysisAtRegistration = riskAnalysisAtRegistration;
        totalPulseNumber++;
        if(lastPulse!=null){
            long ms = time.getTime() - lastPulse.getTime();
            deltaTime = (int) ms/1000;
            bpm = 60/deltaTime;
            currentBPM = bpm;
        }
        lastPulse = time;
    }
}
