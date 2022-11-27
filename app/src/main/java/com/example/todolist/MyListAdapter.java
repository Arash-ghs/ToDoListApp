package com.example.todolist;

import android.app.Activity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> title;
    private final ArrayList<Boolean> done;

    public MyListAdapter(Activity context, ArrayList<String> title, ArrayList<Boolean> done) {
        super(context, R.layout.list_view, title);

        this.context=context;
        this.title=title;
        this.done=done;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_view, null,true);

        TextView titleText = rowView.findViewById(R.id.title);
        CheckBox checkBox = rowView.findViewById(R.id.checkBox);

        titleText.setText(title.get(position));
        checkBox.setChecked(done.get(position));
        if(done.get(position)) {
            titleText.setPaintFlags(titleText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        return rowView;

    };
}