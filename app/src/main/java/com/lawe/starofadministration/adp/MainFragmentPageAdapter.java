package com.lawe.starofadministration.adp;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/7/15
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MainFragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] titleList;

    public MainFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments, String[] titleList) {
        super(fm);
        this.fragments = fragments;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList == null ? "" : titleList[position % titleList.length];
    }
}
