package com.lawe.starofadministration.aty;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.DraftChatAdapter;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.ListChatBean;
import com.lawe.starofadministration.fgt.ducha_duban.SuperContextFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperEclosureFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperSettingFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperSpeedFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkContextFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkEclosureFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkSettingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/26 14:14
 * description : 新建项目
 **/
@Layout(R.layout.activity_new_work)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class NewWorkActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;

    private int flag = 1; //更多展开隐藏标识
    private int chatflag = 1; //常用语展开隐藏标识

    private RadioButton rb;
    private  int pageCounte = 0;
    private RadioButton draftSpeedOne;

    @Override
    public void initViews() {
        initView();

        titleText.setText("新建项目");

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();

        pageCounte = 0;
        draftSpeedOne.setVisibility(View.VISIBLE);
        fragemnts.add(WorkContextFragment.newInstance());
        fragemnts.add(WorkEclosureFragment.newInstance());
        fragemnts.add(WorkSettingFragment.newInstance());
        fragemnts.add(SuperSpeedFragment.newInstance());
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
        //返回
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
        draftSpeedOne = findViewById(R.id.draft_speed_one);
        //设置字体
        titleText.setTypeface(getTextBold);

      /*  superFlag = getIntent().getExtras().getString("superFlag");
        typePerson = getIntent().getExtras().getString("typePerson");*/

    }
}
