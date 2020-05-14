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
 * description :  服务中心
 */

@Layout(R.layout.activity_service)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ServiceActivity extends BaseAty {

    private ImageView setServiceBack;
    private TextView setServiceTitle;
    private TextView setServicePhone;

    @Override
    public void initViews() {
        setServiceBack = findViewById(R.id.set_service_back);
        setServiceTitle = findViewById(R.id.set_service_title);
        setServicePhone = findViewById(R.id.set_service_phone);

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        setServiceTitle.setTypeface(getTextBold);
        setServicePhone.setTypeface(getTextMedium);
    }

    @Override
    public void setEvents() {
        //返回
        setServiceBack.setOnClickListener(view -> finish());
    }

}
