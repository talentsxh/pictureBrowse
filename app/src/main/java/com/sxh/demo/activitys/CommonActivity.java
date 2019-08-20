package com.sxh.demo.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sxh.demo.R;
import com.sxh.picturebrowse.picutils.AllUtils;

public class CommonActivity extends AppCompatActivity {

    private ImageView mImageView;
    private String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mImageView=findViewById(R.id.pics_img);
        Glide.with(this).load(url).into(mImageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllUtils.startImagePage(CommonActivity.this,url,mImageView);
            }
        });
    }
}
