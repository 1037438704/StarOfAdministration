package com.lawe.starofadministration.aty;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MyMedalsAdapter;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.MyMedalBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.fgt.BasicsFragment;
import com.lawe.starofadministration.fgt.CountryFragment;
import com.lawe.starofadministration.fgt.GloryFragment;
import com.lawe.starofadministration.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/7 16:36
 * description :我的勋章
 **/

@Layout(R.layout.activity_my_medal)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）

public class MyMedalActivity extends BaseAty {
    private TabLayout tablayout;
    private ViewPager viewPager;

    static final int NUM_ITEMS = 3;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] strings = new String[]{"基础勋章", "荣耀勋章", "国家级勋章"};
    private  TabLayoutAdapter fragmentAdater;
    private ImageView medalBig;
    private TextView number;
    private ImageView back;

    @Override
    public void initViews() {
        super.initViews();

        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPagerMedal);
        medalBig = findViewById(R.id.medal_big);
        number = findViewById(R.id.number);
        back = findViewById(R.id.back);
        fragmentAdater = new TabLayoutAdapter(getSupportFragmentManager());

        getDatas();
    }

    private void getDatas() {
        showPopDialog();
        HttpRequest.GET(me, Constants.MEDALLIST + depUserId, new Parameter(), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    MyMedalBean myMedalBean = gson.fromJson(response, MyMedalBean.class);
                    GlidUtils.defaultGlid(me,myMedalBean.getMap().getNewMedal(),medalBig);
                    number.setText("共"+myMedalBean.getMap().getTotal()+"枚");
                }else {
                    error.getMessage();
                }
            }
        });
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragmentList.add(new BasicsFragment());
        fragmentList.add(new GloryFragment());
        fragmentList.add(new CountryFragment());
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(fragmentAdater);
        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setEvents() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
