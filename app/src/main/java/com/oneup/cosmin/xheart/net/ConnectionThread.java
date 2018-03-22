package com.oneup.cosmin.xheart.net;

import android.os.Handler;
import android.os.Looper;

import com.jjoe64.graphview.series.DataPoint;
import com.oneup.cosmin.xheart.activities.MainActivity;
import com.oneup.cosmin.xheart.processing.Processor;

public class ConnectionThread{
    private static ConnectionThread singleton = new ConnectionThread();
    public static ConnectionThread getInstance() { return singleton; }
    private ConnectionThread(){
        mTimer = new Stopwatch();
    }

    private static final String TAG = "ConnectionThread";
    private static final int frequency = 10; //Hz
    private static final int sleepTime  = (int)(((float)1/frequency) * 1000);

    private Stopwatch mTimer;

    public void run(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());

                try {
                    mTimer.start();
                } catch (Stopwatch.AlreadyStartedException e) {
                    e.printStackTrace();
                }
                while (true){
                    String read = Connection.getConnection().read().trim();
                    final int readDouble = Integer.parseInt(read);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {//ADD TO GRAPH
                                MainActivity.getInstance().addToGraph(
                                        new DataPoint(
                                                mTimer.getElapsedTimeInSeconds(),
                                                readDouble)
                                );
                            }catch (Stopwatch.NotStartedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //PROCESS
                    Processor.getProcessor().process(readDouble);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setName("Connection Thread");
        thread.setDaemon(true);
        thread.start();
    }
}
