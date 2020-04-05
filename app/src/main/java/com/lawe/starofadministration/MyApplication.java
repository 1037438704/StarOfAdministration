package com.lawe.starofadministration;

import android.app.Application;

import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;

/**
 * @author NineTailDemonFox
 * @date
 * 功能描述：
 * 联系方式：1037438704@qq.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        BaseDialog.unload();
        DialogSettings.init();
//        DialogSettings.checkRenderscriptSupport(this);
//        DialogSettings.DEBUGMODE = true;
//        DialogSettings.isUseBlur = true;
//        DialogSettings.autoShowInputKeyboard = true;
        //DialogSettings.backgroundColor = Color.BLUE;
        //DialogSettings.titleTextInfo = new TextInfo().setFontSize(50);
        //DialogSettings.buttonPositiveTextInfo = new TextInfo().setFontColor(Color.GREEN);
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        DialogSettings.theme = DialogSettings.THEME.LIGHT;
        //开启请求日志
        BaseOkHttp.DEBUGMODE = true;
        super.onCreate();
    }
}
