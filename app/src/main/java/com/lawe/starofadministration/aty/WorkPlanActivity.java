package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.SupervisionAdapter;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.ducha_duban.SuperContextFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperEclosureFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperSettingFragment;
import com.lawe.starofadministration.fgt.work_plan.CompleteFragment;
import com.lawe.starofadministration.fgt.work_plan.ConductFragment;
import com.lawe.starofadministration.fgt.work_plan.MyCreatFragment;
import com.lawe.starofadministration.fgt.work_plan.ToDoFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/28 14:14
 * description : 工作计划列表
 **/
@Layout(R.layout.activity_work_plan)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class WorkPlanActivity extends BaseAty {

    private DrawerLayout workDrawer;
    private ImageView titleBack;
    private TextView titleText;
    private ImageView titleMore;
    private LinearLayout titleNew;
    private TextView titleRight;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RadioGroup mainRgp;
    private RadioButton rb;
    private ViewPager viewPagerData;
    private ImageView workTop;
    private RelativeLayout fictionDrawerlayout;
    private TextView fictionChoose;
    private ImageView speedImgLeibie;
    private Button drawerQuxiao;

    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;

    @Override
    public void initViews() {
        super.initViews();
        workDrawer = findViewById(R.id.work_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleMore = findViewById(R.id.title_more);
        titleNew = findViewById(R.id.title_new);
        titleRight = findViewById(R.id.title_right);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
        workTop = findViewById(R.id.work_top);
        fictionDrawerlayout = findViewById(R.id.fiction_drawerlayout);
        fictionChoose = findViewById(R.id.fiction_choose);
        speedImgLeibie = findViewById(R.id.speed_img_leibie);
        drawerQuxiao = findViewById(R.id.drawer_quxiao);

        titleNew.setVisibility(View.VISIBLE);
        titleText.setText("工作计划");
        titleText.setTypeface(getTextMedium);
        titleRight.setText("新建项目");
        searchEdit.setHint("请输入关键词");

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();
        int pageCounte = 0;
        fragemnts.add(ToDoFragment.newInstance());
        fragemnts.add(ConductFragment.newInstance());
        fragemnts.add(CompleteFragment.newInstance());
        fragemnts.add(MyCreatFragment.newInstance());
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

        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workDrawer.openDrawer(Gravity.RIGHT);
            }
        });

        //新建项目
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(NewWorkActivity.class, new JumpParameter()
                        .put("newWorkFlag","1")
                     );
            }
        });
    }

}
