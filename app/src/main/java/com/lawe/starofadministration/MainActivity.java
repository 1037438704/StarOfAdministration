package com.lawe.starofadministration;

import android.Manifest;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.AppManager;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.CenterFragment;
import com.lawe.starofadministration.fgt.DatasFragment;
import com.lawe.starofadministration.fgt.DealtFragment;
import com.lawe.starofadministration.fgt.SettingsFragment;
import com.lawe.starofadministration.fgt.SpeedFragment;
import com.lawe.starofadministration.httpUtils.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.activity_main)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class MainActivity extends BaseAty {
    private RadioGroup mainRgp;
    private NoScrollViewPager viewPager;
    private long exitTime = 0;
    private List<BaseFragment> fragemnts;
    ViewPagerAdp viewPagerAdp;

    @Override
    public void initViews() {
        //requestPemissions();
        fragemnts = new ArrayList<>();
        mainRgp = findViewById(R.id.main_rgp);
        viewPager = findViewById(R.id.viewPager);
        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts.add(DealtFragment.newInstance());
        fragemnts.add(SpeedFragment.newInstance());
        fragemnts.add(CenterFragment.newInstance());
        fragemnts.add(DatasFragment.newInstance());
        fragemnts.add(SettingsFragment.newInstance());
        viewPagerAdp = new ViewPagerAdp(getSupportFragmentManager(), fragemnts);
        viewPager.setOffscreenPageLimit(fragemnts.size());
        viewPager.setAdapter(viewPagerAdp);
        viewPager.setNoScroll(true);
    }

    @Override
    public void setEvents() {
        //viewPager的滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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
                for (int i = 0; i < mainRgp.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) mainRgp.getChildAt(i);
                    if (rb.isChecked()) {
                        viewPager.setCurrentItem(i, false);
                    }
                }
            }
        });
    }

    /**
     * 按两次退出应用
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

    private void destroyItem(ViewGroup container, int position, Object object) {
        viewPager.removeView(getCurrentFocus());
    }
}