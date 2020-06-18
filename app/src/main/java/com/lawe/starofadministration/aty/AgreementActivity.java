package com.lawe.starofadministration.aty;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.lawe.starofadministration.fgt.gongwen_nizhi.JoinContextFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.JoinEclosureFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.JoinSpeedFragment;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/2 15:22
 * description : 协议
 */
@Layout(R.layout.activity_agreement)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class AgreementActivity extends BaseAty {

    private ImageView loginDown;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    private Button loginJujue;
    private Button loginAgree;
    List<BaseFragment> fragemnts;
    ViewPagerAdp viewPagerAdp;

    @Override
    public void initViews() {
        initView();
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts.add(JoinContextFragment.newInstance());
        fragemnts.add(JoinEclosureFragment.newInstance());
        fragemnts.add(JoinSpeedFragment.newInstance());

        viewPagerAdp = new ViewPagerAdp(me.getSupportFragmentManager(), fragemnts);
        viewPagerData.setOffscreenPageLimit(fragemnts.size());
        viewPagerData.setAdapter(viewPagerAdp);
    }

    @Override
    public void setEvents() {
        //viewPager的滑动监听
        viewPagerData.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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
                        viewPagerData.setCurrentItem(i, false);
                        rb.setTypeface(getTextMedium);
                    } else {
                        rb.setTypeface(getTextRegular);
                    }
                }
            }
        });
    }

    private void initView() {
        loginDown = findViewById(R.id.login_down);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
        loginJujue = findViewById(R.id.login_jujue);
        loginAgree = findViewById(R.id.login_agree);
    }
}
