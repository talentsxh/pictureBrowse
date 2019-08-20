package com.sxh.demo.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxh.demo.R;
import com.sxh.demo.adapters.PicsAdapter;
import com.sxh.demo.beans.MenuBean;
import com.sxh.picturebrowse.picutils.AllUtils;
import com.sxh.picturebrowse.picutils.ToastAlert;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PicsAdapter mPicsAdapter;
    private List<MenuBean.DataEntity> listBeans = new ArrayList<>();
    private ArrayList<String> mStrings=new ArrayList<>();
    private GridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        initViews();
        initVars();
    }



    private void initViews() {
        mRecyclerView=findViewById(R.id.recycle_view);
        mRecyclerView.setNestedScrollingEnabled(false);
        layoutManager=new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        mPicsAdapter =new PicsAdapter(this);
        mRecyclerView.setAdapter(mPicsAdapter);
    }

    private void initVars() {
        for (int i=0;i<15;i++){
            MenuBean.DataEntity beanone = new MenuBean.DataEntity();
            switch (i){
                case 0:
                    beanone.setImg("http://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
                    beanone.setName("超大图"+i);
                    listBeans.add(beanone);
                    break;
                case 1:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568755349&di=60dd43f7de6d1fd9f0d748de71f7e00a&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201203_500x375%2F997%2F15a702c1623ef9bf.jpg");
                    beanone.setName("普通正方形图"+i);
                    listBeans.add(beanone);
                    break;
                case 2:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564567730425&di=b9591af783e80a3be31a3c8ce56cc3a7&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170803%2F3bdb3d68cc6a4e158e3b0e06fb73edc7.gif");
                    beanone.setName("长动图"+i);
                    listBeans.add(beanone);
                    break;
                case 3:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564478741655&di=1da7aea4a536f216e289d6b2ca1a7f1d&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F22000%2Fa21999018_01000.jpg");
                    beanone.setName("普通图宽大于高"+i);
                    listBeans.add(beanone);
                    break;
                case 4:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564565865204&di=f8576272a4ec9791fef8b3110d5450af&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160721%2F2791892bc058440f8f814e6ddf8752d3_th.jpg");
                    beanone.setName("普通动图"+i);
                    listBeans.add(beanone);
                    break;
                case 5:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564568321794&di=272769ed1f34e41d2fc6a195d0d2d0e0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fc60593a6ae0a8012193a31b581d.jpg");
                    beanone.setName("未超宽长图"+i);
                    listBeans.add(beanone);
                    break;
                case 6:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564480167466&di=5fc62da04efbd6e88c521ec1ca2bb24c&imgtype=0&src=http%3A%2F%2Fpic32.nipic.com%2F20130822%2F11918471_133412467197_2.jpg");
                    beanone.setName("普通高大于宽图"+i);
                    listBeans.add(beanone);
                    break;
                case 7:
                    beanone.setImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=244985024,1557281236&fm=15&gp=0.jpg");
                    beanone.setName("未超高度长图"+i);
                    listBeans.add(beanone);
                    break;
                case 8:
                    beanone.setImg("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3081291559,147226194&fm=11&gp=0.jpg");
                    beanone.setName("超高度长图"+i);
                    listBeans.add(beanone);
                    break;
                case 9:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564983117376&di=128945e4209543409b62fe777b295df2&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20141219%2Fzhongguofengdaodeliyizhanbanzhijing_3744115.jpg");
                    beanone.setName("普通chang方形图"+i);
                    listBeans.add(beanone);
                    break;
                case 10:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564983146059&di=194114cbc3f65c98b6f8ad0c7191f1f6&imgtype=0&src=http%3A%2F%2Fpic23.nipic.com%2F20120830%2F9686992_180336646144_2.jpg");
                    beanone.setName("ss图"+i);
                    listBeans.add(beanone);
                    break;
                case 11:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564983378594&di=bb9820d7ea57b3606f6f1dcd6e54aa5d&imgtype=0&src=http%3A%2F%2Fs9.sinaimg.cn%2Fmiddle%2F648e0087ga73503fef3d8%26690");
                    beanone.setName("普通图宽大于高"+i);
                    listBeans.add(beanone);
                    break;
                case 12:
                    beanone.setImg("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3344793769,523805821&fm=26&gp=0.jpg");
                    beanone.setName("普通动图"+i);
                    listBeans.add(beanone);
                    break;
                case 13:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564983293145&di=29976df5a6a41b7bfaa3fb8ea30ee21b&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130315%2F11511914_151013608193_2.jpg");
                    beanone.setName("未超宽长图"+i);
                    listBeans.add(beanone);
                    break;
                case 14:
                    beanone.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564983146058&di=a5f52c806ec42d483aa4af4cea009a5c&imgtype=0&src=http%3A%2F%2Fpic51.nipic.com%2Ffile%2F20141025%2F8649940_220505558734_2.jpg");
                    beanone.setName("普通高大于宽图"+i);
                    listBeans.add(beanone);
                    break;
            }



        }
        mPicsAdapter.setData(listBeans);

        mStrings.clear();
        for (int i=0;i<listBeans.size();i++){
            mStrings.add(listBeans.get(i).getImg());
        }



        mPicsAdapter.setItemClickListeners(new PicsAdapter.OnItemClickListeners() {
            @Override
            public void onClickListener(int position) {
                AllUtils.startImagePage(RecycleActivity.this,mStrings,mPicsAdapter.getAllView(), position);
                AllUtils.setBackListeners(new AllUtils.BackListeners() {
                    @Override
                    public void backMethod(int arg0) {
                        ToastAlert.showMsg(arg0+"");
                        if (layoutManager!=null){
                            layoutManager.scrollToPosition(arg0);
                        }
                    }
                });
            }
        });

    }
}
