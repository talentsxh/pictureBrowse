package com.sxh.demo.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxh.demo.R;
import com.sxh.demo.adapters.FirendAdapter;
import com.sxh.demo.beans.MenuBean;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private FirendAdapter mFirendAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        initView();
    }

    private void initView() {
        mRecyclerView=findViewById(R.id.recycle);
        mFirendAdapter =new FirendAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirendAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        initVars();
    }
    List<MenuBean.DataEntity> listBeans = new ArrayList<>();
    public void initVars(){
        listBeans.clear();
        for (int i=0;i<18;i++){
            MenuBean.DataEntity beanone = new MenuBean.DataEntity();
            List<String> mStrings=new ArrayList<>();
            if (i==0){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                beanone.setPics(mStrings);
                beanone.setName("1图--正方形模式，1图多种设配");
            }
            if (i==1){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                beanone.setPics(mStrings);
                beanone.setName("2图九宫格");
            }
            if (i==2){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                beanone.setPics(mStrings);
                beanone.setName("3图九宫格");
            }
            if (i==3){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                mStrings.add("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                beanone.setPics(mStrings);
                beanone.setName("4图九宫格");
            }
            if (i==4){
                mStrings.clear();
                mStrings.add("http://b-ssl.duitang.com/uploads/blog/201508/10/20150810221337_PAyNX.jpeg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                mStrings.add("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                beanone.setPics(mStrings);
                beanone.setName("5图九宫格");
            }
            if (i==5){
                mStrings.clear();
                mStrings.add("http://b-ssl.duitang.com/uploads/blog/201508/10/20150810221337_PAyNX.jpeg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                mStrings.add("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564567730425&di=b9591af783e80a3be31a3c8ce56cc3a7&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170803%2F3bdb3d68cc6a4e158e3b0e06fb73edc7.gif");
                beanone.setPics(mStrings);
                beanone.setName("6图九宫格");
            }
            if (i==6){
                mStrings.clear();
                mStrings.add("http://b-ssl.duitang.com/uploads/blog/201508/10/20150810221337_PAyNX.jpeg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                mStrings.add("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564567730425&di=b9591af783e80a3be31a3c8ce56cc3a7&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170803%2F3bdb3d68cc6a4e158e3b0e06fb73edc7.gif");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564565865204&di=f8576272a4ec9791fef8b3110d5450af&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160721%2F2791892bc058440f8f814e6ddf8752d3_th.jpg");
                beanone.setPics(mStrings);
                beanone.setName("7图九宫格");
            }
            if (i==7){
                mStrings.clear();
                mStrings.add("http://b-ssl.duitang.com/uploads/blog/201508/10/20150810221337_PAyNX.jpeg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                mStrings.add("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564567730425&di=b9591af783e80a3be31a3c8ce56cc3a7&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170803%2F3bdb3d68cc6a4e158e3b0e06fb73edc7.gif");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564565865204&di=f8576272a4ec9791fef8b3110d5450af&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160721%2F2791892bc058440f8f814e6ddf8752d3_th.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568321794&di=272769ed1f34e41d2fc6a195d0d2d0e0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fc60593a6ae0a8012193a31b581d.jpg");
                beanone.setPics(mStrings);
                beanone.setName("8图九宫格");
            }
            if (i==8){
                mStrings.clear();
                mStrings.add("http://b-ssl.duitang.com/uploads/blog/201508/10/20150810221337_PAyNX.jpeg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                mStrings.add("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564567730425&di=b9591af783e80a3be31a3c8ce56cc3a7&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170803%2F3bdb3d68cc6a4e158e3b0e06fb73edc7.gif");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564565865204&di=f8576272a4ec9791fef8b3110d5450af&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160721%2F2791892bc058440f8f814e6ddf8752d3_th.jpg");
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568321794&di=272769ed1f34e41d2fc6a195d0d2d0e0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fc60593a6ae0a8012193a31b581d.jpg");
                mStrings.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=244985024,1557281236&fm=15&gp=0.jpg");
                beanone.setPics(mStrings);
                beanone.setName("9图九宫格");
            }
            if (i==9){
                mStrings.clear();
                mStrings.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=244985024,1557281236&fm=15&gp=0.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(500);
                beanone.setHeight(3125);
                beanone.setName("1图超长图，未超最大设置参数500*3125");
            }
            if (i==10){
                mStrings.clear();
                mStrings.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3081291559,147226194&fm=11&gp=0.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(531);
                beanone.setHeight(10000);
                beanone.setName("1图超长图，超最大设置参数531*10000");
            }
            if (i==11){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568321794&di=272769ed1f34e41d2fc6a195d0d2d0e0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fc60593a6ae0a8012193a31b581d.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(4060);
                beanone.setHeight(640);
                beanone.setName("1图超长图，全景图，未超最大设置参数4060*640---6倍多");
            }
            if (i==12){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564736659690&di=0f70f686dbffc3adc7c0a98ac2b83c6b&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170220%2F3bde98f642f546be95b2d242994897aa_th.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(1200);
                beanone.setHeight(501);
                beanone.setName("1图全景图，1200*501--2倍多");
            }
            if (i==13){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(1000);
                beanone.setHeight(667);
                beanone.setName("1图横长方形图，1000*667");
            }
            if (i==14){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(685);
                beanone.setHeight(1024);
                beanone.setName("1图竖长方形图，685*1024");
            }
            if (i==15){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564737382755&di=a8aa087d226927af311efea4a6528f00&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20050519%2FImg225629060.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(3600);
                beanone.setHeight(2025);
                beanone.setName("1图大尺寸图，3600*2025");
            }
            if (i==16){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564737574951&di=9a09f428f439650c85c0fc8a1aaf807c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170927%2F9eaeee8b279c4988a1163dc03a04c5c7.jpeg");
                beanone.setPics(mStrings);
                beanone.setWidth(1600);
                beanone.setHeight(500);
                beanone.setName("1图全景图，1600*500---3倍");
            }
            if (i==17){
                mStrings.clear();
                mStrings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                beanone.setPics(mStrings);
                beanone.setWidth(500);
                beanone.setHeight(500);
                beanone.setName("1图--正方形模式，1图多种设配-500*500");
            }
            listBeans.add(beanone);
        }
        mFirendAdapter.setData(listBeans);
    }

}
