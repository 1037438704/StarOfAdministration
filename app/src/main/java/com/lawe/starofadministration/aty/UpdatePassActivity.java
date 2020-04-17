package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;

/**
 * author : dell
 * date : 2020/4/17 11:19
 * description :  重置密码
 */

@Layout(R.layout.activity_update_pass)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class UpdatePassActivity extends BaseAty {

    private ImageView setPassBack;
    private TextView setPassTitle;

    @Override
    public void initViews() {
        setPassBack = findViewById(R.id.set_pass_back);
        setPassTitle = findViewById(R.id.set_pass_title);

        setPassTitle.setTypeface(getTextMedium);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {
        //返回
        setPassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
