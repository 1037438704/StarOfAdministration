package com.lawe.starofadministration.fgt;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.adp.ViewPagerFragmentAdp;
import com.lawe.starofadministration.aty.ScanningActivity;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dell
 * date : 2020/4/7 16:36
 * description :待办
 **/

@Layout(R.layout.fgt_dealt)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class DealtFragment extends BaseFgt {

    private RadioGroup mainRgp;
    private ViewPager viewPager;
    ViewPagerFragmentAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;
    private ImageView saoyisao;

    @Override
    public void initViews() {
        fragemnts = new ArrayList<>();
        mainRgp = (RadioGroup) findViewById(R.id.main_rgp);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        saoyisao = (ImageView) findViewById(R.id.saoyisao);
        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
    }

    @Override
    public void initDatas() {
        fragemnts.add(MessageFragment.newInstance());
        fragemnts.add(NoticeFragment.newInstance());
        viewPagerAdp = new ViewPagerFragmentAdp(getChildFragmentManager(), fragemnts);
        viewPager.setOffscreenPageLimit(fragemnts.size());
        viewPager.setAdapter(viewPagerAdp);

    }

    @Override
    public void setEvents() {
        //viewPager的滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //获取当前位置的RadioButton
                RadioButton rb = (RadioButton) mainRgp.getChildAt(position);
                //设置为true
                rb.setChecked(true);
            }
        });
        //RadioGroup的事件监听
        mainRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < mainRgp.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) mainRgp.getChildAt(i);
                    if (rb.isChecked()) {
                        viewPager.setCurrentItem(i, false);
                    }
                }
            }
        });

        //扫一扫跳转
        saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ScanningActivity.class);
            }
        });
    }

    public static DealtFragment newInstance() {
        return new DealtFragment();
    }
}
