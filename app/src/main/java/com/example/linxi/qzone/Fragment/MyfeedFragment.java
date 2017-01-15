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

public class MyfeedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myfeedLayout=inflater.inflate(R.layout.myfeed_layout,container,false);
        return myfeedLayout;

    }
}
