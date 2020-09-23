package com.lawe.starofadministration.adp;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.kongzue.baseframework.util.Preferences;
import com.lawe.starofadministration.aty.GuideActivity;
import com.lawe.starofadministration.aty.LoginActivity;

import java.util.List;

/**
 * @author zhaofawu
 * @date 2018/4/18 0018.
 */
public class QuickPageAdapter<T extends View> extends PagerAdapter {
    private List<T> mList;

    public QuickPageAdapter(List<T> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }
}