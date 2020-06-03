package com.lawe.starofadministration.aty;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
import com.lawe.starofadministration.adp.MapAdapter;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.work_plan.WorkContextFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkDongFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkEclosureFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkNewFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkSettingFragment;
import com.lawe.starofadministration.fgt.work_plan.WorkSpeedFragment;

import org.xmlpull.v1.XmlPullParser;

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
    private String newWorkFlag;
    private LinearLayout titleNewBack;
    private String workType;
    private String personType;
    private Button bottomChat;
    private EditText bottomWhrit;
    private ImageView bottomPizhu;
    private Button bottomButton;
    private LinearLayout draftChat;
    private RecyclerView draftChatRecycle;
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
    private WebView workLookWebview;
    private LinearLayout bottomZong;
    private Button workLookWancheng;
    private LinearLayout workLookWebLinear;
    private RadioButton radioOne;
    private RadioButton radioTwo;
    private RadioButton radioThree;
    private RadioButton radioFour;
    private RadioButton radioFive;
    private RadioButton radioSix;
    private TextView newWorkShow;

    @Override
    public void initViews() {
        initView();

        if (workType.equals("true")){
            titleText.setText("查看项目");
            radioOne.setText("项目内容");
            radioTwo.setText("项目附件");
            radioThree.setText("项目设置");
            mainRgp.removeView(radioFive);
            mainRgp.removeView(radioSix);
            workLookWebLinear.setVisibility(View.VISIBLE);
            if (personType.equals("1")){
                bottomZong.setVisibility(View.GONE);
                workLookWancheng.setVisibility(View.VISIBLE);
            }else{
                bottomZong.setVisibility(View.GONE);
            }
        }else{
            titleText.setText("查看任务");
            radioOne.setText("任务内容");
            radioTwo.setText("任务附件");
            radioThree.setText("任务设置");
            titleNewBack.setVisibility(View.VISIBLE);
            if (personType.equals("1")){
                bottomZong.setVisibility(View.GONE);
                workLookWancheng.setVisibility(View.VISIBLE);
                workLookWancheng.setText("强制完成任务");
            }else{
                bottomChooseper1.setText("申请延期");
                bottomChooseper2.setText("申请退办");
                bottomImg1.setImageResource(R.mipmap.icon_yan_qi);
                bottomImg2.setImageResource(R.mipmap.icon_tui_ban);
            }
        }

        //activity向fragment传值
        SharedPreferences sharedPreferences1=getSharedPreferences("id",MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences1.edit();
        edit.putString("workType",workType).commit();
        edit.putString("personType",personType).commit();
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();
        if(workType.equals("true")){
            pageCounte = 0;
            //fragemnts.add(WorkSpeedFragment.newInstance());
            fragemnts.add(WorkContextFragment.newInstance());
            fragemnts.add(WorkEclosureFragment.newInstance());
            fragemnts.add(WorkSettingFragment.newInstance());
            fragemnts.add(WorkNewFragment.newInstance());
            rb = (RadioButton) mainRgp.getChildAt(pageCounte);
        }else if(workType.equals("false")){
            pageCounte = 0;
            fragemnts.add(WorkContextFragment.newInstance());
            fragemnts.add(WorkEclosureFragment.newInstance());
            fragemnts.add(WorkSettingFragment.newInstance());
            fragemnts.add(WorkNewFragment.newInstance());
            fragemnts.add(WorkSpeedFragment.newInstance());
            fragemnts.add(WorkDongFragment.newInstance());
            rb = (RadioButton) mainRgp.getChildAt(pageCounte);
        }

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

        //项目数据
        workLookWebLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(WorkDataActivity.class);
            }
        });

        //展开
        newWorkShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.pop_work_show, null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                ImageView loginDown = view.findViewById(R.id.login_down);
                RecyclerView mapRecycle = view.findViewById(R.id.popwork_recycle);
                //列表
                List list = new ArrayList<>();
                MapAdapter mapAdapter = new MapAdapter(R.layout.item_map);
                //到时候修改适配器   现在和地图用的一个
                mapRecycle.setLayoutManager(new LinearLayoutManager(me));
                mapRecycle.setAdapter(mapAdapter);

                for (int i = 0; i < 10; i++) {
                    list.add("" + i);
                }
                mapAdapter.setNewData(list);

                loginDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
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
        workLookWebview = findViewById(R.id.work_look_webview);
        bottomZong = findViewById(R.id.bottom_zong);
        workLookWancheng = findViewById(R.id.work_look_wancheng);
        workLookWebLinear = findViewById(R.id.work_look_web_linear);
        radioOne = findViewById(R.id.radio_one);
        radioTwo = findViewById(R.id.radio_two);
        radioThree = findViewById(R.id.radio_three);
        radioFour =findViewById(R.id.radio_four);
        radioFive = findViewById(R.id.radio_five);
        radioSix = findViewById(R.id.radio_six);
        newWorkShow = (TextView) findViewById(R.id.new_work_show);
    }

}
