package com.lawe.starofadministration.aty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.FictionAdapter;
import com.lawe.starofadministration.adp.OtherBumenAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/7 16:54
 * description : 人员选择
 **/
@Layout(R.layout.activity_choose_person)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ChoosePersonActivity extends BaseAty {

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
    private androidx.recyclerview.widget.RecyclerView choosePersonRecycle;

    @Override
    public void initViews() {
        initView();

        //设置字体
        choosePersonTijiao.setTypeface(getTextMedium);
        titleText.setTypeface(getTextBold);
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setTypeface(getTextMedium);
        itemChooseBuMenName.setTypeface(getTextMedium);
        titleRight.setText("常用组");

        //获取上一个页面传递的标识
        int flagType = (int) getParameter().get("flagType");
        Log.e("flagType",flagType+"");
        if (flagType == 1){
            titleText.setText("选择审核人");
        }

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

        for (int i = 0; i < 10; i++) {
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
