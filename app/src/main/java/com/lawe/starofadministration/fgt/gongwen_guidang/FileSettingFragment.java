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

    List<String> data = new ArrayList<String>();

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {

    }

    public static FileSettingFragment newInstance() {
        return new FileSettingFragment();
    }

}
