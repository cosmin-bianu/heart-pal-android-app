package com.oneup.cosmin.xheart.processing;

import android.content.Context;

import com.oneup.cosmin.xheart.processing.cases.CaseProcessor;
import com.oneup.cosmin.xheart.processing.ecg.ECG;
import com.oneup.cosmin.xheart.processing.ecg.ECGProcessor;
import com.oneup.cosmin.xheart.processing.pulse.Pulse;
import com.oneup.cosmin.xheart.processing.pulse.PulseProcessor;
import com.oneup.cosmin.xheart.processing.risk.RiskAnalysis;
import com.oneup.cosmin.xheart.processing.risk.RiskAssessmentResult;
import com.oneup.cosmin.xheart.processing.risk.RiskProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Processor {
    private static Processor singleton;
    public static void init(Context context) { singleton = new Processor(context); }
    public static Processor getProcessor(){ return singleton; }

    private final ECGProcessor ecgProcessor = ECGProcessor.getInstance();
    private final PulseProcessor pulseProcessor = PulseProcessor.getInstance();
    private final RiskProcessor riskProcessor = RiskProcessor.getInstance();
    private final CaseProcessor caseProcessor = CaseProcessor.getInstance();

    private Processor(final Context context){
        RegisterMemory.init(context);
        riskProcessor.startRiskAssessment();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(context.getFilesDir(), "registeredData.dat");
                 try {
                     if(!file.exists())
                        file.createNewFile();
                     FileOutputStream outputStream = new FileOutputStream(file);
                     String toWrite = String.valueOf(pulseProcessor.getPulseThreshold()) + ";" +
                                        String.valueOf(RiskProcessor.getArrhythmiaSafeThreshold()) + ";" +
                                        String.valueOf(RiskProcessor.getBpmSafeRange().getMin()) + "-" +
                                        String.valueOf(RiskProcessor.getBpmSafeRange().getMax() + ";\n"); //TODO add the rest.
                     outputStream.write(toWrite.getBytes());
                     outputStream.flush();
                     outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public Register process(final int value){
        int ecgValue = ecg(value);
        Timestamp[] pulseValue = pulse(value);
        RiskAssessmentResult riskValue = risk();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        RiskAnalysis riskAnalysis = new RiskAnalysis(riskValue, ts);
        ECG ecg = new ECG(ecgValue, ts, riskAnalysis);
        Pulse pulse = null;
        if(pulseValue != null)
            pulse = new Pulse(value,
                    ts,
                    pulseValue[0], //start time
                    pulseValue[1]); //end time
        Register register =
                new Register(
                        ts,
                        ecg,
                        pulse,
                        riskAnalysis);
        RegisterMemory.getInstance().add(register);
        return register;
    }


    private int ecg(final int value){
        return ecgProcessor.process(value);
    }

    private Timestamp[] pulse(final int value){
        return pulseProcessor.process(value);
    }

    private RiskAssessmentResult risk() {
        return riskProcessor.getLastProcessedRiskAssessment();
    }
}
