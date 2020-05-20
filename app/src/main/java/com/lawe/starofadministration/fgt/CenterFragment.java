package com.lawe.starofadministration.fgt;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.CirculationActivity;
import com.lawe.starofadministration.aty.FictionActivity;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : fuke
 * date : 2020/4/28 16:36
 * description : 中间工作台
 */
@Layout(R.layout.fgt_center)
public class CenterFragment extends BaseFgt {

    private TextView centerTitle;
    private TextView centerNizhi;
    private LinearLayout centerLinerarLayout;
    private TextView centerChuanyue;
    private TextView centerGuidang;
    private TextView centerBaofa;
    private TextView centerGongxiang;
    private TextView centerTongzhi;
    private TextView centerDucha;
    private TextView centerJihua;

    @Override
    public void initViews() {
        centerTitle = (TextView) findViewById(R.id.center_title);
        centerNizhi = (TextView) findViewById(R.id.center_nizhi);
        centerChuanyue = (TextView) findViewById(R.id.center_chuanyue);
        centerGuidang = (TextView) findViewById(R.id.center_guidang);
        centerBaofa = (TextView) findViewById(R.id.center_baofa);
        centerGongxiang = (TextView) findViewById(R.id.center_gongxiang);
        centerTongzhi = (TextView) findViewById(R.id.center_tongzhi);
        centerDucha = (TextView) findViewById(R.id.center_ducha);
        centerJihua = (TextView) findViewById(R.id.center_jihua);
        centerLinerarLayout = (LinearLayout) findViewById(R.id.center_linerar_layout);
        centerLinerarLayout.setPadding(0, me.getStatusBarHeight(), 0, 0);
        //设置字体
        centerTitle.setTypeface(getTextMedium);
    }

    @Override
    public void initDatas() {
        //公文拟制
        centerNizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(FictionActivity.class);
            }
        });

        //收文传阅
        centerChuanyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(CirculationActivity.class);
            }
        });
    }

    @Override
    public void setEvents() {

    }

    public static CenterFragment newInstance() {
        return new CenterFragment();
    }
}
