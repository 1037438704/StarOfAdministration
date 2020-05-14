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
 * author : fuke
 * date : 2020/4/17 13:18
 * description :  操作中心
 */

@Layout(R.layout.activity_explana)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ExplanaActivity extends BaseAty {
    private ImageView setExplanationBack;
    private TextView setExplanationTitle;
    private TextView setExplanationShuoming;
    private TextView setServicePhone;

    @Override
    public void initViews() {
        setExplanationBack = findViewById(R.id.set_explanation_back);
        setExplanationTitle = findViewById(R.id.set_explanation_title);
        setExplanationShuoming = findViewById(R.id.set_explanation_shuoming);
        setServicePhone = findViewById(R.id.set_service_phone);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        setExplanationTitle.setTypeface(getTextBold);
        setExplanationShuoming.setTypeface(getTextMedium);
        setServicePhone.setTypeface(getTextMedium);
    }

    @Override
    public void setEvents() {
        //返回
        setExplanationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
