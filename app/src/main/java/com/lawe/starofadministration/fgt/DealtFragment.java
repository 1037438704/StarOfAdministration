package com.lawe.starofadministration.fgt;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerFragmentAdp;
import com.lawe.starofadministration.aty.MyMedalActivity;
import com.lawe.starofadministration.aty.ScanningActivity;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/7 16:36
 * description : 首页-待办
 **/

@Layout(R.layout.fgt_dealt)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class DealtFragment extends BaseFgt {

    private RadioGroup mainRgp;
    private ViewPager viewPager;
    ViewPagerFragmentAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;
    private ImageView saoyisao;
    private ImageView daiban_head;
    private DrawerLayout draw_person;
    LinearLayout dealtLinerarLayout;
    private RelativeLayout my_allXun;
    private LinearLayout linear_myXunzhang;
    private RadioButton dealtMessage;
    private RadioButton dealtNotice;

    @Override
    public void initViews() {
        fragemnts = new ArrayList<>();
        mainRgp = (RadioGroup) findViewById(R.id.main_rgp);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dealtMessage = (RadioButton) findViewById(R.id.dealt_message);
        dealtNotice = (RadioButton) findViewById(R.id.dealt_notice);
        saoyisao = (ImageView) findViewById(R.id.saoyisao);
        daiban_head = (ImageView) findViewById(R.id.daiban_head);
        draw_person = getActivity().findViewById(R.id.drawer_layout_shaixuan);
        my_allXun = getActivity().findViewById(R.id.my_xunzhang);
        linear_myXunzhang = getActivity().findViewById(R.id.linear_myXunzhang);
        dealtLinerarLayout = (LinearLayout) findViewById(R.id.dealt_linerar_layout);
        dealtLinerarLayout.setPadding(0
        ,me.getStatusBarHeight(),0,0);

        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
        rb.setTypeface(getTextMedium);
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
                rb.setTypeface(getTextMedium);
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
                        rb.setTypeface(getTextMedium);
                    }else{
                        rb.setTypeface(getTextRegular);
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

        //点击头像滑出左侧菜单栏
        daiban_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw_person.openDrawer(Gravity.LEFT);
            }
        });

        //我的勋章
        my_allXun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(MyMedalActivity.class);
            }
        });

        linear_myXunzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(MyMedalActivity.class);
            }
        });
    }

    public static DealtFragment newInstance() {
        return new DealtFragment();
    }

}
