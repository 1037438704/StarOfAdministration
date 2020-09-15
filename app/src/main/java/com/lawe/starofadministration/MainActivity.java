package com.lawe.starofadministration;

import android.Manifest;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.BindView;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.AppManager;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.kongzue.dialog.v3.WaitDialog;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.PersonMessBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.fgt.CenterFragment;
import com.lawe.starofadministration.fgt.DatasFragment;
import com.lawe.starofadministration.fgt.DealtFragment;
import com.lawe.starofadministration.fgt.SettingsFragment;
import com.lawe.starofadministration.fgt.SpeedFragment;
import com.lawe.starofadministration.utils.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

@Layout(R.layout.activity_main)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class MainActivity extends BaseAty {
    private long exitTime = 0;
    private List<BaseFragment> fragemnts;
    ViewPagerAdp viewPagerAdp;
    private DrawerLayout drawer;
    private ArrayList<FragmentTouchListener> mFragmentTouchListeners;
    private RadioGroup mainRgp;
    private NoScrollViewPager viewPager;
    private TextView personName;

    private TextView personCompany;
    private TextView personXunzhang;
    private TextView personData;
    private TextView personSetting;
    private ImageView imgHead;
    private TextView suozaidanwei;
    private TextView suozaibumen;
    private TextView name;
    private TextView zhichen;
    private TextView age;
    private ProgressBar proShixiao;
    private ProgressBar proChaping;
    private ProgressBar proXinyong;
    private TextView textWork;
    private TextView textChaping;
    private TextView textXinyong;

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        fragemnts = new ArrayList<>();
        mFragmentTouchListeners = new ArrayList<>();
        viewPager = findViewById(R.id.viewPager);
        mainRgp = findViewById(R.id.main_rgp);
        personCompany = findViewById(R.id.person_company);
        personXunzhang = findViewById(R.id.person_xunzhang);
        personData = findViewById(R.id.person_data);
        personSetting = findViewById(R.id.person_setting);
        personName = findViewById(R.id.person_name);
        personName.setTypeface(getTextBold);
        personXunzhang.setTypeface(getTextMedium);
        personData.setTypeface(getTextMedium);
        personSetting.setTypeface(getTextMedium);
        drawer = findViewById(R.id.drawer_layout_shaixuan);

        imgHead = findViewById(R.id.img_head);
        suozaidanwei = findViewById(R.id.suozaidanwei);
        suozaibumen = findViewById(R.id.suozaibumen);
        name = findViewById(R.id.name);
        zhichen = findViewById(R.id.zhichen);
        age = findViewById(R.id.ages);
        proShixiao = findViewById(R.id.proShixiao);
        proChaping = findViewById(R.id.proChaping);
        proXinyong = findViewById(R.id.proXinyong);
        textWork = findViewById(R.id.textWork);
        textChaping = findViewById(R.id.textChaping);
        textXinyong = findViewById(R.id.textXinyong);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);

        fragemnts.add(DealtFragment.newInstance());
        fragemnts.add(SpeedFragment.newInstance());
        fragemnts.add(CenterFragment.newInstance());
        fragemnts.add(DatasFragment.newInstance());
        fragemnts.add(SettingsFragment.newInstance());
        viewPagerAdp = new ViewPagerAdp(getSupportFragmentManager(), fragemnts);
        viewPager.setOffscreenPageLimit(fragemnts.size());
        viewPager.setAdapter(viewPagerAdp);
        //禁止viewpager滑动
        viewPager.setNoScroll(true);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        //个人信息
        showPopDialog();
        HttpRequest.POST(me, Constants.PERSONALMSG, new Parameter()
                .add("departId",departmentId)
                .add("userId",depUserId), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    PersonMessBean personMessBean = gson.fromJson(response, PersonMessBean.class);
                    personName.setText(personMessBean.getMap().getD_name());
                    personCompany.setText(personMessBean.getMap().getDepart());
                    suozaidanwei.setText(personMessBean.getMap().getDepart());
                    proShixiao.setProgress(personMessBean.getMap().getWork_percentage());
                    proXinyong.setProgress(personMessBean.getMap().getCredit_percentage());
                    proChaping.setProgress(personMessBean.getMap().getPolicy_percentage());
                    textWork.setText(personMessBean.getMap().getWork_percentage()+"/100");
                    textChaping.setText(personMessBean.getMap().getPolicy_percentage()+"/100");
                    textXinyong.setText(personMessBean.getMap().getCredit_percentage()+"/400");
                    age.setText(String.valueOf(personMessBean.getMap().getAge()));
                    name.setText(personMessBean.getMap().getD_name());
                    suozaibumen.setText(personMessBean.getMap().getUnit());
                    zhichen.setText(personMessBean.getMap().getJob_name());
                }else{
                    error.getMessage();
                }
            }
        });
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

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }


    public void registerFragmentTouchListener(FragmentTouchListener listener) {
        mFragmentTouchListeners.add(listener);
    }


    public void unRegisterFragmentTouchListener(FragmentTouchListener listener) {
        mFragmentTouchListeners.remove(listener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        for (FragmentTouchListener listener : mFragmentTouchListeners) {
            listener.onTouchEvent(event);
        }

        return super.dispatchTouchEvent(event);
    }

    public interface FragmentTouchListener {
        boolean onTouchEvent(MotionEvent event);
    }

    /*
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