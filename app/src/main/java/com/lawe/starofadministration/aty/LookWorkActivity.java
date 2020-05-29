package com.lawe.starofadministration.aty;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
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
import com.lawe.starofadministration.fgt.work_plan.WorkContextFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkEclosureFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkNewFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkSettingFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkSpeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/26 14:14
 * description : 查看项目/任务
 **/
@Layout(R.layout.activity_look_work)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class LookWorkActivity extends BaseAty {

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
    private String newWorkFlag;
    private LinearLayout titleNewBack;
    private String workType;
    private String personType;
    private android.widget.Button bottomChat;
    private android.widget.EditText bottomWhrit;
    private ImageView bottomPizhu;
    private android.widget.Button bottomButton;
    private LinearLayout draftChat;
    private androidx.recyclerview.widget.RecyclerView draftChatRecycle;
    private LinearLayout draftChatNew;
    private ImageView draftChatNewImg;
    private TextView draftChatNewText;
    private LinearLayout draftChatSet;
    private ImageView draftChatSetImg;
    private TextView draftChatSetText;
    private LinearLayout bottomGongneng;
    private LinearLayout bottomOne;
    private ImageView bottomImg1;
    private TextView bottomChooseper1;
    private LinearLayout bottomPerson;
    private ImageView bottomImg2;
    private TextView bottomChooseper2;
    private android.webkit.WebView workLookWebview;
    private LinearLayout bottomZong;
    private Button workLookWancheng;

    @Override
    public void initViews() {
        initView();

        if (workType.equals("true")){
            titleText.setText("查看项目");
            workLookWebview.setVisibility(View.VISIBLE);
            if (personType.equals("1")){
                bottomZong.setVisibility(View.GONE);
                workLookWancheng.setVisibility(View.VISIBLE);
            }else{
                bottomZong.setVisibility(View.GONE);
            }
        }else{
            titleText.setText("查看任务");
            if (personType.equals("1")){
                bottomGongneng.setVisibility(View.GONE);
            }else{
                bottomChooseper1.setText("申请延期");
                bottomChooseper2.setText("申请退办");
                bottomImg1.setImageResource(R.mipmap.icon_yan_qi);
                bottomImg2.setImageResource(R.mipmap.icon_tui_ban);
            }
        }
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();

        pageCounte = 0;
        draftSpeedOne.setVisibility(View.VISIBLE);
        fragemnts.add(WorkSpeedFragment.newInstance());
        fragemnts.add(WorkContextFragment.newInstance());
        fragemnts.add(WorkEclosureFragment.newInstance());
        fragemnts.add(WorkSettingFragment.newInstance());
        fragemnts.add(WorkNewFragment.newInstance());
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
        //1:创建者   2：执行者    true:项目    flase:任务
        personType = getIntent().getExtras().getString("personType");
        workType = getIntent().getExtras().getString("workType");

        titleNewBack = findViewById(R.id.title_new_back);
        bottomChat = findViewById(R.id.bottom_chat);
        bottomWhrit = findViewById(R.id.bottom_whrit);
        bottomPizhu = findViewById(R.id.bottom_pizhu);
        bottomButton = findViewById(R.id.bottom_button);
        draftChat = findViewById(R.id.draft_chat);
        draftChatRecycle = findViewById(R.id.draft_chat_recycle);
        draftChatNew = findViewById(R.id.draft_chat_new);
        draftChatNewImg = findViewById(R.id.draft_chat_new_img);
        draftChatNewText = findViewById(R.id.draft_chat_new_text);
        draftChatSet = findViewById(R.id.draft_chat_set);
        draftChatSetImg = findViewById(R.id.draft_chat_set_img);
        draftChatSetText = findViewById(R.id.draft_chat_set_text);
        bottomGongneng = findViewById(R.id.bottom_gongneng);
        bottomOne = findViewById(R.id.bottom_one);
        bottomImg1 = findViewById(R.id.bottom_img1);
        bottomChooseper1 = findViewById(R.id.bottom_chooseper1);
        bottomPerson = findViewById(R.id.bottom_person);
        bottomImg2 = findViewById(R.id.bottom_img2);
        bottomChooseper2 = findViewById(R.id.bottom_chooseper2);
        workLookWebview = (WebView) findViewById(R.id.work_look_webview);
        bottomZong = (LinearLayout) findViewById(R.id.bottom_zong);
        workLookWancheng = (Button) findViewById(R.id.work_look_wancheng);
    }
}
