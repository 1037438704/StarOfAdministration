package com.lawe.starofadministration.aty;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.LoginDefaltBean;
import com.lawe.starofadministration.bean.LoginWebBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.fgt.JoinContextFragment;
import com.lawe.starofadministration.fgt.JoinEclosureFragment;
import com.lawe.starofadministration.fgt.JoinSpeedFragment;
import com.lawe.starofadministration.utils.Constant;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.CacheType;
import com.okhttplib.annotation.ContentType;
import com.okhttplib.annotation.RequestType;
import com.okhttplib.bean.DownloadFileInfo;
import com.okhttplib.callback.Callback;
import com.wynsbin.vciv.VerificationCodeInputView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private RelativeLayout linear_code;
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
    private String jsonLogin;
    private String loginPhone;
    private String loginPass;
    private WebView loginWeb;
    private String ids = "/118";
    private String codes;

    private Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String title = (String) msg.obj;
            }
        }

    };
    private LinearLayout linera_edit_code;
    private String jsonCode;

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
        login_code = findViewById(R.id.login_code);
        linera_edit_code = findViewById(R.id.linera_edit_code);
         linear_pass = findViewById(R.id.linear_pass);
        text_zhuanwang = findViewById(R.id.text_zhuanwang);
        linear_popAgree = findViewById(R.id.linear_popAgree);
        linear_popNet = findViewById(R.id.linear_pop);
        login_down = findViewById(R.id.login_down);
        login_agree = findViewById(R.id.login_agree);
        login_zhanghaoBig = findViewById(R.id.login_zhanghao_big);
        login_zhanghaoSmall = findViewById(R.id.login_zhanghao_small);
        login_phone = findViewById(R.id.login_phone);
        login_jujue = findViewById(R.id.login_jujue);
        login_text_forget = findViewById(R.id.login_text_forget);
        login_ninhao = findViewById(R.id.login_ninhao);
        login_passwordBig = findViewById(R.id.login_password_big);
        login_passwordSmall = findViewById(R.id.login_password_small);
        loginWeb = findViewById(R.id.login_webview);
        String phone = Preferences.getInstance().getString(me, "phone", "phone");
        String psd = Preferences.getInstance().getString(me, "phone", "psd");
        if (phone != null && psd != null){
            login_phone.setText(phone);
            login_edpass.setText(psd);
        }
        //加载协议
        webXieyi();
       // shouye();

    }

    private void shouye() {

        HttpRequest.GET(me,Constants.DOCUMENTFICTION + ids,new Parameter(),new ResponseListener(){
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Log.e("shuju",response);
                } else {

                }
            }
        });
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        //设置字体
        login_ninhao.setTypeface(getTextMedium);
        login_zhanghaoBig.setTypeface(getTextMedium);
        login_passwordBig.setTypeface(getTextMedium);
        buttonLoginImmediately.setTypeface(getTextMedium);
        login_zhanghaoSmall.setTypeface(getTextMedium);
        login_passwordSmall.setTypeface(getTextMedium);
        login_code.setTypeface(getTextMedium);

        //输入密码  不可见
        login_eye.setBackgroundResource(R.mipmap.login_biyan);
        LoginActivity.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //获取手机型号、序列号
        phoneBrand = Build.MODEL;
        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

    }

    @Override
    public void setEvents() {

        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_phone.setFocusableInTouchMode(true);
                login_zhanghaoBig.setVisibility(View.GONE);
                login_zhanghaoSmall.setVisibility(View.VISIBLE);

            }
        });

        login_edpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_edpass.setFocusableInTouchMode(true);
                login_passwordBig.setVisibility(View.GONE);
                login_passwordSmall.setVisibility(View.VISIBLE);
            }
        });

        //清除验证码
        vcivCode.clearCode();

        //发送验证码
        sendCode();

        /*验证码输入框*/
        vcivCode.setOnInputListener(new VerificationCodeInputView.OnInputListener() {
            @Override
            public void onComplete(String code) {
                login_code.setVisibility(View.GONE);
                login_passwordSmall.setVisibility(View.VISIBLE);
                login_passwordSmall.setText("验证码");
                linera_edit_code.setVisibility(View.VISIBLE);
                Toast.makeText(me, code, Toast.LENGTH_SHORT).show();
                codes = code;
            }

            @Override
            public void onInput() {

            }
        });

        //立即登录
        buttonLoginImmediately.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                postLogin();
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

    //发送短信验证码
    private void sendCode() {
        login_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //账号密码登录
                String loginPhoneCode = login_phone.getText().toString();
                try {
                    json.put("account",loginPhoneCode);
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
                        } else {
                            error.getMessage();
                        }
                    }
                });
            }
        });
    }

    //登录请求
    private void postLogin() {
        String login_tishi = login_text.getText().toString();
        if (login_tishi.equals("验证码登录")) {
            validCode();
        } else {
            //账号密码登录
            loginPhone = login_phone.getText().toString();
            loginPass = login_edpass.getText().toString();
            Preferences.getInstance().commit(me, "phone", "phone", login_phone.getText().toString().trim());
            Preferences.getInstance().commit(me, "phone", "psd", login_edpass.getText().toString().trim());
            if (loginPhone.equals("")){
                toast("请输入账号");
            }else if(loginPass.equals("")){
                toast("请输入密码");
            }else {
                try {
                    json.put("account", loginPhone);
                    json.put("password", loginPass);
                    //json转化为string类型
                    jsonLogin = String.valueOf(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HttpRequest.JSONPOST(me, Constants.LOGIN, jsonLogin, new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        WaitDialog.dismiss();
                        if (error == null) {
                            Log.e("shuju",response);
                            LoginDefaltBean loginDefaltBean = gson.fromJson(response, LoginDefaltBean.class);
                            Preferences.getInstance().commit(me, "user", "token", loginDefaltBean.getToken());
                            isFrist();
                        } else {
                            error.getMessage();
                        }
                    }
                });
            }
        }
    }

    //加载登陆协议
    private  void webXieyi(){
        try {
            json.put("name","123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json2 = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.FINDUSERAGREEMENT, json2, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null) {
                    Log.e("shuju",response);
                    LoginWebBean loginWebBean = gson.fromJson(response, LoginWebBean.class);
                    String userAgreementUrl = loginWebBean.getUserAgreementUrl();
                    WebSettings webSettings = loginWeb.getSettings();
                    if (Build.VERSION.SDK_INT >= 21) {
                        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                    }
                    // 加快HTML网页加载完成速度
                    if (Build.VERSION.SDK_INT >= 19) {
                        webSettings.setLoadsImagesAutomatically(true);
                    } else {
                        webSettings.setLoadsImagesAutomatically(false);
                    }
                    webSettings.setDomStorageEnabled(true);//设置适应Html5的一些方法
                    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                    webSettings.setJavaScriptEnabled(true);
                    loginWeb.loadUrl(userAgreementUrl);
                } else {
                    error.getMessage();
                }
            }
        });
    }

    //短信验证
    private void validCode(){
        //账号密码登录
        loginPhone = login_phone.getText().toString();
        Preferences.getInstance().commit(me, "phone", "phone", login_phone.getText().toString().trim());
        if (loginPhone.equals("")){
            toast("请输入账号");
        }else if(codes.equals("")){
            toast("请输入验证码");
        }else {
           try {
                json.put("account", loginPhone);
                json.put("password", loginPass);
                //json转化为string类型
                jsonCode = String.valueOf(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpRequest.JSONPOST(me, Constants.LOGIN, jsonCode, new ResponseListener() {
                @Override
                public void onResponse(String response, Exception error) {
                    WaitDialog.dismiss();
                    if (error == null) {
                        Log.e("shuju",response);
                        LoginDefaltBean loginDefaltBean = gson.fromJson(response, LoginDefaltBean.class);
                        Preferences.getInstance().commit(me, "user", "token", loginDefaltBean.getToken());
                        isFrist();
                    } else {
                        error.getMessage();
                    }
                }
            });
        }
    }

    //是否同意协议
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

    //判断是否第一次登录同意协议
    private void isFrist() {
        frist = getSharedPreferences("frist", Context.MODE_PRIVATE);
        if (frist.getBoolean("isAgree",false)){
            login_phone.setText(frist.getString("phone",null));
            login_edpass.setText(frist.getString("pwd",null));
            jump(MainActivity.class);
        }else{
            //点击确定或者取消
            isAgree();
        }
    }

}