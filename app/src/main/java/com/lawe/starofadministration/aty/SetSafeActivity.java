package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;

/**
 * author : fuke
 * date : 2020/4/17 10:36
 * description :  安全设置
 */

@Layout(R.layout.activity_set_safe)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class SetSafeActivity extends BaseAty {

    private ImageView setSafeBack;
    private TextView setSafeTitle;
    private ImageView setSafeNone;
    private ImageView setSafeChoose;
    private LinearLayout setSafePass;
    private LinearLayout setSafeConPhone;
    private boolean flag;


    @Override
    public void initViews() {
        setSafeBack = findViewById(R.id.set_safe_back);
        setSafeTitle = findViewById(R.id.set_safe_title);
        setSafeNone = findViewById(R.id.set_safe_none);
        setSafeChoose = findViewById(R.id.set_safe_choose);
        setSafePass = findViewById(R.id.set_safe_pass);
        setSafeConPhone = findViewById(R.id.set_safe_conPhone);

        setSafeTitle.setTypeface(getTextBold);

    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {
        //返回
        setSafeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置密码
        setSafePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(UpdatePassActivity.class);
            }
        });

        //功能是否开启
        setSafeConPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断功能是否开启
                if (flag == true){
                    setSafeChoose.setVisibility(View.VISIBLE);
                    setSafeNone.setVisibility(View.GONE);
                    flag = false;
                }else{
                    setSafeChoose.setVisibility(View.GONE);
                    setSafeNone.setVisibility(View.VISIBLE);
                    flag = true;
                }
            }
        });

    }

}
