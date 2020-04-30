package com.lawe.starofadministration.aty;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
import com.lawe.starofadministration.fgt.CompanyFragment;
import com.lawe.starofadministration.fgt.DeductionFragment;
import com.lawe.starofadministration.fgt.DocumentEditFragment;
import com.lawe.starofadministration.fgt.EnclosureCatalogFragment;
import com.lawe.starofadministration.fgt.LookDataFragment;
import com.lawe.starofadministration.fgt.PersonFragment;
import com.lawe.starofadministration.fgt.SetMessageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/30 15:54
 * description : 公文起草
 **/
@Layout(R.layout.activity_draft)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class DraftActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    private ImageView titleMore;

    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;

    @Override
    public void initViews() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        titleMore = findViewById(R.id.title_more);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);

        //设置字体
        titleText.setText("起草公文");
        titleText.setTypeface(getTextBold);
        titleMore.setVisibility(View.VISIBLE);

        fragemnts = new ArrayList<>();
        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
        rb.setTypeface(getTextMedium);

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts.add(DocumentEditFragment.newInstance());
        fragemnts.add(EnclosureCatalogFragment.newInstance());
        fragemnts.add(SetMessageFragment.newInstance());

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
                    }else{
                        rb.setTypeface(getTextRegular);
                    }
                }
            }
        });

    }

}