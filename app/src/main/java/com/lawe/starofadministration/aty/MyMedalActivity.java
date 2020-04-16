package com.lawe.starofadministration.aty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.AllMedalFragment;
import com.lawe.starofadministration.fgt.LookAllFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dell
 * date : 2020/4/7 16:36
 * description :我的勋章
 **/

@Layout(R.layout.activity_my_medal)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）

public class MyMedalActivity extends BaseAty {
    private TabLayout tablayout;
    private ViewPager viewPager;

    static final int NUM_ITEMS = 3;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] strings = new String[]{"基础勋章","荣耀勋章","国家级勋章"};

    @Override
    public void initViews() {

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPagerMedal);

        fragmentList.add(new AllMedalFragment());
        fragmentList.add(new AllMedalFragment());
        fragmentList.add(new AllMedalFragment());

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        TabLayoutAdapter fragmentAdater = new  TabLayoutAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdater);
        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {

    }

    public class TabLayoutAdapter extends FragmentPagerAdapter {
        public TabLayoutAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }
    }
}
