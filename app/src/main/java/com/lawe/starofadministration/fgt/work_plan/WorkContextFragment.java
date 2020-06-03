package com.lawe.starofadministration.fgt.work_plan;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private String workType;
    private LinearLayout workTitle;
    private EditText documentSubject;
    private EditText documentTitle;
    private TextView documentNumber;
    private TextView documentSubTitle;
    private WebView webview;
    private String personType;
    private String newWorkFlag;
    private TextView newWorkTitle;

    @Override
    public void initViews() {

        cebianlan = (LinearLayout) findViewById(R.id.cebianlan);
        workTitle = (LinearLayout) findViewById(R.id.work_title);
        documentSubject = (EditText) findViewById(R.id.document_subject);
        documentTitle = (EditText) findViewById(R.id.document_title);
        documentNumber = (TextView) findViewById(R.id.document_number);
        documentSubTitle = (TextView) findViewById(R.id.document_sub_title);
        webview = (WebView) findViewById(R.id.webview);
        newWorkTitle = (TextView) findViewById(R.id.new_work_title);

        SharedPreferences sps = me.getSharedPreferences("ids", Context.MODE_PRIVATE);
        newWorkFlag = sps.getString("newWorkFlag", "");
        //newWorkFlag:新建任务/项目的标识
        if (newWorkFlag.equals("1")) {
            //workTitle.setVisibility(View.VISIBLE);
            documentSubject.setHint("请输入项目的内容");
            //documentSubTitle.setText("项目描述：");
        } else if(newWorkFlag.equals("2")) {
            workTitle.setVisibility(View.GONE);
            documentSubject.setHint("请对任务进行描述");
            documentSubTitle.setText("任务描述：");
        }
        SharedPreferences sp = me.getSharedPreferences("id", Context.MODE_PRIVATE);
        workType = sp.getString("workType", "");
        personType = sp.getString("personType", "");
        Log.e("newWorkFlag1", workType + "----" + personType + "-------" + newWorkFlag);
        //workType：查看项目/任务的标识
        //personType：是创建者还是执行者的标识
        if (workType.equals("false") && personType.equals("1")) {
            workTitle.setVisibility(View.GONE);
            documentSubTitle.setText("任务描述：");
        } else if (workType.equals("false") && personType.equals("2")) {
            workTitle.setVisibility(View.GONE);
            documentSubTitle.setText("任务描述：");
        }else if(workType.equals("true") && personType.equals("1")){
            workTitle.setVisibility(View.VISIBLE);
            documentSubject.setHint("请输入项目的内容");
            documentTitle.setHint("请输入项目标题");
            documentSubTitle.setText("项目描述：");
        }else if(workType.equals("true") && personType.equals("2")){
            workTitle.setVisibility(View.VISIBLE);
            documentSubject.setHint("请输入项目的内容");
            documentTitle.setHint("请输入项目标题");
            documentSubTitle.setText("项目描述：");
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
