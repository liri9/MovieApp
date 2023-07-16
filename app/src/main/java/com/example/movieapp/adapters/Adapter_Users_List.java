package com.example.movieapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.movieapp.models.User;

import java.util.List;

public class Adapter_Users_List extends ArrayAdapter<User> {

    private final Context mContext;

    public Adapter_Users_List(Context context, int resource, List<User> items) {
        super(context, resource, items);
        this.mContext = context;
    }
    public void updateData(List<User> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(android.R.layout.simple_list_item_1, null);
        }

        User p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(android.R.id.text1);

            if (tt1 != null) {
                tt1.setText(p.getUserName());
            }
        }

        return v;
    }
}