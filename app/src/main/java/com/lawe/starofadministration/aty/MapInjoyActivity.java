package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.lawe.starofadministration.adp.MapAdapter;
import com.lawe.starofadministration.adp.WorkPlanAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/6/1 11:54
 * description : 共享地图
 **/
@Layout(R.layout.activity_map_injoy)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class MapInjoyActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private EditText searchEdit;
    private LinearLayout titleNew;
    private ImageView titleImg;
    private TextView titleRight;
    private ImageView mapGiveMe;

    //空集合
    private List<String> list;
    private MapAdapter mapAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void initViews() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        searchEdit = findViewById(R.id.search_edit);
        titleNew = findViewById(R.id.title_new);
        titleImg = findViewById(R.id.title_img);
        titleRight = findViewById(R.id.title_right);
        mapGiveMe = findViewById(R.id.map_give_me);

        titleText.setTypeface(getTextMedium);
        titleText.setText("共享地图");
        titleNew.setVisibility(View.VISIBLE);
        titleImg.setVisibility(View.GONE);
        titleRight.setText("共享给他人");
        titleRight.setTextSize(14);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChoosePersonActivity.class, new JumpParameter()
                        .put("flagType", 3)
                );
            }
        });

        mapGiveMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.pop_map, null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                ImageView loginDown = view.findViewById(R.id.login_down);
                RecyclerView mapRecycle = view.findViewById(R.id.pop_map_recycle);
                //列表
                list = new ArrayList<>();
                mapAdapter = new MapAdapter(R.layout.item_map);

                layoutManager = new LinearLayoutManager(me);
                mapRecycle.setLayoutManager(layoutManager);
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
}
