package com.example.projectapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BreakpointAdapter extends ArrayAdapter<Breakpoint> {

    public BreakpointAdapter(Context context, ArrayList<Breakpoint> breakpoints) {
        super(context, R.layout.row_location, breakpoints);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Breakpoint breakpoint = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_location, parent, false);
        }
        return convertView;
    }
}
