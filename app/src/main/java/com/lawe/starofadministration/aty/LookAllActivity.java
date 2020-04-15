package com.lawe.starofadministration.aty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.LookAllFragment;

import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.activity_look_all)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class LookAllActivity extends BaseAty {

    private TextView textName;


    private LinearLayout textChoose;
    private DrawerLayout drawerLayout;
    private Button drawer_quxiao;

    private TabLayout tablayout;
    private ViewPager viewPager;

    static final int NUM_ITEMS = 6;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] strings = new String[]{"全部日程","紧急事项","会议会晤","个人日程","督查事项","工作计划"};

    @Override
    public void initViews() {

        textName = (TextView) findViewById(R.id.text_name);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        textChoose = (LinearLayout) findViewById(R.id.text_choose);
        drawerLayout = findViewById(R.id.drawer_all_day);
        drawer_quxiao = findViewById(R.id.drawer_quxiao1);

        fragmentList.add(new LookAllFragment());
        fragmentList.add(new LookAllFragment());
        fragmentList.add(new LookAllFragment());
        fragmentList.add(new LookAllFragment());
        fragmentList.add(new LookAllFragment());
        fragmentList.add(new LookAllFragment());

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
        //筛选弹出pop
        textChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

       drawer_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
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
