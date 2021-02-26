package com.oneup.cosmin.xheart.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.oneup.cosmin.xheart.activities.Main;
import com.oneup.cosmin.xheart.net.Connection;
import com.oneup.cosmin.xheart.processing.Diagnostic;
import com.oneup.cosmin.xheart.processing.DiagnosticAdapter;

import java.util.ArrayList;

public class BackgroundService extends Service {
    private static final String TAG = "BackgroundService";
    private Handler handler;
    private static final int waitTimeMilliseconds = 4 * 1000;
    private static final DiagnosticAdapter diagAdapter = DiagnosticAdapter.getInstance();
    public static final ArrayList<Diagnostic> diags = new ArrayList<>();
    private static int[] lastSamples;
    private static int lastBPM;
    private  Connection con = Connection.getConnection();

    //TEMPLATE
    //A,TIME,DATE
    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: Polling");
            String str = con.read();

            //Log.d(TAG, "run: str:\n" + str);

            String[] entities = str.split("END\n");
            //String[] diagnostics = entities[0].split("\n");
            String[] samples = entities[1].split("\n");

            //final ArrayList<Diagnostic> tmpDiags = new ArrayList<>(diagnostics.length);

            //for (String x : diagnostics)
            /*    tmpDiags.add(diagAdapter.getDiagnostic(x));

            new Handler(MainActivity.getLooper()).post(new Runnable() {
                @Override
                public void run() {
                    diags.addAll(tmpDiags);
                }
            }); */

            ArrayList<Integer> samplesArray = new ArrayList<>();
            int firstCounter = -1;
            for (int i = 0; i < samples.length; i++)
                if(i%3==0)
                    samplesArray.add(Integer.parseInt(samples[i]));

            lastSamples = new int[samplesArray.size()];

            for (Integer x : samplesArray){
                lastSamples[++firstCounter] = x;
            }

            int counter = -1;
            int[] vertices = new int[lastSamples.length];
            int[] sampleTimes = new int[lastSamples.length];
            int beforeLastSample = lastSamples[0];
            int lastSample = lastSamples[1];
            for(int i=2; i<lastSamples.length;i++){
                int currentSample = lastSamples[i];
                if(currentSample > lastSample)
                    if(lastSample < beforeLastSample) {
                        vertices[++counter] = lastSample;
                        sampleTimes[counter] = i ;
                        //Log.d(TAG, "run: Vertex " + lastSample);
                    }
                beforeLastSample = lastSample;
                lastSample = currentSample;
            }

            counter = -1;
            int[] selectedVertices = new int[vertices.length];
            int[] selectedTimes = new int[sampleTimes.length];
            for(int i =0; i<vertices.length;i++){
                int vertex = vertices[i];
                if(vertex==0) continue;
                int time = sampleTimes[i];
                if(vertex < 300){
                    selectedVertices[++counter] = vertex;
                    selectedTimes[counter] = time;
                   // Log.d(TAG, "run: Selected vertex" + selectedVertices[counter]);
                }
            }

            double[] vertexDeltas = new double[selectedVertices.length];
            int lastTime = selectedTimes[0];
            for (int i = 1; i<selectedVertices.length; i++) {
                int vertex = selectedVertices[i];
                if(vertex == 0) continue;
                int time = selectedTimes[i];

                vertexDeltas[i-1] =(double) Math.abs(lastTime-time)/60;
                //Log.d(TAG, "run: Added time: " + vertexDeltas[i-1]);
                lastTime = time;
            }

            double total = 0;
            int totalCount = 0;
            for(double time : vertexDeltas)
                if(time!=0) {
                    total += time;
                    totalCount++;
                }

            double average = (double)total/totalCount;

            lastBPM = (int)(20/average);

            if(Main.getInstance()!=null) {
                Main.getInstance().setGraph(lastSamples);
                Main.getInstance().setPulse(lastBPM);
            }

            Log.d(TAG, "run: Done. Delaying future task now.");
            handler.postDelayed(backgroundTask, waitTimeMilliseconds);
        }
    };

    public static int getLastBPM() {
        return lastBPM;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        ArrayList<Diagnostic> demoList = new ArrayList<>();
        demoList.add(diagAdapter.getDiagnostic("A,13:32,24/02/2018"));
        demoList.add(diagAdapter.getDiagnostic("A,20:16,28/02/2018"));
        demoList.add(diagAdapter.getDiagnostic("V,11:59,02/03/2018"));
        diags.addAll(demoList);
        handler.post(backgroundTask);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static int[] getlastSamples() {
        return lastSamples;
    }
}
