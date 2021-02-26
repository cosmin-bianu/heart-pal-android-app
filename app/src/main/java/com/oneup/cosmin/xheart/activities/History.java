package com.oneup.cosmin.xheart.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.oneup.cosmin.xheart.R;

public class History extends Fragment {
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_history, container, false);

        //REFERENCE
        lv = rootView.findViewById(R.id.history_LV);

        //ADAPTER
        lv.setAdapter(new HistoryAdapter());

      return rootView;
    }

    @Override
    public String toString() {
        return "History";
    }
}
