package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ChooseCompanyActivity;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.utils.PickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/30 16:40
 * description : 起草公文---设置信息
 **/

@Layout(R.layout.fgt_set_message)
public class SetMessageFragment extends BaseFgt {

    private LinearLayout setmesType;
    private TextView setmesTypeText;
    private LinearLayout setmesZhuti;
    private LinearLayout setmesHuiqian;
    private LinearLayout setmesTime;

    List<String> data = new ArrayList<String>();

    @Override
    public void initViews() {
        setmesType = (LinearLayout) findViewById(R.id.setmes_type);
        setmesTypeText = (TextView) findViewById(R.id.setmes_type_text);
        setmesZhuti = (LinearLayout) findViewById(R.id.setmes_zhuti);
        setmesHuiqian = (LinearLayout) findViewById(R.id.setmes_huiqian);
        setmesTime = (LinearLayout) findViewById(R.id.setmes_time);
    }

    @Override
    public void initDatas() {


    }

    @Override
    public void setEvents() {

        //公文类型
        setmesType.setOnClickListener(new View.OnClickListener() {

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
                minute_pv.setOnSelectListener(new PickerView.onSelectListener()
                {

                    @Override
                    public void onSelect(String text)
                    {
                        Toast.makeText(getContext(), "选择了 " + text + " 分",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });

        //会签单位
        setmesHuiqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChooseCompanyActivity.class);
            }
        });
    }

    public static SetMessageFragment newInstance() {
        return new SetMessageFragment();
    }

}
