package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;


/**
 * @author Nine tail Fox
 * @date
 * 功能描述：
 * 联系方式：1037438704@qq.com
 */
@Layout(R.layout. aty_splash)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class SplashAty extends BaseAty {

    private LinearLayout framgentLayout;
    @Override
    public void initViews() {
        Preferences.getInstance().commit(me, "page", "decide", true);
        Preferences.getInstance().getBoolean(me,"page","decide");
        framgentLayout = findViewById(R.id.fragment_lauout);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {

    }

    /**
     * 防止用户返回键退出APP
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK
                || keyCode == KeyEvent.KEYCODE_HOME
                || super.onKeyDown(keyCode, event);
    }

}
