package com.example.linxi.qzone;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.linxi.qzone.Fragment.FriendfeedFragment;
import com.example.linxi.qzone.Fragment.MoreFragment;
import com.example.linxi.qzone.Fragment.MyfeedFragment;
import com.example.linxi.qzone.Fragment.MyzoneFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FriendfeedFragment friendfeedFragment;
    private MyfeedFragment myfeedFragment;
    private MyzoneFragment myzoneFragment;
    private MoreFragment moreFragment;

    private View friendfeedLayout;
    private View myfeedLayout;
    private View myzoneLayout;
    private View moreLayout;

    private ImageView friendfeedImage;
    private ImageView myfeedImage;
    private ImageView myzoneImage;
    private ImageView moreImage;

    private ImageView publishImage;

    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager=getFragmentManager();
        setTabSelection(0);
    }



    private void initViews() {
        friendfeedLayout=findViewById(R.id.friendfeed_layout);
        myfeedLayout=findViewById(R.id.myfeed_layout);
        myzoneLayout=findViewById(R.id.myzone_layout);
        moreLayout=findViewById(R.id.more_layout);

        friendfeedImage= (ImageView) findViewById(R.id.im_friendfeed);
        myfeedImage= (ImageView) findViewById(R.id.im_myfeed);
        publishImage= (ImageView) findViewById(R.id.im_publish);
        myzoneImage= (ImageView) findViewById(R.id.im_myzone);
        moreImage= (ImageView) findViewById(R.id.im_more);

        friendfeedLayout.setOnClickListener(this);
        myfeedLayout.setOnClickListener(this);
        myzoneLayout.setOnClickListener(this);
        moreLayout.setOnClickListener(this);

        publishImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friendfeed_layout:
                setTabSelection(0);
                break;
            case R.id.myfeed_layout:
                setTabSelection(1);
                break;
            case R.id.myzone_layout:
                setTabSelection(2);
                break;
            case R.id.more_layout:
                setTabSelection(3);
                break;

        }

    }

    /**
     * 根据传入的index参数来设置选中的tab页
     * @param index
     * 0表示动态，1表示与我相关，2表示我的空间，3表示更多
     */

    private void setTabSelection(int index) {
        clearSelection();
        //开启事务
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        //先隐藏掉所有Fragment，防止多个Fragment同时显示
        hideFragments(transaction);
        switch (index){
            case 0:
                friendfeedImage.setImageResource(R.drawable.qz_icon_toolbar_friendfeed_pressed);

                if (friendfeedFragment==null){
                    friendfeedFragment=new FriendfeedFragment();
                    transaction.add(R.id.content,friendfeedFragment);
                }else {
                    transaction.show(friendfeedFragment);
                }
                break;
            case 1:
                myfeedImage.setImageResource(R.drawable.qz_icon_toolbar_myfeed_pressed);
                if (myfeedFragment==null){
                    myfeedFragment=new MyfeedFragment();
                    transaction.add(R.id.content,myfeedFragment);
                }else{
                    transaction.show(myfeedFragment);
                }
                break;
            case 2:
                myzoneImage.setImageResource(R.drawable.qz_icon_toolbar_myzone_pressed);
                if (myzoneFragment==null){
                    myzoneFragment=new MyzoneFragment();
                    transaction.add(R.id.content,myzoneFragment);
                }else{
                    transaction.show(myzoneFragment);
                }
                break;
            case 3:
                moreImage.setImageResource(R.drawable.qz_icon_toolbar_more_pressed);
                if (moreFragment==null){
                    moreFragment=new MoreFragment();
                    transaction.add(R.id.content,moreFragment);
                }else{
                    transaction.show(moreFragment);
                }
                break;

        }
        transaction.commit();

    }
    //隐藏所有Fragment
    private void hideFragments(FragmentTransaction transaction) {
        if (friendfeedFragment!=null){
            transaction.hide(friendfeedFragment);
        }
        if (myfeedFragment!=null){
            transaction.hide(myfeedFragment);
        }
        if (myzoneFragment!=null){
            transaction.hide(myzoneFragment);
        }
        if (moreFragment!=null){
            transaction.hide(moreFragment);
        }

    }
    //清除所有选中状态
    private void clearSelection() {
        friendfeedImage.setImageResource(R.drawable.qz_icon_toolbar_friendfeed_normal);
        myfeedImage.setImageResource(R.drawable.qz_icon_toolbar_myfeed_normal);
        myzoneImage.setImageResource(R.drawable.qz_icon_toolbar_myzone_normal);
        moreImage.setImageResource(R.drawable.qz_icon_toolbar_more_normal);
    }
}
