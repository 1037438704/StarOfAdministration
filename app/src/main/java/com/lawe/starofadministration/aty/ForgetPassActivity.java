package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.print.PrintJob;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.dialog.v3.WaitDialog;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.LoginDefaltBean;
import com.lawe.starofadministration.config.Constants;
import com.okhttplib.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Map;

import butterknife.OnClick;
import okhttp3.Call;

/**
 * author : fuke
 * date : 2020/4/7 15:22
 * description : 忘记密码
 */
@Layout(R.layout.activity_forget_pass)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class ForgetPassActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private EditText editPhone;
    private EditText editCode;
    private TextView tvGetCode;
    private Button forgetNext;

    //验证码倒计时
    private CountDownTimer timer;
    private String phone;
    private String jsonCode;

    @Override
    public void initViews() {
        super.initViews();
        initView();
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        titleText.setText("忘记密码");
        titleText.setTypeface(getTextMedium);
    }

    @Override
    public void setEvents() {
        //返回
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击获取验证码
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //账号密码登录
                phone = editPhone.getText().toString();

                if (TextUtils.isEmpty(phone)){
                    toast("请输入手机号");
                }else{
                    getPhoneCode();
                }
            }
        });

        //下一步
        forgetNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validCode();
            }
        });
    }

    //获取验证码
    private void getPhoneCode() {
        try {
            JSONObject json=new JSONObject();
            json.put("account",phone);
            jsonCode = String.valueOf(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //请求接口
        HttpRequest.JSONPOST(me, Constants.GETPASSWORDBYSENDNOTE, jsonCode, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null) {
                    toast("发送成功");
                    countDown();
                } else {
                    error.getMessage();
                }
            }
        });
    }

    //验证码倒计时
    private void countDown(){
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetCode.setText(String.format(Locale.CHINA,"%d秒",(int)millisUntilFinished/1000));
                tvGetCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                tvGetCode.setText("获取验证码");
                tvGetCode.setClickable(true);
            }
        };
        timer.start();
    }

    //短信验证
    private void validCode(){
        phone = editPhone.getText().toString();
        String code = editCode.getText().toString();
        if (TextUtils.isEmpty(phone)){
            toast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(code)){
            toast("请输入验证码");
            return;
        }

        try {
            JSONObject json=new JSONObject();
            json.put("account", phone);
            json.put("code", code);
            json.put("businessType","1");
            //json转化为string类型
            jsonCode = String.valueOf(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpRequest.JSONPOST(me, Constants.VALIDCODE, jsonCode, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Intent intent = new Intent(ForgetPassActivity.this, ForgetPassUpdateActivity.class);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    finish();
                } else {
                    error.getMessage();
                }
            }
        });
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        editPhone = findViewById(R.id.edit_phone);
        editCode = findViewById(R.id.edit_code);
        tvGetCode = findViewById(R.id.tv_get_code);
        forgetNext = findViewById(R.id.forget_next);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }
}