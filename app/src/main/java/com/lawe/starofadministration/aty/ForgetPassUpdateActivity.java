package com.lawe.starofadministration.aty;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.dialog.v3.WaitDialog;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.config.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * author : fuke
 * date : 2020/4/7 15:22
 * description : 忘记密码
 */
@Layout(R.layout.activity_forget_pass_update)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class ForgetPassUpdateActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private EditText editPass;
    private EditText editPassSure;
    private Button buttonSure;
    private String jsonCode;
    private String phone;


    @Override
    public void initViews() {
        super.initViews();
        initView();
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        titleText.setText("忘记密码");
        titleText.setTypeface(getTextMedium);
        phone = getIntent().getExtras().getString("phone");

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

        //确认修改
        buttonSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataPass();
            }
        });
    }

    //修改密码
    private void updataPass() {
        String password = editPass.getText().toString();
        String etPassReply = editPassSure.getText().toString();
        if (!etPassReply.equals(password)){
            toast("两次密码不一致");
            return;
        }

        try {
            JSONObject json=new JSONObject();
            json.put("account",phone);
            json.put("newPwd",password);
            json.put("repeatPwd",etPassReply);
            jsonCode = String.valueOf(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //请求接口
        HttpRequest.JSONPOST(me, Constants.UPDATEPASSWORD, jsonCode, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null) {
                    toast("修改成功");
                    jump(LoginActivity.class);
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
        editPass = findViewById(R.id.edit_pass);
        editPassSure = findViewById(R.id.edit_pass_sure);
        buttonSure = findViewById(R.id.buttonSure);
    }

}