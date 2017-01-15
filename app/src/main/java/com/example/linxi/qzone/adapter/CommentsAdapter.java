package com.example.linxi.qzone.adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.linxi.qzone.model.Mood;

import java.util.List;

/**
 * Created by linxi on 2017/1/15.
 */

public class CommentsAdapter extends ArrayAdapter {
    private int resource;
    private Context context;
    private List<Mood> moodList;


    public CommentsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resource,null);
        }else{
            view=convertView;
        }
        return view;

    }

}
