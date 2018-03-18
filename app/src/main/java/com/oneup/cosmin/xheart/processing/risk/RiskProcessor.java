package com.oneup.cosmin.xheart.processing.risk;

import com.oneup.cosmin.xheart.processing.Register;
import com.oneup.cosmin.xheart.processing.RegisterMemory;
import com.oneup.cosmin.xheart.processing.cases.Range;
import com.oneup.cosmin.xheart.processing.pulse.Pulse;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RiskProcessor {
    private static final RiskProcessor ourInstance = new RiskProcessor();
    public static RiskProcessor getInstance() {
        return ourInstance;
    }
    private RiskProcessor() {

    }

    private static float lastProcessedArrhythmiaRisk;
    private static float arrhythmiaSafeThreshold;
    private static float lastProcessedBPMAssessment;
    private static Range bpmSafeRange;

    private static final LinkedBlockingQueue<Float> arrhythmiaMemory = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Float> bpmMemory = new LinkedBlockingQueue<>();
    private static final int THREADSLEEP_PERIOD = 5000;
    private final Thread arrhythmiaAssessment = new Thread(new Runnable() {
        @Override
        public void run() {
            ConcurrentLinkedQueue<Register> registerMemory =
                    RegisterMemory.getInstance().getMemory();
            while(true){
                int previousPulseDelta = -1;
                int totalDeltaTime = 0;
                for(Register x: registerMemory){
                    Pulse p;
                    if((p = x.getPulse())!=null) {
                        if (previousPulseDelta != -1) {
                            int currentPulseDelta = p.getDeltaTime();
                            totalDeltaTime +=
                                    Math.abs(currentPulseDelta - previousPulseDelta);
                        }
                        previousPulseDelta = p.getDeltaTime();
                    }
                }
                lastProcessedArrhythmiaRisk = totalDeltaTime;
                arrhythmiaMemory.add(Float.valueOf(totalDeltaTime));
                try {
                    Thread.sleep(THREADSLEEP_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    private final Thread bpmAssessment = new Thread(new Runnable() {
        @Override
        public void run() {
            ConcurrentLinkedQueue<Register> registerMemory =
                    RegisterMemory.getInstance().getMemory();
            while(true){
                int pulseCount = 0;
                int totalBPM = 0;
                for(Register x: registerMemory){
                    Pulse p;
                    if((p=x.getPulse())!=null) {
                        pulseCount++;
                        totalBPM += p.getBpm();
                    }
                }
                lastProcessedBPMAssessment = totalBPM/pulseCount;
                try{
                    Thread.sleep(THREADSLEEP_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public RiskAssessmentResult getLastProcessedRiskAssessment(){
        return new RiskAssessmentResult(
                lastProcessedArrhythmiaRisk,
                lastProcessedBPMAssessment);
    }
    public void startRiskAssessment(){
        arrhythmiaAssessment.setDaemon(true);
        arrhythmiaAssessment.start();
        try {
            Thread.sleep(THREADSLEEP_PERIOD/2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bpmAssessment.setDaemon(true);
        bpmAssessment.start();
    }

    public static float getArrhythmiaSafeThreshold() {
        return arrhythmiaSafeThreshold;
    }

    public static void setArrhythmiaSafeThreshold(float arrhythmiaSafeThreshold) {
        RiskProcessor.arrhythmiaSafeThreshold = arrhythmiaSafeThreshold;
    }

    public static Range getBpmSafeRange() {
        return bpmSafeRange;
    }

    public static void setBpmSafeRange(Range bpmSafeRange) {
        RiskProcessor.bpmSafeRange = bpmSafeRange;
    }
}
