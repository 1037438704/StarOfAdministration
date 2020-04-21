package com.lawe.starofadministration.fgt;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ExplanaActivity;
import com.lawe.starofadministration.aty.FeedBackActivity;
import com.lawe.starofadministration.aty.ServiceActivity;
import com.lawe.starofadministration.aty.SetSafeActivity;
import com.lawe.starofadministration.aty.SystemActivity;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/7 16:36
 * description :
 */

@Layout(R.layout.fgt_settings)
public class SettingsFragment extends BaseFgt {

    private TextView text_set;
    private TextView set_out;
    private TextView set_safe;
    private TextView setSystem;
    private TextView setService;
    private TextView setItem;
    private TextView setExplanation;
    private TextView setUpgread;
    private TextView setFeedback;
    private LinearLayout linearSetSafe;
    private LinearLayout linearSetSystem;
    private LinearLayout linearSetService;
    private LinearLayout linearSetItem;
    private LinearLayout linearSetExplanation;
    private LinearLayout linearSetUpgread;
    private LinearLayout linearSetFeedback;

    private int flag = 2;

    @Override
    public void initViews() {
        text_set = (TextView) findViewById(R.id.text_set);
        set_out = (TextView) findViewById(R.id.set_out);
        set_safe = (TextView) findViewById(R.id.set_safe);
        setSystem = (TextView) findViewById(R.id.set_system);
        setService = (TextView) findViewById(R.id.set_service);
        setItem = (TextView) findViewById(R.id.set_item);
        setExplanation = (TextView) findViewById(R.id.set_explanation);
        setUpgread = (TextView) findViewById(R.id.set_upgread);
        setFeedback = (TextView) findViewById(R.id.set_feedback);

        text_set.setTypeface(getTextMedium);
        set_out.setTypeface(getTextMedium);
        set_safe.setTypeface(getTextRegular);
        setSystem.setTypeface(getTextRegular);
        setService.setTypeface(getTextRegular);
        setItem.setTypeface(getTextRegular);
        setExplanation.setTypeface(getTextRegular);
        setUpgread.setTypeface(getTextRegular);
        setFeedback.setTypeface(getTextRegular);

        linearSetSafe = (LinearLayout) findViewById(R.id.linear_set_safe);
        linearSetSystem = (LinearLayout) findViewById(R.id.linear_set_system);
        linearSetService = (LinearLayout) findViewById(R.id.linear_set_service);
        linearSetItem = (LinearLayout) findViewById(R.id.linear_set_item);
        linearSetExplanation = (LinearLayout) findViewById(R.id.linear_set_explanation);
        linearSetUpgread = (LinearLayout) findViewById(R.id.linear_set_upgread);
        linearSetFeedback = (LinearLayout) findViewById(R.id.linear_set_feedback);

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //检查是否有更新
        linearSetUpgread.setOnClickListener(new View.OnClickListener() {

            private Button setAppUpdate;
            private Button setAppCancle;
            private TextView setAppUpdateContent;
            private TextView setAppUpdateExplain;
            private TextView setAppUpdateTime;
            private TextView setAppStartTime;
            private TextView setAppCode;
            private TextView setUpdate;

            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    //有更新，弹框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    View view = View.inflate(getContext(), R.layout.pop_set_update, null);
                    builder.setView(view);
                    builder.setCancelable(true);

                    setUpdate = view.findViewById(R.id.set_update);
                    setAppCode = view.findViewById(R.id.set_app_code);
                    setAppStartTime = view.findViewById(R.id.set_app_startTime);
                    setAppUpdateTime = view.findViewById(R.id.set_app_updateTime);
                    setAppUpdateExplain = view.findViewById(R.id.set_app_updateExplain);
                    setAppUpdateContent = view.findViewById(R.id.set_app_updateContent);
                    setAppCancle = view.findViewById(R.id.set_app_cancle);
                    setAppUpdate = view.findViewById(R.id.set_app_update);

                    setUpdate.setTypeface(getTextMedium);
                    setAppCode.setTypeface(getTextRegular);
                    setAppStartTime.setTypeface(getTextRegular);
                    setAppUpdateTime.setTypeface(getTextRegular);
                    setAppUpdateExplain.setTypeface(getTextRegular);
                    setAppUpdateContent.setTypeface(getTextRegular);
                    setAppCancle.setTypeface(getTextRegular);
                    setAppUpdate.setTypeface(getTextMedium);

                    //取消或确定按钮监听事件处理
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    setAppCancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    //无更新，提示
                    toast("已经是最新版本啦");
                }
            }
        });

        //安全设置
        linearSetSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(SetSafeActivity.class);
            }
        });

        //系统设置
        linearSetSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(SystemActivity.class);
            }
        });

        //问题反馈
        linearSetFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(FeedBackActivity.class);
            }
        });

        //服务中心
        linearSetService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ServiceActivity.class);
            }
        });

        //操作说明
        linearSetExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ExplanaActivity.class);
            }
        });
        
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

}
