package com.lawe.starofadministration.fgt;

import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dell
 * date : 2020/4/7 16:36
 * description :
 */

@Layout(R.layout.fgt_datas)
public class DatasFragment extends BaseFgt {

    private RadioGroup mainRgp;
    private ViewPager viewPager;
    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;
    private TextView dataDataCenter;
    private TextView dataKaohe;
    private TextView dataPaiming;
    private TextView dataKoufen;
    private TextView dataJixiao;
    private TextView dataXinyong;
    private ContentView view;

    @Override
    public void initViews() {

        fragemnts = new ArrayList<>();
        mainRgp = (RadioGroup) findViewById(R.id.main_rgp);
        viewPager = (ViewPager) findViewById(R.id.viewPagerData);

        dataDataCenter = (TextView) findViewById(R.id.data_dataCenter);
        dataKaohe = (TextView) findViewById(R.id.data_kaohe);
        dataPaiming = (TextView) findViewById(R.id.data_paiming);
        dataKoufen = (TextView) findViewById(R.id.data_koufen);

        dataJixiao = (TextView) findViewById(R.id.data_jixiao);
        dataXinyong = (TextView) findViewById(R.id.data_xinyong);

        dataDataCenter.setTypeface(getTextMedium);
        dataKaohe.setTypeface(getTextNum);
        dataKoufen.setTypeface(getTextNum);
        dataPaiming.setTypeface(getTextNum);

        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);

    }

    @Override
    public void initDatas() {
        fragemnts.add(CompanyFragment.newInstance());
        fragemnts.add(PersonFragment.newInstance());
        fragemnts.add(DeductionFragment.newInstance());
        fragemnts.add(LookDataFragment.newInstance());

        viewPagerAdp = new ViewPagerAdp(me.getSupportFragmentManager(), fragemnts);
        viewPager.setOffscreenPageLimit(fragemnts.size());
        viewPager.setAdapter(viewPagerAdp);

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

        //绩效
        dataJixiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(getContext(),R.layout.pop_data_jixiao,null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView pop_pingjia = view.findViewById(R.id.data_pop_ji_pingjia);
                TextView pop_message = view.findViewById(R.id.data_pop_ji_message);
                TextView pop_time = view.findViewById(R.id.data_pop_ji_time);

                pop_message.setTypeface(getTextMedium);
                pop_pingjia.setTypeface(getTextMedium);
                pop_time.setTypeface(getTextNum);

                dialog.show();
            }
        });

        //信用
        dataXinyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(getContext(),R.layout.pop_data_xinyong,null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView pop_text1 = view.findViewById(R.id.data_pop_xin_text1);
                TextView pop_message = view.findViewById(R.id.data_pop_xin_message);
                TextView pop_time = view.findViewById(R.id.data_pop_xin_time);

                pop_message.setTypeface(getTextMedium);
                pop_text1.setTypeface(getTextMedium);
                pop_time.setTypeface(getTextNum);

                dialog.show();
            }
        });
    }

    public static DatasFragment newInstance() {
        return new DatasFragment();
    }

}
