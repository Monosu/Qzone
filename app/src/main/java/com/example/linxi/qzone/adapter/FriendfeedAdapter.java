package com.example.linxi.qzone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.linxi.qzone.model.Journal;

import java.util.List;

/**
 * Created by linxi on 2017/1/15.
 */

public class FriendfeedAdapter extends ArrayAdapter {
    //提交
    private int resource;
    private List<Journal> journalList;
    private Context context;

    public FriendfeedAdapter(Context context, int resource, List<Journal> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.journalList=objects;

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  View view;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resource,null);
        }else{
            view=convertView;
        }
        return view;
    }
}
