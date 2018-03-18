package com.oneup.cosmin.xheart.processing;


import android.content.Context;

import com.oneup.cosmin.xheart.processing.pulse.Pulse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RegisterMemory {

    static void init(Context context){
        if(!init) {
            ourInstance = new RegisterMemory(context);
            init = true;
        }
    }
    private static RegisterMemory ourInstance;
    public static RegisterMemory getInstance() {
        return ourInstance;
    }
    private RegisterMemory(Context context) {
        File file = new File(context.getFilesDir(), "history.xht");
        try {
            if(!file.exists())
                file. createNewFile();
            outputStream = new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread checkerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (memory.size() >= 2000) {
                        try {
                            for(int i=1; i<=400; i++) {
                                Register entity = memory.poll();
                                String ecg = String.valueOf(entity.getEcg().getValue());
                                String pulse = String.valueOf(entity.getPulse().getValue());
                                String risk = String.valueOf(entity.getRiskAnalysis().getValue());
                                String timestamp = entity.getTime().toString();
                                String bpm = String.valueOf(entity.getPulse().getBpm());
                                String totalBeats = String.valueOf(Pulse.getTotalPulseNumber());
                                String toWrite = timestamp + ";" +
                                        ecg + ";" +
                                        pulse + ";" +
                                        risk + ";" +
                                        bpm + ";" +
                                        totalBeats + ";\n";
                                outputStream.write(toWrite.getBytes());
                                outputStream.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try{
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        checkerThread.setDaemon(true);
        checkerThread.start();
    }


    private static boolean init;
    private final ConcurrentLinkedQueue<Register> memory
            = new ConcurrentLinkedQueue<>();
    private FileOutputStream outputStream;

    void add(Register toAdd){
        memory.add(toAdd);
    }
    public ConcurrentLinkedQueue<Register> getMemory(){ return memory; }
}
