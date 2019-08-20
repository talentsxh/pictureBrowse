package com.sxh.demo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sxh.demo.R;

public class MainActivity extends AppCompatActivity {
    private Button mLieanrBtn;
    private Button mRecycleBtn;
    private Button mFriendBtn;
    private Button mCommonBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mLieanrBtn=findViewById(R.id.linaer_recycle_btn);
        mRecycleBtn=findViewById(R.id.grid_recycle_btn);
        mFriendBtn=findViewById(R.id.friend_btn);
        mCommonBtn=findViewById(R.id.common_btn);

        mLieanrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LieanrRecycleActivity.class));
            }
        });

        mRecycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecycleActivity.class));
            }
        });

        mFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FriendActivity.class));
            }
        });

        mCommonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CommonActivity.class));
            }
        });
    }
}
