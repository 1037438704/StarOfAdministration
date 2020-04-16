package com.lawe.starofadministration.fgt;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
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
    private TextView text_set;
    private TextView set_out;
    private TextView set_safe;
    private TextView setSystem;
    private TextView setService;
    private TextView setItem;
    private TextView setExplanation;
    private TextView setUpgread;
    private TextView setFeedback;

    @Override
    public void initViews() {
        fragmentLauout = (LinearLayout) findViewById(R.id.fragment_lauout);
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
