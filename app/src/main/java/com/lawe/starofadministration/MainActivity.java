package com.lawe.starofadministration;

import android.Manifest;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.interfaces.BindView;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.AppManager;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnPermissionResponseListener;
import com.lawe.starofadministration.adp.MainFragmentPageAdapter;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.CenterFragment;
import com.lawe.starofadministration.fgt.DatasFragment;
import com.lawe.starofadministration.fgt.DealtFragment;
import com.lawe.starofadministration.fgt.SettingsFragment;
import com.lawe.starofadministration.fgt.SpeedFragment;
import com.wynsbin.vciv.VerificationCodeInputView;

import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.activity_main)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class MainActivity extends BaseAty {

    private RadioGroup mainRgp;
    private ViewPager viewpager;
    private long exitTime = 0;
    private List<Fragment> fragemnts;

    @Override
    public void initViews() {
        mainRgp = findViewById(R.id.main_rgp);
        viewpager = findViewById(R.id.viewPager1);
        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();
        fragemnts.add(DealtFragment.newInstance());
        fragemnts.add(SpeedFragment.newInstance());
        fragemnts.add(CenterFragment.newInstance());
        fragemnts.add(DatasFragment.newInstance());
        fragemnts.add(SettingsFragment.newInstance());
        //实例化适配器
        MainFragmentPageAdapter adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragemnts);
        viewpager.setOffscreenPageLimit(fragemnts.size());
        //设置适配器
        viewpager.setAdapter(adapter);
    }

    @Override
    public void setEvents() {
        //viewPager的滑动监听
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //获取当前位置的RadioButton
                RadioButton rb = (RadioButton) mainRgp.getChildAt(position);
                //设置为true
                rb.setChecked(true);
            }
        });
        //RadioGroup的事件监听
        mainRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int index = 0; index < mainRgp.getChildCount(); index++) {
                    RadioButton rb = (RadioButton) mainRgp.getChildAt(index);
                    if (rb.isChecked()) {
                        viewpager.setCurrentItem(index, false);
                        break;
                    }
                    RadioButton childAt = (RadioButton) mainRgp.getChildAt(4);
                    if(childAt.isChecked()){
                        /*if (token == null || token.equals("")){
                            start(me, LoginActivity.class);
                        }*/
                    }
                }
            }
        });
    }

    /**
     * 按两次退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getInstance().killAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void requestPemissions() {
        requestPermission(new OnPermissionResponseListener() {
                              @Override
                              public void onSuccess(String[] permissions) {

                              }

                              @Override
                              public void onFail() {
                                  startAppSettings();
                              }
                          }, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        );
    }

    private void requestPermission(OnPermissionResponseListener onPermissionResponseListener, String camera,
                                   String writeExternalStorage, String readExternalStorage) {
    }

}
