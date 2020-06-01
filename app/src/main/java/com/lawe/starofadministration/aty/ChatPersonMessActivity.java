package com.lawe.starofadministration.aty;

import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.chat.ChatDataFragment;
import com.lawe.starofadministration.fgt.chat.ChatPersonFragment;
import com.lawe.starofadministration.fgt.chat.ChatxunzhangFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/6/3 15:22
 * description : 个人信息
 */
@Layout(R.layout.activity_chat_person_mess)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ChatPersonMessActivity extends BaseAty {

    private ImageView titleBack;
    private TextView chatName;
    private LinearLayout titleMore;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    private List<BaseFragment> fragemnts;
    private RadioButton rb;
    private  int pageCounte = 0;
    ViewPagerAdp viewPagerAdp;

    @Override
    public void initViews() {
        titleBack = findViewById(R.id.title_back);
        chatName = findViewById(R.id.chat_name);
        titleMore = findViewById(R.id.title_more);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);

        chatName.setTypeface(getTextBold);

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();

        pageCounte = 0;
        fragemnts.add(ChatPersonFragment.newInstance());
        fragemnts.add(ChatDataFragment.newInstance());
        fragemnts.add(ChatxunzhangFragment.newInstance());
        rb = (RadioButton) mainRgp.getChildAt(pageCounte);
        rb.setChecked(true);
        //字体
        rb.setTypeface(getTextMedium);
        viewPagerAdp = new ViewPagerAdp(me.getSupportFragmentManager(), fragemnts);
        viewPagerData.setOffscreenPageLimit(fragemnts.size());
        viewPagerData.setAdapter(viewPagerAdp);
        viewPagerData.setCurrentItem(pageCounte);

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

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
