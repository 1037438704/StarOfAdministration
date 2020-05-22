package com.lawe.starofadministration.fgt.gongwen_guidang;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ChooseCompanyActivity;
import com.lawe.starofadministration.aty.ChoosePersonActivity;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.utils.PickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/22 14:09
 * description : 公文归档---核发设置
 */
@Layout(R.layout.fgt_file_setting)
public class FileSettingFragment extends BaseFgt {


    private LinearLayout appSetJiguan;
    private TextView appSetJiguanText;
    private LinearLayout appSetWenzhong;
    private TextView appSetWenzhongText;
    private LinearLayout appSetYear;
    private TextView appSetYearText;
    private LinearLayout appSetTextnum;
    private TextView appSetTextnumText;
    private LinearLayout appSetChooseCompany;
    private TextView appSetChooseCompanyNum;
    private LinearLayout appSetPerson;
    private TextView appSetPersonNum;
    private LinearLayout appSetPerson2;
    private TextView appSetPersonNum2;

    List<String> data = new ArrayList<String>();

    @Override
    public void initViews() {
        appSetJiguan = (LinearLayout) findViewById(R.id.app_set_jiguan);
        appSetJiguanText = (TextView) findViewById(R.id.app_set_jiguan_text);
        appSetWenzhong = (LinearLayout) findViewById(R.id.app_set_wenzhong);
        appSetWenzhongText = (TextView) findViewById(R.id.app_set_wenzhong_text);
        appSetYear = (LinearLayout) findViewById(R.id.app_set_year);
        appSetYearText = (TextView) findViewById(R.id.app_set_year_text);
        appSetTextnum = (LinearLayout) findViewById(R.id.app_set_textnum);
        appSetTextnumText = (TextView) findViewById(R.id.app_set_textnum_text);
        appSetChooseCompany = (LinearLayout) findViewById(R.id.app_set_choose_company);
        appSetChooseCompanyNum = (TextView) findViewById(R.id.app_set_choose_company_num);
        appSetPerson = (LinearLayout) findViewById(R.id.app_set_person);
        appSetPersonNum = (TextView) findViewById(R.id.app_set_person_num);
        appSetPerson2 = (LinearLayout) findViewById(R.id.app_set_person2);
        appSetPersonNum2 = (TextView) findViewById(R.id.app_set_person_num2);
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {
        //机关代字
        appSetJiguan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(getContext(),R.layout.pop_document_type,null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                PickerView minute_pv = view.findViewById(R.id.minute_pv);

                for (int i = 0; i < 10; i++)
                {
                    data.add("0" + i);
                }
                minute_pv.setData(data);

                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });

        //文种
        appSetWenzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //转发至其他单位
        appSetChooseCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChooseCompanyActivity.class);
            }
        });

        //设置报发人
        appSetPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChoosePersonActivity.class);
            }
        });

        //设置归档人
        appSetPerson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChoosePersonActivity.class);
            }
        });

    }

    public static FileSettingFragment newInstance() {
        return new FileSettingFragment();
    }

}
