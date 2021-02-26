package com.oneup.cosmin.xheart.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oneup.cosmin.xheart.R;
import com.oneup.cosmin.xheart.processing.Diagnostic;
import com.oneup.cosmin.xheart.service.BackgroundService;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<Diagnostic>{

    private ArrayList<Diagnostic> diagnostics;

    public HistoryAdapter(){
        super(MainActivity.getContext(), R.layout.history_entry, BackgroundService.diags);
        diagnostics = BackgroundService.diags;
    }

    public HistoryAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;

        if(item == null)
            item = LayoutInflater.from(getContext()).inflate(R.layout.history_entry, parent, false);

        Diagnostic diag = diagnostics.get(position);

        TextView type = item.findViewById(R.id.tv_type);
        type.setText(diag.description);

        TextView time = item.findViewById(R.id.tv_time);
        time.setText(diag.time);

        TextView date = item.findViewById(R.id.tv_date);
        date.setText(diag.date);

        return item;
    }
}
