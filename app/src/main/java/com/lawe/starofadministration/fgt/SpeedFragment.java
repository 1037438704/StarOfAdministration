package com.lawe.starofadministration.fgt;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/7 16:36
 * description :
 */
@Layout(R.layout.fgt_speed)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class SpeedFragment extends BaseFgt {
    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static SpeedFragment newInstance() {
        return new SpeedFragment();
    }
}
