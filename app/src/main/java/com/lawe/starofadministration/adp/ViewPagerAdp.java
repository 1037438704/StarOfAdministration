package com.lawe.starofadministration.adp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.kongzue.baseframework.BaseFragment;

import java.util.List;

public class ViewPagerAdp extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public ViewPagerAdp(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdp(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
