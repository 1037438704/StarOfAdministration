package com.lawe.starofadministration.aty;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
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

import androidx.annotation.ContentView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.kongzue.dialog.v3.WaitDialog;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.ConcealFragment;
import com.lawe.starofadministration.fgt.PageFragment;
import com.lawe.starofadministration.fgt.ServeFragment;
import com.lawe.starofadministration.fgt.UserFragment;
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
    private PopupWindow popupWindow;
    private RadioGroup radio_group;
    private ViewPager viewpager;
    private ArrayList<Fragment> list;
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
        //输入密码  不可见
        login_eye.setBackgroundResource(R.mipmap.login_biyan);
        LoginAty.this.login_edpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {
        buttonLoginImmediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog.show(me, R.layout.layout_full_login, new FullScreenDialog.OnBindView() {
                            @Override
                            public void onBind(FullScreenDialog dialog, View rootView) {
                                initPopWindeo(rootView);
                                rootView.findViewById(R.id.image_finish).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.doDismiss();
                                    }
                                });

                            }
                        });
            }
        });

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

                // 获取自定义布局文件activity_popupwindow_left.xml的视图
                View popupWindow_view = getLayoutInflater().inflate(R.layout.layout_full_login, null,
                        false);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow(popupWindow_view, 200, ViewGroup.LayoutParams.MATCH_PARENT, true);
                // 点击其他地方消失
                popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // TODO Auto-generated method stub
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
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
            }
        });

        linear_popNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_popNet.setVisibility(View.GONE);
            }
        });
    }

    private void initPopWindeo(View view) {
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        viewpager = (ViewPager) view.findViewById(R.id.vp_container);

        list = new ArrayList<>();

        list.add(new ConcealFragment());
        list.add(new UserFragment());
        list.add(new ServeFragment());

        //为pager设置适配器
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()){

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return list.get(arg0);
            }
        });
        //为pager设置监听事件
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                switch (arg0) {
                    case 0:
                        radio_group.check(R.id.radio_conceal);
                        break;
                    case 1:
                        radio_group.check(R.id.radio_user);
                        break;
                    case 2:
                        radio_group.check(R.id.radio_serve);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        //位group设置监听
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.radio_conceal:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.radio_user:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.radio_serve:
                        viewpager.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}
