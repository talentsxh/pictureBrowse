package com.sxh.demo.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxh.demo.R;
import com.sxh.demo.beans.MenuBean;
import com.sxh.demo.views.MultiImageView;
import com.sxh.picturebrowse.picutils.AllUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/9/4.
 */

public class FirendAdapter extends RecyclerView.Adapter<FirendAdapter.ViewHolder> {
    private List<MenuBean.DataEntity> data=new ArrayList<>();
    private Context mContext;

    public FirendAdapter(Context context) {
        mContext=context;
    }

    public List<MenuBean.DataEntity> getData() {
        return data;
    }

    public void setData(List<MenuBean.DataEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public FirendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        FirendAdapter.ViewHolder viewHolder = new FirendAdapter.ViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuBean.DataEntity bean=data.get(position);
        if (bean==null){
            return;
        }
        holder.icon.setList(bean.getPics(),bean.getWidth(),bean.getHeight());
        //holder.icon.setList(bean.getPics(),400,400);
        holder.icon.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ImageView[] imageViews) {
                AllUtils.startImagePage((Activity) mContext, bean.getPics(), Arrays.asList(imageViews),position);
            }
        });

        holder.name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (data!=null) {
            return data.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MultiImageView icon;
        private TextView name;

        public ViewHolder(View itemView)
        {
            super(itemView);
            icon = (MultiImageView) itemView.findViewById(R.id.multi_image);
            name = (TextView) itemView.findViewById(R.id.friend_name);
        }
    }
}
