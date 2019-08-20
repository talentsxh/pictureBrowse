package com.sxh.demo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sxh.demo.R;
import com.sxh.demo.beans.MenuBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/4.
 */

public class LinearPicsAdapter extends RecyclerView.Adapter<LinearPicsAdapter.ViewHolder> {
    private List<MenuBean.DataEntity> data=new ArrayList<>();
    private Context mContext;
    private int width;
    private Map<Integer, ImageView> mCaches = new HashMap<>();

    public LinearPicsAdapter(Context context) {
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
    public LinearPicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_menu_item, parent, false);
        LinearPicsAdapter.ViewHolder viewHolder = new LinearPicsAdapter.ViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        RequestOptions options=new RequestOptions();
        options.placeholder(R.mipmap.not_pic);
        options.error(R.mipmap.not_pic);
        Glide.with(mContext).load(data.get(position).getImg()).apply(options).into(holder.icon);
        holder.name.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mItemClickListeners!=null){
                   mItemClickListeners.onClickListener(position);
               }
            }
        });
        mCaches.put(position, holder.icon);
    }


    public ImageView getItemView(int position) {
        return mCaches.get(position);
    }

    public List<ImageView> getAllView(){
        List<ImageView> list=new ArrayList<>();
        list.clear();
        for (int i=0;i<mCaches.size();i++){
            list.add(mCaches.get(i));
        }
        return list;
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
        private ImageView icon;
        private TextView name;

        public ViewHolder(View itemView)
        {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.index_menu_icon);
            name = (TextView) itemView.findViewById(R.id.index_menu_name);
        }
    }

    private OnItemClickListeners mItemClickListeners;

    public void setItemClickListeners(OnItemClickListeners itemClickListeners) {
        mItemClickListeners = itemClickListeners;
    }

    public interface OnItemClickListeners{
        void onClickListener(int position);
    }
}
