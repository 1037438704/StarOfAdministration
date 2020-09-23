package com.lawe.starofadministration.aty;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.LoginDefaltBean;
import com.lawe.starofadministration.bean.LoginWebBean;
import com.lawe.starofadministration.config.Constants;
import com.wynsbin.vciv.VerificationCodeInputView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

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
    private TextView login_zhanghaoBig;
    private TextView login_zhanghaoSmall;
    private EditText login_phone;
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
    private int codes = 1;
    //验证码倒计时
    private CountDownTimer timer;
    private LinearLayout linera_edit_code;
    private String jsonCode;
    private EditText loginCodePass;
    private LoginWebBean loginWebBean;

    @Override
    public void initViews() {
        super.initViews();
        buttonLoginImmediately = findViewById(R.id.button_login_immediately);
        frame_layout_bottom = findViewById(R.id.frame_layout_bottom);
        vcivCode = findViewById(R.id.vciv_code);
        login_eye = findViewById(R.id.login_eye);
        login_edpass = findViewById(R.id.login_edpass);
        login_text = findViewById(R.id.login_text);
        login_getCode = findViewById(R.id.login_getCode);
        linear_code = findViewById(R.id.linear_code);
        loginCodePass = findViewById(R.id.login_codePass);
        login_code = findViewById(R.id.login_code);
        linera_edit_code = findViewById(R.id.linera_edit_code);
        linear_pass = findViewById(R.id.linear_pass);
        text_zhuanwang = findViewById(R.id.text_zhuanwang);
        linear_popNet = findViewById(R.id.linear_pop);
        login_down = findViewById(R.id.login_down);

        login_zhanghaoBig = findViewById(R.id.login_zhanghao_big);
        login_zhanghaoSmall = findViewById(R.id.login_zhanghao_small);
        login_phone = findViewById(R.id.login_phone);

        login_text_forget = findViewById(R.id.login_text_forget);
        login_ninhao = findViewById(R.id.login_ninhao);
        login_passwordBig = findViewById(R.id.login_password_big);
        login_passwordSmall = findViewById(R.id.login_password_small);
        String phone = Preferences.getInstance().getString(me, "phone", "phone");
        String psd = Preferences.getInstance().getString(me, "phone", "psd");

        if (phone != null && psd != null){
            login_phone.setText(phone);
            login_edpass.setText(psd);
        }
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
            }

            @Override
            public void onInput() {

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
                    login_passwordSmall.setVisibility(View.GONE);
                    codes = 2;
                } else {
                    login_getCode.setVisibility(View.GONE);
                    login_text.setText("验证码登录");
                    linear_pass.setVisibility(View.VISIBLE);
                    linear_code.setVisibility(View.GONE);
                    codes = 1;
                }
            }
        });

        //立即登录
        buttonLoginImmediately.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (codes == 1){
                    postLogin();
                }else{
                    vaildLogin();
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
                    JSONObject json=new JSONObject();
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
                            countDown();
                        } else {
                            error.getMessage();
                        }
                    }
                });
            }
        });
    }

    //验证码倒计时
    private void countDown(){
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                login_getCode.setText(String.format(Locale.CHINA,"%d秒",(int)millisUntilFinished/1000));
                login_getCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                login_getCode.setText("获取验证码");
                login_getCode.setClickable(true);
            }
        };
        timer.start();
    }

    //手机号密码登录
    private void postLogin() {
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
                JSONObject json = new JSONObject();
                json.put("account", loginPhone);
                json.put("password", loginPass);
                json.put("deviceNum", phoneBrand);
                json.put("loginTerminal","2");
                json.put("firstLoginImei",androidId);
                json.put("firstLoginIsp","移动");
                //json转化为string类型
                jsonLogin = String.valueOf(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpRequest.JSONPOST(me, Constants.LOGIN, jsonLogin, new ResponseListener() {
                @Override
                public void onResponse(String response, Exception error) {
                   if (error == null){
                       LoginDefaltBean loginDefaltBean = gson.fromJson(response, LoginDefaltBean.class);
                       String depUserId = loginDefaltBean.getEntityBO().getId();
                       String dName = loginDefaltBean.getEntityBO().getDName();
                       for (int i = 0; i < loginDefaltBean.getEntityBO().getListDepartment().size(); i++) {
                           String departmentId = loginDefaltBean.getEntityBO().getListDepartment().get(i).getId();
                           String departFullName = loginDefaltBean.getEntityBO().getListDepartment().get(i).getDepartFullName();
                           Preferences.getInstance().set(me, "login", "departmentId",departmentId);
                           Preferences.getInstance().set(me, "login", "departFullName",departFullName);
                       }
                       Preferences.getInstance().set(me, "login", "token",loginDefaltBean.getToken());
                       Preferences.getInstance().set(me, "login", "depUserId",depUserId);
                       Preferences.getInstance().set(me, "login", "name",dName);
                       isFrist();
                   }else{
                       error.getMessage();
                       toast(error.getMessage());
                   }

                }
            });
        }
    }

    //验证码登录
    private void vaildLogin() {
        loginPhone = login_phone.getText().toString();
        String loginCode = loginCodePass.getText().toString();
        Preferences.getInstance().commit(me, "phone", "phone", login_phone.getText().toString().trim());
        if (TextUtils.isEmpty(loginPhone)){
            toast("请输入账号");
            return;
        }
        if(TextUtils.isEmpty(loginCode)){
            toast("请输入验证码");
            return;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("account", loginPhone);
            json.put("code", loginCode);
            //json转化为string类型
            jsonLogin = String.valueOf(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        showPopDialog();
        HttpRequest.JSONPOST(me, Constants.VALIDCODELOGIN, jsonLogin, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    LoginDefaltBean loginDefaltBean = gson.fromJson(response, LoginDefaltBean.class);
                    String depUserId = loginDefaltBean.getEntityBO().getId();
                    String dName = loginDefaltBean.getEntityBO().getDName();
                    for (int i = 0; i < loginDefaltBean.getEntityBO().getListDepartment().size(); i++) {
                        String departmentId = loginDefaltBean.getEntityBO().getListDepartment().get(i).getId();
                        String departFullName = loginDefaltBean.getEntityBO().getListDepartment().get(i).getDepartFullName();
                        Preferences.getInstance().set(me, "login", "departmentId",departmentId);
                        Preferences.getInstance().set(me, "login", "departFullName",departFullName);
                    }
                    Preferences.getInstance().set(me, "login", "token",loginDefaltBean.getToken());
                    Preferences.getInstance().set(me, "login", "depUserId",depUserId);
                    Preferences.getInstance().set(me, "login", "name",dName);

                    isFrist();
                }else{
                    error.getMessage();
                }

            }
        });

    }

    //是否同意协议
    private void isAgree() {

        final Dialog dialog = new Dialog(me, R.style.DialogTheme);

        View view = View.inflate(me, R.layout.pop_agreement, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button login_agree = view.findViewById(R.id.login_agree);
        Button login_jujue = view.findViewById(R.id.login_jujue);
        RadioGroup mainRgp = view.findViewById(R.id.main_rgp);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        ScrollView sv01 = (ScrollView) findViewById(R.id.popup_sf_event_scroll_01);
        loginWeb.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    sv01.requestDisallowInterceptTouchEvent(false);
                else
                    sv01.requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });
        WebView loginWeb = view.findViewById(R.id.login_webview);
        RadioButton radioButton = (RadioButton) mainRgp.getChildAt(0);
        radioButton.setChecked(true);

        //加载协议
        JSONObject json=new JSONObject();
        String json2 = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.FINDUSERAGREEMENT, json2, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null) {
                    loginWebBean = gson.fromJson(response, LoginWebBean.class);
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
                    String userAgreementUrl = loginWebBean.getXy().getPrivate_agreement_url();
                    loginWeb.loadUrl(userAgreementUrl);
                } else {
                    error.getMessage();
                }
            }
        });

        //RadioGroup的事件监听
        mainRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < mainRgp.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) mainRgp.getChildAt(i);
                    if (rb.isChecked()) {
                        if (i == 1){
                            String userAgreementUrl = loginWebBean.getXy().getUser_agreement_url();
                            loginWeb.loadUrl(userAgreementUrl);
                        }else if (i == 2){
                            String userAgreementUrl = loginWebBean.getXy().getService_agreement_url();
                            loginWeb.loadUrl(userAgreementUrl);
                        }else{
                            String userAgreementUrl = loginWebBean.getXy().getPrivate_agreement_url();
                            loginWeb.loadUrl(userAgreementUrl);
                        }
                    }
                }
            }
        });


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
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    //判断是否第一次登录同意协议
    private void isFrist() {
        frist = getSharedPreferences("frist", Context.MODE_PRIVATE);
        if (frist.getBoolean("isAgree",false)){
            jump(MainActivity.class);
        }else{
            //点击确定或者取消
            isAgree();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }

}