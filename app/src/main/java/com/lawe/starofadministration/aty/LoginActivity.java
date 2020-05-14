package com.lawe.starofadministration.aty;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.fgt.JoinContextFragment;
import com.lawe.starofadministration.fgt.JoinEclosureFragment;
import com.lawe.starofadministration.fgt.JoinSpeedFragment;
import com.wynsbin.vciv.VerificationCodeInputView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/2 15:22
 * description : 登录
 */
@Layout(R.layout.aty_login)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class LoginActivity extends BaseAty {
    FrameLayout frame_layout_bottom;
    Button buttonLoginImmediately;
    private VerificationCodeInputView vcivCode;
    private ImageView login_eye;
    private boolean lock;
    private EditText login_edpass;
    private TextView login_text;
    private Button login_getCode;
    private LinearLayout linear_code;
    private RelativeLayout linear_pass;
    private TextView text_zhuanwang;
    private RelativeLayout linear_popNet;
    private ImageView login_down;
    private RelativeLayout linear_popAgree;
    private Button login_agree;
    private TextView login_zhanghaoBig;
    private TextView login_zhanghaoSmall;
    private EditText login_phone;
    private Button login_jujue;
    private TextView login_text_forget;
    private TextView login_ninhao;
    private TextView login_passwordBig;
    private TextView login_passwordSmall;
    private TextView login_code;
    private String phoneBrand;  //手机型号
    private String androidId;   //手机唯一序列号
    private SharedPreferences.Editor editor;
    private SharedPreferences frist;

    @Override
    public void initViews() {
        initView();

        //输入密码  不可见
        login_eye.setBackgroundResource(R.mipmap.login_biyan);
        LoginActivity.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //获取手机型号、序列号
        phoneBrand = Build.MODEL;
        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("phone", phoneBrand + "     " + androidId);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        //账号密码登录
        HttpRequest.POST(me, Constants.LOGIN, new Parameter()
                .add("account", "")
                .add("id", "userId"), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null) {

                } else {
                    error.getMessage();
                }
            }
        });
    }

    @Override
    public void setEvents() {

        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_phone.setFocusableInTouchMode(true);
                login_zhanghaoBig.setVisibility(View.GONE);
                login_zhanghaoSmall.setVisibility(View.VISIBLE);
               /* //输入账号
                login_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            toast("有焦点");

                        }else{
                            toast("没有焦点");
                        }
                    }
                });*/

            }
        });

        login_edpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_edpass.setFocusableInTouchMode(true);
                login_passwordBig.setVisibility(View.GONE);
                login_passwordSmall.setVisibility(View.VISIBLE);
                //输入密码
                /*login_edpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            toast("有焦点");

                        }else{
                            toast("没有焦点");
                        }
                    }
                });*/
            }
        });

        //清除验证码
        vcivCode.clearCode();

        /*验证码输入框*/
        vcivCode.setOnInputListener(new VerificationCodeInputView.OnInputListener() {
            @Override
            public void onComplete(String code) {
                Toast.makeText(me, code, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInput() {

            }
        });

        //立即登录
        buttonLoginImmediately.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isFrist();
            }

        });

        /*密码可见不可见*/
        login_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lock) {
                    lock = false;
                    LoginActivity.this.login_edpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    login_eye.setBackgroundResource(R.mipmap.login_eye);

                } else {
                    lock = true;
                    LoginActivity.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    login_eye.setBackgroundResource(R.mipmap.login_biyan);

                }
            }
        });

        /*各种登录方式切换提示*/
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_tishi = login_text.getText().toString();
                if (login_tishi.equals("验证码登录")) {
                    linear_code.setVisibility(View.VISIBLE);
                    login_getCode.setVisibility(View.VISIBLE);
                    login_getCode.setText("获取验证码");
                    login_text.setText("多因素登录");
                    linear_pass.setVisibility(View.GONE);
                } else {
                    login_getCode.setVisibility(View.GONE);
                    login_text.setText("验证码登录");
                    linear_pass.setVisibility(View.VISIBLE);
                    linear_code.setVisibility(View.GONE);
                }
            }
        });

        /*输入专网*/
        text_zhuanwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_popNet.setVisibility(View.VISIBLE);
            }
        });

        login_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_popNet.setVisibility(View.GONE);
            }
        });

        //忘记密码---跳转
        login_text_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ForgetPassActivity.class);
            }
        });
    }

    private void isAgree() {
        linear_popAgree.setVisibility(View.VISIBLE);
        login_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frist.edit().putBoolean("isAgree",true).commit();
                jump(MainActivity.class);
            }
        });

        login_jujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frist.edit().putBoolean("isAgree",false).commit();
                linear_popAgree.setVisibility(View.GONE);
            }
        });
    }

    private void isFrist() {
        frist = getSharedPreferences("frist", Context.MODE_PRIVATE);

        if (frist.getBoolean("isAgree",true)){
            toast("点击了");
            jump(MainActivity.class);
        }else{
            //点击确定或者取消
            toast("协议");
            isAgree();
        }
    }

    private void initView() {
        buttonLoginImmediately = findViewById(R.id.button_login_immediately);
        frame_layout_bottom = findViewById(R.id.frame_layout_bottom);
        vcivCode = findViewById(R.id.vciv_code);
        login_eye = findViewById(R.id.login_eye);
        login_edpass = findViewById(R.id.login_edpass);
        login_text = findViewById(R.id.login_text);
        login_getCode = findViewById(R.id.login_getCode);
        linear_code = findViewById(R.id.linear_code);
        login_code = findViewById(R.id.login_code);
        linear_pass = findViewById(R.id.linear_pass);
        text_zhuanwang = findViewById(R.id.text_zhuanwang);
        linear_popNet = findViewById(R.id.linear_pop);
        login_down = findViewById(R.id.login_down);
        linear_popAgree = findViewById(R.id.linear_popAgree);
        login_agree = findViewById(R.id.login_agree);
        login_zhanghaoBig = findViewById(R.id.login_zhanghao_big);
        login_zhanghaoSmall = findViewById(R.id.login_zhanghao_small);
        login_phone = findViewById(R.id.login_phone);
        login_jujue = findViewById(R.id.login_jujue);
        login_text_forget = findViewById(R.id.login_text_forget);
        login_ninhao = findViewById(R.id.login_ninhao);
        login_passwordBig = findViewById(R.id.login_password_big);
        login_passwordSmall = findViewById(R.id.login_password_small);
        //设置字体
        login_ninhao.setTypeface(getTextMedium);
        login_zhanghaoBig.setTypeface(getTextMedium);
        login_passwordBig.setTypeface(getTextMedium);
        buttonLoginImmediately.setTypeface(getTextMedium);
        login_zhanghaoSmall.setTypeface(getTextMedium);
        login_passwordSmall.setTypeface(getTextMedium);
        login_code.setTypeface(getTextMedium);
    }

}
