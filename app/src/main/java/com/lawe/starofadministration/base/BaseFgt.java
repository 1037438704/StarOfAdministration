package com.lawe.starofadministration.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.listener.ResponseInterceptListener;
import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.map.JSONUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

abstract public class BaseFgt extends BaseFragment implements Constants {

    public AppCompatActivity fgtContext;
    protected Dialog dialog;
    public Typeface getTextMedium = MyApplication.getTextMedium;
    public Typeface getTextRegular = MyApplication.getTextRegular;
    public Typeface getTextBold = MyApplication.getTextBold;
    public Typeface getTextNum = MyApplication.getTextNum;
    public Gson gson = new Gson();

    public String token;
    public String depUserId;
    public String departmentId;
    public String name;
    public String departFullName;
    public String time;

    @Override
    public void initViews() {
        interceptDate();
        //getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);15600057920
        fgtContext = (AppCompatActivity) getActivity();
        token = Preferences.getInstance().getString(getActivity(),"login","token");
        depUserId = Preferences.getInstance().getString(getActivity(),"login","depUserId");
        departmentId = Preferences.getInstance().getString(getActivity(),"login","departmentId");
        name = Preferences.getInstance().getString(getActivity(),"login","name");
        departFullName = Preferences.getInstance().getString(getActivity(),"login","departFullName");
    }

    @Override
    public void onResume() {
        super.onResume();
        token = Preferences.getInstance().getString(me,"login","token");
        depUserId = Preferences.getInstance().getString(me,"login","depUserId");
        departmentId = Preferences.getInstance().getString(me,"login","departmentId");
        name = Preferences.getInstance().getString(me,"login","name");
        departFullName = Preferences.getInstance().getString(me,"login","departFullName");
    }

    //获取当前时间
    public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        time = formatter.format(curDate);
        return time;
    }

    //网络请求数据拦截器
    public void interceptDate(){
        BaseOkHttp.responseInterceptListener = new ResponseInterceptListener() {
            @Override
            public boolean onResponse(Context context, String url, String response, Exception error) {
                if (error == null) {
                    Map<String, String> data = JSONUtils.parseKeyAndValueToMap(response);
                    //判空
                    if (isNull(data.toString())){
                        toast("请求超时");
                        return false;
                    }
                    if (isNull(data.get("msg"))){
                        toast("数据为空");
                        return false;
                    }
                    if (data.get("msg").equals("success")){
                        return true;
                    }else{
                        toast(data.get("msg"));
                        return false;
                    }
                } else {
                    error.getMessage();
                    return false;
                }
            }
        };
    }

    public void showPopDialog() {
        if ((!me.isFinishing()) && (dialog == null || !dialog.isShowing())) {
            dialog = new Dialog(me, R.style.MyDialogStyle);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setDimAmount(0f);
            dialog.setContentView(R.layout.core_center_loading);
            dialog.setCancelable(true);
            dialog.show();
        }
    }
    public void endLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
