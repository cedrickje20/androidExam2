package com.example.anexam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class Custom extends BaseAdapter {
    private ArrayList<Login> loginArrayList;
    private Context context;
    private int layout;

    public Custom(List<Questions> loginArrayList, Context context, int layout) {
        this.loginArrayList = loginArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
