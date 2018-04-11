package com.oneup.cosmin.xheart.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.oneup.cosmin.xheart.R;

/**
 * Created by mihai on 04.04.2018.
 */

public class History extends Fragment {

    private ListView lv;
    private static String[] istorice={"", "Historic 1","Historic 2","Historic 3","Historic 4","Historic 5"};

    public static History newInstance(){
        History history = new History();

        return history;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_content_main, container, false);

        //REFERENCE
        lv = rootView.findViewById(R.id.history_LV);


        //ADAPTER
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, istorice));

        //ITEM CLICKS
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), istorice[i], Toast.LENGTH_SHORT).show();
            }
        });
      return rootView;
    }
    @Override
    public String toString() {

        return "History";
    }
}
