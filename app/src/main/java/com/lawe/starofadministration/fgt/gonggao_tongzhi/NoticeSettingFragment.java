package com.lawe.starofadministration.fgt.gonggao_tongzhi;

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
 * date : 2020/5/25 14:09
 * description : 公告通知---设置信息
 */
@Layout(R.layout.fgt_notice_setting)
public class NoticeSettingFragment extends BaseFgt {

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {

    }

    public static NoticeSettingFragment newInstance() {
        return new NoticeSettingFragment();
    }

}
