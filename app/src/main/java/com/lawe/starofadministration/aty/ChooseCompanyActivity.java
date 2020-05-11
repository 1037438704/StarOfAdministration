package com.lawe.starofadministration.aty;

import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ChoosePeopleAdapter;
import com.lawe.starofadministration.adp.LookGroupAdapter;
import com.lawe.starofadministration.adp.OtherBumenAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 15:09
 * description : 单位选择
 **/
@Layout(R.layout.activity_choose_company)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ChooseCompanyActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private TextView titleRight;
    private LinearLayout choosePersonSeach;
    private EditText choosePersonSeachEdit;
    private CheckBox itemChooseMyAll;
    private ImageView itemChooseMyBumen;
    private TextView itemChooseBuMenName;
    private TextView itemChooseBuMenNum;
    private ImageView itemChooseGoNext;
    private CheckBox choosePersonSelect;
    private LinearLayout choosePersonAlredySelect;
    private Button choosePersonTijiao;

    //空集合
    private List<String> list;
    private OtherBumenAdapter otherBumenAdapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView choosePersonRecycle;

    @Override
    public void initViews() {
        initView();
        setFinishOnTouchOutside(true);

        //设置字体
        choosePersonTijiao.setTypeface(getTextMedium);
        titleText.setTypeface(getTextBold);
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setTypeface(getTextMedium);
        itemChooseBuMenName.setTypeface(getTextMedium);
        titleRight.setText("常用组");

        titleText.setText("选择处理单位");


        //其他部门列表
        list = new ArrayList<>();
        //待办信息
        layoutManager = new LinearLayoutManager(me);
        choosePersonRecycle.setLayoutManager(layoutManager);
        otherBumenAdapter = new OtherBumenAdapter(R.layout.item_choose_person);
        choosePersonRecycle.setAdapter(otherBumenAdapter);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        otherBumenAdapter.setNewData(list);
        otherBumenAdapter.notifyDataSetChanged();

    }

    @Override
    public void setEvents() {
        //返回
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //查看已选人员
        choosePersonAlredySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.pop_alredy_choose_people, null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                ImageView loginDown = view.findViewById(R.id.login_down);
                RecyclerView popAlredyRecycle = view.findViewById(R.id.pop_alredy_recycle);
                LinearLayoutManager layoutManager = new LinearLayoutManager(me);
                popAlredyRecycle.setLayoutManager(layoutManager);

                //列表
                list = new ArrayList<>();
                //待办信息
                ChoosePeopleAdapter choosePeopleAdapter = new ChoosePeopleAdapter(R.layout.item_choose_company);
                popAlredyRecycle.setAdapter(choosePeopleAdapter);

                for (int i = 0; i < 10; i++) {
                    list.add("" + i);
                }
                choosePeopleAdapter.setNewData(list);
                choosePeopleAdapter.notifyDataSetChanged();


                loginDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
            }
        });

        //查看常用组
        titleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.pop_look_group, null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                RecyclerView popChooseGroup = view.findViewById(R.id.pop_choose_group);
                TextView popTitle = view.findViewById(R.id.pop_title);
                LinearLayout popGuanli = view.findViewById(R.id.pop_guanli);
                Button popCancle = view.findViewById(R.id.pop_cancle);
                //设置字体
                popTitle.setTypeface(getTextMedium);

                LinearLayoutManager layoutManager = new LinearLayoutManager(me);
                popChooseGroup.setLayoutManager(layoutManager);

                //管理常用组
                popGuanli.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jump(GroupMangerActivity.class);
                        dialog.dismiss();
                    }
                });

                //取消
                popCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                //列表
                list = new ArrayList<>();
                //待办信息
                LookGroupAdapter lookGroupAdapter = new LookGroupAdapter(R.layout.item_group);
                popChooseGroup.setAdapter(lookGroupAdapter);

                for (int i = 0; i < 10; i++) {
                    list.add("" + i);
                }
                lookGroupAdapter.setNewData(list);
                lookGroupAdapter.notifyDataSetChanged();

                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
            }
        });
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleRight = findViewById(R.id.title_right);
        choosePersonSeach = findViewById(R.id.choose_person_seach);
        choosePersonSeachEdit = findViewById(R.id.choose_person_seach_edit);
        itemChooseMyAll = findViewById(R.id.item_choose_myAll);
        itemChooseMyBumen = findViewById(R.id.item_choose_myBumen);
        itemChooseBuMenName = findViewById(R.id.item_choose_buMen_name);
        itemChooseBuMenNum = findViewById(R.id.item_choose_buMen_num);
        itemChooseGoNext = findViewById(R.id.item_choose_goNext);
        choosePersonSelect = findViewById(R.id.choose_person_select);
        choosePersonAlredySelect = findViewById(R.id.choose_person_alredy_select);
        choosePersonTijiao = findViewById(R.id.choose_person_tijiao);
        choosePersonRecycle = findViewById(R.id.choose_person_recycle);
    }
}
