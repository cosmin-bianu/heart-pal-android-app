package com.oneup.cosmin.xheart.processing.risk;

import com.oneup.cosmin.xheart.exceptions.ShittyCodeException;
import com.oneup.cosmin.xheart.processing.Register;
import com.oneup.cosmin.xheart.processing.RegisterMemory;
import com.oneup.cosmin.xheart.processing.cases.CaseProcessor;
import com.oneup.cosmin.xheart.processing.cases.Range;
import com.oneup.cosmin.xheart.processing.pulse.Pulse;

import java.util.concurrent.ConcurrentLinkedQueue;

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
    private static float lastProcessedQRSAssessment;
    private static long qrsComplexLength;

    private static final int THREADSLEEP_PERIOD = 5000;
    private static final CaseProcessor cp = CaseProcessor.getInstance();
    private final Thread arrhythmiaAssessment = new Thread(new Runnable() {
        @Override
        public void run() {
            ConcurrentLinkedQueue<Register> registerMemory =
                    RegisterMemory.getInstance().getValueMemory();
            while(true){
                if(registerMemory.size() > 500) {
                    int previousPulseDelta = -1;
                    int totalDeltaTime = 0;
                    for (Register x : registerMemory) {
                        Pulse p;
                        if ((p = x.getPulse()) != null) {
                            if (previousPulseDelta != -1) {
                                int currentPulseDelta = p.getDeltaTime();
                                totalDeltaTime +=
                                        Math.abs(currentPulseDelta - previousPulseDelta);
                            }
                            previousPulseDelta = p.getDeltaTime();
                        }
                    }
                    lastProcessedArrhythmiaRisk = totalDeltaTime;
                    if (lastProcessedArrhythmiaRisk > arrhythmiaSafeThreshold)
                        cp.issueAlert("DANGER! UNSTABLE HEART RATE");
                }
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
                    RegisterMemory.getInstance().getValueMemory();
            while(true){
                if(registerMemory.size() > 500){
                    int pulseCount = 0;
                    int totalBPM = 0;
                    for (Register x : registerMemory) {
                        Pulse p;
                        if ((p = x.getPulse()) != null) {
                            pulseCount++;
                            totalBPM += p.getBpm();
                        }
                    }
                    lastProcessedBPMAssessment = totalBPM / pulseCount;
                    if (!bpmSafeRange.isWithinRange(lastProcessedArrhythmiaRisk)) {
                        if (lastProcessedArrhythmiaRisk <= bpmSafeRange.getMin())
                            cp.issueAlert("DANGER! LOW BPM");
                        else cp.issueAlert("DANGER! HIGH BPM");
                    }
                }
                try{
                    Thread.sleep(THREADSLEEP_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    private final Thread complexAssessment = new Thread(new Runnable() {
        @Override
        public void run() {
            ConcurrentLinkedQueue<Register> registerMemory =
                    RegisterMemory.getInstance().getValueMemory();
            while(true){
                try {
                    if(registerMemory.size() > 500) {
                        Range complexRange = new Range(
                                qrsComplexLength - 100, //ms
                                qrsComplexLength + 100, //ms
                                false,
                                false
                        );
                        for (Register x : registerMemory) {
                            if (x.getPulse() != null) {
                                long drt = x.getPulse().getDuration();
                                if (!complexRange.isWithinRange(drt)) {
                                    if (drt <= complexRange.getMin())
                                        lastProcessedQRSAssessment += complexRange.getMin() - drt;
                                    else if (drt >= complexRange.getMax())
                                        lastProcessedQRSAssessment += drt - complexRange.getMax();
                                    else throw new ShittyCodeException();
                                }
                            }
                        }
                        if (lastProcessedQRSAssessment > 400) //ms //TODO [ANALYZE] adjust this value
                            cp.issueAlert("DANGER! UNSTABLE COMPLEX");
                    }
                    Thread.sleep(THREADSLEEP_PERIOD);
                } catch (ShittyCodeException
                        | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public RiskAssessmentResult getLastProcessedRiskAssessment(){
        return new RiskAssessmentResult(
                lastProcessedArrhythmiaRisk,
                lastProcessedBPMAssessment,
                lastProcessedQRSAssessment);
    }
    public void startRiskAssessment(){
        arrhythmiaAssessment.setDaemon(true);
        arrhythmiaAssessment.setName("Arrhythmia Assess. Thread");
        arrhythmiaAssessment.start();
        try {
            Thread.sleep(THREADSLEEP_PERIOD/3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bpmAssessment.setDaemon(true);
        bpmAssessment.setName("BPM Assess. Thread");
        bpmAssessment.start();
        try {
            Thread.sleep(THREADSLEEP_PERIOD/3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complexAssessment.setDaemon(true);
        complexAssessment.setName("Complex Assess. Thread");
        complexAssessment.start();
    }

    public static float getArrhythmiaSafeThreshold() {
        return arrhythmiaSafeThreshold;
    }
    //TODO [ANALYZE] arrhythmia safe threshold
    public static void setArrhythmiaSafeThreshold(float arrhythmiaSafeThreshold) {
        RiskProcessor.arrhythmiaSafeThreshold = arrhythmiaSafeThreshold;
    }

    public static Range getBpmSafeRange() {
        return bpmSafeRange;
    }

    public static void setBpmSafeRange(Range bpmSafeRange) {
        RiskProcessor.bpmSafeRange = bpmSafeRange;
    }

    public static long getQrsComplexLength() {
        return qrsComplexLength;
    }

    public static void setQrsComplexLength(long qrsComplexLength) {
        RiskProcessor.qrsComplexLength = qrsComplexLength;
    }
}
