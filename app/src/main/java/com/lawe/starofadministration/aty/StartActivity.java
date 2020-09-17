package com.lawe.starofadministration.aty;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.kongzue.baseframework.util.Preferences;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;

import butterknife.ButterKnife;

public class StartActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //BaseUtil.setImmersive(getWindow());
        //ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        SharedPreferences frist = getSharedPreferences("frist", Context.MODE_PRIVATE);
        boolean isAgree = frist.getBoolean("isAgree",false);
        if(isAgree){
            toMain();
        }else {
            //toGuide();
        }
    }
    /*public void toGuide(){
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
            finish();
        },1000); //延迟1秒跳转
    }*/

    /**
     * 有网络就登录 更新用户信息
     * 没有网络的情况下，就根据之前是否登录，如果已经登录过，就直接进入主界面，如果没有登录过的话，就进入登录界面
     */
    public void toMain(){
        new Handler().postDelayed(() -> {
            String token = Preferences.getInstance().getString(StartActivity.this,"login","token");
            if(TextUtils.isEmpty(token)) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent  =new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        },1000); //延迟1秒跳转
    }

}
