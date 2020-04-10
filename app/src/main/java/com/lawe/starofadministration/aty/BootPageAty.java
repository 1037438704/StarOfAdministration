package com.lawe.starofadministration.aty;

import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.GuidePagerFgt;
import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.aty_boot_page)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）

public class BootPageAty extends BaseAty {
    private ViewPagerAdp adaper;
    private List<BaseFragment> fragments = new ArrayList<>();
    ViewPager viewPager;
    LinearLayout llIndicator;
    @Override
    public void initViews() {
        viewPager = findViewById(R.id.viewPager);
        llIndicator = findViewById(R.id.ll_indicator);
        for (int i = 0; i < 3; i++) {
            fragments.add(GuidePagerFgt.newInstance(i));
        }
        adaper = new ViewPagerAdp(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adaper);

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        initlndicator();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int index, float v, int i1) {
                for (int i = 0; i < fragments.size(); i++) {
                    llIndicator.getChildAt(i).setBackgroundResource(index == i ? R.drawable.dot_focus : R.drawable.dot_normal);
                }
                if (index == 2){
                    llIndicator.setVisibility(View.GONE);
                }else {
                    llIndicator.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPageSelected(int i) {
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void setEvents() {

    }

    private void initlndicator() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.setMargins(10, 0, 10, 0);
        for (int i = 0; i < fragments.size(); i++) {
            View view = new View(this);
            view.setId(i);
            view.setBackgroundResource(i == 0 ? R.drawable.dot_focus : R.drawable.dot_normal);
            view.setLayoutParams(lp);
            llIndicator.addView(view, i);
            if (i == 2){
                llIndicator.setVisibility(View.GONE);
            }else {
                llIndicator.setVisibility(View.VISIBLE);
            }
        }
    }

}
