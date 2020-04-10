package com.lawe.starofadministration.fgt;

import androidx.fragment.app.Fragment;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/8 10:23
 * description :待办----通知信息
 */
@Layout(R.layout.fgt_notice)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class NoticeFragment extends BaseFgt {

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static NoticeFragment newInstance() {
        return new NoticeFragment();
    }
}