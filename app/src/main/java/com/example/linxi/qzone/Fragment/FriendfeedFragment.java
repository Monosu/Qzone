package com.example.linxi.qzone.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.linxi.qzone.R;
import com.example.linxi.qzone.adapter.CommentsAdapter;
import com.example.linxi.qzone.adapter.FriendfeedAdapter;
import com.example.linxi.qzone.model.Journal;

import java.util.List;

/**
 * Created by linxi on 2017/1/9.
 */

public class FriendfeedFragment extends Fragment {
    private ListView friendfeeedListview;
    private ListView commentListview;
    private FriendfeedAdapter friendfeedAdapter;
    private CommentsAdapter commentsAdapter;
    private List<Journal> journalList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View friendfeedLayout=inflater.inflate(R.layout.friendfeed_layout,container,false);

        friendfeeedListview= (ListView)friendfeedLayout.findViewById(R.id.friendfeed_lv);
        friendfeedAdapter=new FriendfeedAdapter(getActivity(),R.layout.friendfeed_listview_item,null);
        friendfeeedListview.setAdapter(friendfeedAdapter);

//        commentListview= (ListView) friendfeedLayout.findViewById(R.id.comment_lv);
//        commentsAdapter=new CommentsAdapter(getActivity(),R.layout.comment_lv_item,null);
//        commentListview.setAdapter(commentsAdapter);


        return friendfeedLayout;
    }


}
