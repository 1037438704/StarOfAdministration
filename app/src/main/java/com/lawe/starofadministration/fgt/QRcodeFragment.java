package com.lawe.starofadministration.fgt;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.config.Constants;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * author : fuke
 * date : 2020/4/10 11:40
 * description : 二维码扫描
 */
@Layout(R.layout.camera)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class QRcodeFragment extends BaseFgt {

    public static final int RESULT_OK = -1;
    private String TAG = "QRcodeFragment";
    private String results = "";

    @Override
    public void initViews() {
        super.initViews();
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);

        getFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {

        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            Log.e("TAG", "onAnalyzeSuccess: AFragment:" + result);
            results = result;

                final Dialog dialog = new Dialog(getContext(), R.style.DialogTheme);
                View view = View.inflate(getContext(), R.layout.layout_window_login, null);
                dialog.setContentView(view);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.main_menu_animStyle);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                Button loginDown = view.findViewById(R.id.login_conputer_sure);
                TextView cancle = view.findViewById(R.id.login_conputer_none);
                ImageView back_button = view.findViewById(R.id.back_button);

                loginDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //調用登录接口
                        getUserInfo();
                        getLogin();
                    }
                });

                back_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

            getActivity().setResult(RESULT_OK, resultIntent);
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            getActivity().setResult(RESULT_OK, resultIntent);
        }
    };

    private void getUserInfo() {
        HttpRequest.GET(getActivity(), Constants.UPDATEDEPUEINFO + depUserId + "/" + results, new Parameter(), new ResponseListener() {
            @Override
            public void onResponse(String main, Exception error) {
                if (error == null){

                }else{
                    error.getMessage();
                }
            }
        });
    }

    private void getLogin() {
        HttpRequest.POST(getActivity(), Constants.FINDQRISOVERDUE, new Parameter().add("qrCodeString",results), new ResponseListener() {
            @Override
            public void onResponse(String main, Exception error) {
                if (error == null){

                }else{
                    error.getMessage();
                }
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static QRcodeFragment newInstance() {
        return new QRcodeFragment();
    }
}
