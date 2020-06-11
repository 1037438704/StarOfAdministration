package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.LookGroupAdapter;
import com.lawe.starofadministration.adp.OtherBumenAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;


/**
 * author : fuke
 * date : 2020/5/8 15:54
 * description : 常用组管理
 **/
@Layout(R.layout.activity_group_manger)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class GroupMangerActivity extends BaseAty {

    //空集合
    private List<String> list;
    private OtherBumenAdapter otherBumenAdapter;
    private android.widget.ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleMore;
    private LinearLayout titleNew;
    private TextView titleRight;
    private LinearLayout titleButton1;
    private Button titleButton2;
    private RecyclerView groupRecycle;
    private LinearLayout groupDelete;
    private TextView draftChatSetText;
    private LinearLayout groupNew;
    private TextView draftChatNewText;
    private  LookGroupAdapter lookGroupAdapter;

    @Override
    public void initViews() {
        super.initViews();
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleMore = findViewById(R.id.title_more);
        titleNew = findViewById(R.id.title_new);
        titleRight = findViewById(R.id.title_right);
        titleButton1 = findViewById(R.id.title_button1);
        titleButton2 = findViewById(R.id.title_button2);
        groupRecycle = findViewById(R.id.group_recycle);
        groupDelete = findViewById(R.id.group_delete);
        draftChatSetText = findViewById(R.id.draft_chat_set_text);
        groupNew = findViewById(R.id.group_new);
        draftChatNewText = findViewById(R.id.draft_chat_new_text);
        groupRecycle.setLayoutManager(new LinearLayoutManager(me));
        lookGroupAdapter = new LookGroupAdapter(R.layout.item_group);
        //列表
        list = new ArrayList<>();
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        titleButton1.setVisibility(View.VISIBLE);
        titleText.setText("管理常用组");
        titleText.setTypeface(getTextBold);
        draftChatSetText.setTypeface(getTextMedium);
        draftChatNewText.setTypeface(getTextMedium);

        //待办信息
        groupRecycle.setAdapter(lookGroupAdapter);
        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        lookGroupAdapter.setNewData(list);
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

        //新建常用组
        groupNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(me);
                View view = View.inflate(me, R.layout.pop_add_group, null);
                builder.setView(view);
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();

                TextView popAddTitle = view.findViewById(R.id.pop_add_group_title);
                EditText popAddEdit = view.findViewById(R.id.pop_add_group_edit);
                Button popAddCancle = view.findViewById(R.id.pop_add_group_cancle);
                Button popAddSure = view.findViewById(R.id.pop_add_group_sure);

                //设置字体
                popAddTitle.setTypeface(getTextMedium);
                popAddSure.setTypeface(getTextMedium);

                //取消的点击
                popAddCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        });
        //删除常用组
        groupDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    //显示dialog
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(me);
        View view = View.inflate(me, R.layout.pop_delete_group, null);
        builder.setView(view);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();

        TextView popDeleteTitle = view.findViewById(R.id.pop_delete_group_title);
        TextView popDeleteName = view.findViewById(R.id.pop_delete_group_name);
        Button popDeleteCancle = view.findViewById(R.id.pop_delete_group_cancle);
        Button popDeleteSure = view.findViewById(R.id.pop_delete_group_sure);

        //设置字体
        popDeleteTitle.setTypeface(getTextMedium);
        popDeleteName.setTypeface(getTextMedium);
        popDeleteSure.setTypeface(getTextMedium);

        //取消的点击
        popDeleteCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }




}
