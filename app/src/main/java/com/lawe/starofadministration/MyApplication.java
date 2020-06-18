package com.lawe.starofadministration;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.okhttplib.OkHttpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author  fuke
 * @date
 * 功能描述： Application
 */
public class MyApplication extends Application {

    public static Typeface getTextMedium;
    public static Typeface getTextBold;
    public static Typeface getTextRegular;
    public static Typeface getTextNum;

    @Override
    public void onCreate() {
        BaseDialog.unload();
        DialogSettings.init();
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        DialogSettings.theme = DialogSettings.THEME.LIGHT;
        //开启请求日志
        BaseOkHttp.DEBUGMODE = true;
        OkHttpUtil.init(getApplicationContext()).setShowHttpLog(true).build();

        //请求公共链接
        BaseOkHttp.serviceUrl = "http://192.168.0.117:8081/szzw-web";

        //字体
        AssetManager textType = this.getAssets();
        getTextMedium = Typeface.createFromAsset(textType, "fonts/SourceHanSansCN-Medium.otf");
        getTextBold = Typeface.createFromAsset(textType, "fonts/SourceHanSansCN-Bold.otf");
        getTextRegular = Typeface.createFromAsset(textType, "fonts/SourceHanSansCN-Regular.otf");
        getTextNum = Typeface.createFromAsset(textType, "fonts/DINAlternateBold.ttf");

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
