package com.lawe.starofadministration.fgt.work_plan;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.JoinSpeedAdapter;
import com.lawe.starofadministration.adp.WorkListAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/29 14:09
 * description : 工作计划---任务目录
 * */
@Layout(R.layout.fgt_join_speed)
public class WorkDongFragment extends BaseFgt {

    private RecyclerView joinSpeedRecycle;

    //空集合
    private List<String> list;
    private WorkListAdapter workListAdapter;

    @Override
    public void initViews() {
        joinSpeedRecycle = (RecyclerView) findViewById(R.id.join_speed_recycle);
        //进度列表
        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(me);
        joinSpeedRecycle.setLayoutManager(layoutManager);
        workListAdapter = new WorkListAdapter(R.layout.item_dong_list);
        joinSpeedRecycle.setAdapter(workListAdapter);

    }

    @Override
    public void initDatas() {
        //进度列表
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        workListAdapter.setNewData(list);
        workListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static WorkDongFragment newInstance() {
        return new WorkDongFragment();
    }

}