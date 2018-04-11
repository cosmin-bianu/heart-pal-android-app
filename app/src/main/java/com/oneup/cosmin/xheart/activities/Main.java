package com.oneup.cosmin.xheart.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.oneup.cosmin.xheart.R;

public class Main extends Fragment {
    private GraphView graph;
    private LineGraphSeries<DataPoint> points;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        graph = rootView.findViewById(R.id.ecg_graph);
        graph.setClickable(false);
        Viewport gvp = graph.getViewport();
        gvp.setMinX(0);
        gvp.setMaxX(5);
        gvp.setScrollable(false);
        gvp.setScrollableY(false);
        gvp.setMinY(-127);
        gvp.setMaxY(127);

        points = new LineGraphSeries<>();
        graph.addSeries(points);
        return rootView;
    }

    private void addToGraph(DataPoint p){
        //fixme: W/GraphView: scrollToEnd works only with manual x axis bounds
        points.appendData(p, true, 100);
        graph.addSeries(points);
    }
}
