package com.lawe.starofadministration.aty;

import android.animation.ObjectAnimator;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.wynsbin.vciv.VerificationCodeInputView;


@Layout(R.layout.aty_login)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
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
    private TextView login_zhanghao;
    private EditText login_phone;
    private Button login_jujue;
    private TextView login_text_forget;

    @Override
    public void initViews() {
        buttonLoginImmediately = findViewById(R.id.button_login_immediately);
        frame_layout_bottom = findViewById(R.id.frame_layout_bottom);
        vcivCode = findViewById(R.id.vciv_code);
        login_eye = findViewById(R.id.login_eye);
        login_edpass = findViewById(R.id.login_edpass);
        login_text = findViewById(R.id.login_text);
        login_getCode = findViewById(R.id.login_getCode);
        linear_code = findViewById(R.id.linear_code);
        linear_pass = findViewById(R.id.linear_pass);
        text_zhuanwang = findViewById(R.id.text_zhuanwang);
        linear_popNet = findViewById(R.id.linear_pop);
        login_down = findViewById(R.id.login_down);
        linear_popAgree = findViewById(R.id.linear_popAgree);
        login_agree = findViewById(R.id.login_agree);
        login_zhanghao = findViewById(R.id.login_zhanghao);
        login_phone = findViewById(R.id.login_phone);
        login_jujue = findViewById(R.id.login_jujue);
        login_text_forget = findViewById(R.id.login_text_forget);
        //输入密码  不可见
        login_eye.setBackgroundResource(R.mipmap.login_biyan);
        LoginActivity.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        //点击输入框   提示动画
        login_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    toast("有焦点");

                    //账号上移
                    TranslateAnimation translateAni = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                            0, Animation.RELATIVE_TO_PARENT, 0,
                            Animation.RELATIVE_TO_PARENT, -0.5f);

                    //设置动画执行的时间，单位是毫秒
                    translateAni.setDuration(1000);
                    translateAni.setFillAfter(true);

                    // 设置动画重复次数
                    // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
                    translateAni.setRepeatCount(0);

                    // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
                    //translateAni.setRepeatMode(Animation.REVERSE);

                    // 启动动画
                    login_zhanghao.startAnimation(translateAni);
                    login_zhanghao.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

                    //输入框左平移
                    TranslateAnimation translateAnied = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                            -0.2f, Animation.RELATIVE_TO_PARENT, 0,
                            Animation.RELATIVE_TO_PARENT, 0);

                    //设置动画执行的时间，单位是毫秒
                    translateAnied.setDuration(1000);
                    translateAnied.setFillAfter(true);

                    // 设置动画重复次数
                    // -1或者Animation.INFINITE表示无限重复，正数表示重复次数，0表示不重复只播放一次
                    translateAnied.setRepeatCount(0);

                    // 启动动画
                    login_phone.startAnimation(translateAnied);
                }else{
                    toast("没有焦点");
                }
            }
        });
    }

    @Override
    public void setEvents() {

        //清除验证码
//        vcivCode.clearCode();

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
                linear_popAgree.setVisibility(View.VISIBLE);
                login_agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jump(MainActivity.class);
                    }
                });

                login_jujue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linear_popAgree.setVisibility(View.GONE);
                    }
                });

                login_down.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linear_popAgree.setVisibility(View.GONE);
                    }
                });
            }
        });

        /*密码可见不可见*/
        login_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lock){
                    lock=false;
                    LoginActivity.this.login_edpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    login_eye.setBackgroundResource(R.mipmap.login_eye);

                }else{
                    lock=true;
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
                if (login_tishi.equals("验证码登录")){
                    linear_code.setVisibility(View.VISIBLE);
                    login_getCode.setVisibility(View.VISIBLE);
                    login_getCode.setText("获取验证码");
                    login_text.setText("多因素登录");
                    linear_pass.setVisibility(View.GONE);
                }else{
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
}
