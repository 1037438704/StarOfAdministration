package com.lawe.starofadministration.fgt.work_plan;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.EnclosureAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/29 14:08
 * description : 工作计划----附件目录
 */
@Layout(R.layout.fgt_work_enclosure)
public class WorkEclosureFragment extends BaseFgt {

    private RecyclerView enclosureRecycle;
    private TextView enclosureText;
    private EnclosureAdapter enclosureAdapter;
    //空集合
    private List<String> list;

    @Override
    public void initViews() {
        super.initViews();
        enclosureRecycle = (RecyclerView) findViewById(R.id.enclosure_recycle);

        //附件列表
        list = new ArrayList<>();
        enclosureRecycle.setNestedScrollingEnabled(false);
        enclosureRecycle.setLayoutManager(new LinearLayoutManager(me));
        enclosureAdapter = new EnclosureAdapter(R.layout.item_enclosure);
        enclosureRecycle.setAdapter(enclosureAdapter);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }


    public static WorkEclosureFragment newInstance() {
        return new WorkEclosureFragment();
    }
}
