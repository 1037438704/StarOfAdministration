package com.lawe.starofadministration.fgt.work_plan;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : fuke
 * date : 2020/5/29 14:08
 * description : 工作计划----项目内容
 */
@Layout(R.layout.fgt_document_edit)
public class WorkContextFragment extends BaseFgt {

    private LinearLayout cebianlan;
    private String newWorkFlag;
    private LinearLayout workTitle;
    private EditText documentSubject;

    @Override
    public void initViews() {

        cebianlan = (LinearLayout) findViewById(R.id.cebianlan);
        workTitle = (LinearLayout) findViewById(R.id.work_title);
        documentSubject = (EditText) findViewById(R.id.document_subject);

        SharedPreferences sp = me.getSharedPreferences("id", Context.MODE_PRIVATE);
        newWorkFlag = sp.getString("newWorkFlag", "");
        Log.e("newWorkFlag1", newWorkFlag);

        if (newWorkFlag.equals("2")) {
            workTitle.setVisibility(View.GONE);
            documentSubject.setHint("请对任务进行描述");
        }

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static WorkContextFragment newInstance() {
        return new WorkContextFragment();
    }

}
