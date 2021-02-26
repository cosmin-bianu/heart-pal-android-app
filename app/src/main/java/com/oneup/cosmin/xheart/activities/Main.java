package com.oneup.cosmin.xheart.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.oneup.cosmin.xheart.R;
import com.oneup.cosmin.xheart.service.BackgroundService;

public class Main extends Fragment {
    private GraphView graph;
    private TextView pulse;

    private static Main instance = null;

    public Main() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);

        instance = this;

        graph = rootView.findViewById(R.id.ecg_graph);
        graph.setClickable(false);
        Viewport gvp = graph.getViewport();
        //gvp.setMinX(0);
        //gvp.setMaxX(5);
        gvp.setScrollable(true);
        //gvp.setScrollableY(false);
        gvp.setMinY(2000);
        gvp.setMaxY(5000);
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setHorizontalLabelsVisible(false);
        glr.setVerticalLabelsVisible(false);
        //glr.setGridColor(Color.WHITE);

        pulse = rootView.findViewById(R.id.tv_pulse);

        if(BackgroundService.getlastSamples() != null){
            setGraph(BackgroundService.getlastSamples());
            setPulse(BackgroundService.getLastBPM());
        }

        return rootView;
    }


    @Override
    public void onDestroy() {
        instance = null;

        super.onDestroy();
    }

    public void setGraph(int[] samples){
        DataPoint[] dataPoints = new DataPoint[samples.length];
        int i = 0;
        for(int y :  samples){
            dataPoints[i] = new DataPoint(i, y);
            i++;
        }
        LineGraphSeries<DataPoint> series =  new LineGraphSeries<>(dataPoints);
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    public void setPulse(int lastBPM) {
        pulse.setText(String.valueOf(lastBPM) + " BPM");
    }

    public static Main getInstance() {
        return instance;
    }
}
