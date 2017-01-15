package com.example.linxi.qzone.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linxi.qzone.R;

/**
 * Created by linxi on 2017/1/9.
 */

public class MoreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View moreLayout=inflater.inflate(R.layout.more_layout,container,false);
        return moreLayout;

    }
}
