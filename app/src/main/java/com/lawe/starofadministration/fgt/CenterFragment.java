package com.lawe.starofadministration.fgt;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.CirculationActivity;
import com.lawe.starofadministration.aty.FictionActivity;
import com.lawe.starofadministration.aty.FileActivity;
import com.lawe.starofadministration.aty.MailListActivity;
import com.lawe.starofadministration.aty.MapInjoyActivity;
import com.lawe.starofadministration.aty.NoticeActivity;
import com.lawe.starofadministration.aty.ReportActivity;
import com.lawe.starofadministration.aty.ShareActivity;
import com.lawe.starofadministration.aty.SupervisionActivity;
import com.lawe.starofadministration.aty.WorkPlanActivity;
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
    private TextView centerDitu;
    private TextView centerTongxunlu;

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
        centerDitu = (TextView) findViewById(R.id.center_ditu);
        centerTongxunlu = (TextView) findViewById(R.id.center_tongxunlu);

        centerLinerarLayout = (LinearLayout) findViewById(R.id.center_linerar_layout);
        centerLinerarLayout.setPadding(0, me.getStatusBarHeight(), 0, 0);
        //设置字体
        centerTitle.setTypeface(getTextMedium);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
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

        //公文归档
        centerGuidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(FileActivity.class);
            }
        });

        //文件报发
        centerBaofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ReportActivity.class);
            }
        });

        //共享文件
        centerGongxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ShareActivity.class);
            }
        });

        //公告通知
        centerTongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(NoticeActivity.class);
            }
        });

        //督察督办
        centerDucha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(SupervisionActivity.class);
            }
        });

        //工作计划
        centerJihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(WorkPlanActivity.class);
            }
        });

        //共享地图
        centerDitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(MapInjoyActivity.class);
            }
        });

        //通讯录
        centerTongxunlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(MailListActivity.class);
            }
        });
    }

    public static CenterFragment newInstance() {
        return new CenterFragment();
    }

}
