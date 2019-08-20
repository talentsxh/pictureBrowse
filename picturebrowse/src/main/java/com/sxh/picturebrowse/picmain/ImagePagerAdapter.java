package com.sxh.picturebrowse.picmain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * by sxh
 */

public class ImagePagerAdapter extends FragmentStatePagerAdapter {
    private ViewPager mPager;
    private ArrayList<Fragment> mFragmentList=new ArrayList<>();
    private ArrayList<String> mViewPosition=new ArrayList<>();
    private int mSelectPosition;

    public ImagePagerAdapter(FragmentManager fm, List<String> datas, ViewPager pager, ArrayList<String> viewPosotion, int position) {
        super(fm);
        mPager=pager;
        mPager.setAdapter(this);
        mViewPosition=viewPosotion;
        mSelectPosition=position;
        updateData(datas);
    }


    public void updateData(List<String> dataList) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0, size = dataList.size(); i < size; i++) {
            ImageDetailFragment fragment = ImageDetailFragment.newInstance(dataList.get(i),dataList);
            fragment.setViewPager(mPager);
            fragment.setViewPosition(mViewPosition,mSelectPosition);
            fragments.add(fragment);
        }
        setViewList(fragments);
    }

    private void setViewList(ArrayList<Fragment> fragmentList) {
        if (mFragmentList != null) {
            mFragmentList.clear();
        }
        mFragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragmentList==null?0:mFragmentList.size();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


}