package com.lawe.starofadministration.base;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.listener.ResponseInterceptListener;
import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.map.JSONUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;


abstract public class BaseAty extends BaseActivity implements Constants {
    public Typeface getTextMedium = MyApplication.getTextMedium;
    public Typeface getTextRegular = MyApplication.getTextRegular;
    public Typeface getTextBold = MyApplication.getTextBold;
    public Typeface getTextNum = MyApplication.getTextNum;

    public Gson gson = new Gson();
    protected Dialog dialog;

    //权限申请回调
    private OnPermissionResponseListener onPermissionResponseListener;
    private int REQUEST_CODE_PERMISSION = 0x00099;

    public String token;
    public String depUserId;
    public String departmentId;
    public String name;
    public String departFullName;
    public String time;

    //所有的页面都要加
    @Override
    public void initViews() {
        interceptDate();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        token = Preferences.getInstance().getString(me,"login","token");
        depUserId = Preferences.getInstance().getString(me,"login","depUserId");
        departmentId = Preferences.getInstance().getString(me,"login","departmentId");
        name = Preferences.getInstance().getString(me,"login","name");
        departFullName = Preferences.getInstance().getString(me,"login","departFullName");
    }

    @Override
    protected void onResume() {
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


    /**
     * 点击页面空白处时，让键盘消失
     *
     * @param event 事件
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                if (getCurrentFocus().getWindowToken() != null) {
                    mInputMethodManager.hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v     View
     * @param event 事件
     * @return boolean
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token1 Ibinder
     */
    private void hideKeyboard(IBinder token1) {
        if (token1 != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token1, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public static void disableShowSoftInput(EditText editText) {
        Class<EditText> cls = EditText.class;
        Method method;
        try {
            method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            method.setAccessible(true);
            method.invoke(editText, false);
        } catch (Exception e) {
            // TODO: 2018/8/27 处理错误
        }

    }
    /**
     * 请求权限
     * <p>
     * 警告：此处除了用户拒绝外，唯一可能出现无法获取权限或失败的情况是在AndroidManifest.xml中未声明权限信息
     * Android6.0+即便需要动态请求权限（重点）但不代表着不需要在AndroidManifest.xml中进行声明。
     *
     * @param permissions                  请求的权限
     * @param onPermissionResponseListener 回调监听器
     */
    public void requestPermission(OnPermissionResponseListener onPermissionResponseListener, String... permissions) {
        this.onPermissionResponseListener = onPermissionResponseListener;
        if (checkPermissions(permissions)) {
            if (onPermissionResponseListener != null) {
                onPermissionResponseListener.onSuccess(permissions);
            }
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    public interface OnPermissionResponseListener {
        void onSuccess(String[] permissions);

        void onFail();
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String... permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    public void showPopDialog() {
        if ((!this.isFinishing()) && (dialog == null || !dialog.isShowing())) {
            dialog = new Dialog(this, R.style.MyDialogStyle);
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
