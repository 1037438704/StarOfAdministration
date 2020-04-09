package com.lawe.starofadministration;

import android.app.Application;
import android.content.Context;

import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author
 * @date
 * 功能描述：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        BaseDialog.unload();
        DialogSettings.init();
        //DialogSettings.checkRenderscriptSupport(this);
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

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
