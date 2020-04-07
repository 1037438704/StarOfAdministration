package com.lawe.starofadministration.aty;

import android.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.wynsbin.vciv.VerificationCodeInputView;

import java.util.ArrayList;


@Layout(R.layout.aty_login)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class LoginAty extends BaseAty {
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

        //输入密码  不可见
        login_eye.setBackgroundResource(R.mipmap.login_biyan);
        LoginAty.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void initDatas(JumpParameter parameter) {

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
                toast("立即登录");
                linear_popAgree.setVisibility(View.VISIBLE);
            }
        });

        /*密码可见不可见*/
        login_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lock){
                    lock=false;
                    LoginAty.this.login_edpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    login_eye.setBackgroundResource(R.mipmap.login_eye);

                }else{
                    lock=true;
                    LoginAty.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    login_eye.setBackgroundResource(R.mipmap.login_biyan);

                }
            }
        });

        /*各种登录方式切换提示*/
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_tishi = login_text.getText().toString();
                toast(login_tishi);
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
                linear_popAgree.setVisibility(View.GONE);
            }
        });

        linear_popNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_popNet.setVisibility(View.GONE);
            }
        });

        linear_popAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_popAgree.setVisibility(View.GONE);
            }
        });

    }
}
