package com.lawe.starofadministration.fgt;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/7 16:36
 * description :
 */

@Layout(R.layout.fgt_settings)
public class SettingsFragment extends BaseFgt {
    private LinearLayout fragmentLauout;
    private TextView texttype;

    @Override
    public void initViews() {
        fragmentLauout = (LinearLayout) findViewById(R.id.fragment_lauout);
        texttype = (TextView) findViewById(R.id.texttype);
        //欧克了
//        texttype.setTypeface(fromAsset);

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

}
