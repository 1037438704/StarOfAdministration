package com.lawe.starofadministration.fgt.work_plan;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.WorkSpeedAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/29 14:09
 * description : 工作计划---任务目录
 */
@Layout(R.layout.fgt_workspeed)
public class WorkSpeedFragment extends BaseFgt {

    private RecyclerView joinSpeedRecycle;

    //空集合
    private List<String> list;
    private WorkSpeedAdapter workSpeedAdapter;


    @Override
    public void initViews() {
        joinSpeedRecycle = (RecyclerView) findViewById(R.id.join_speed_recycle);

        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(me);
        joinSpeedRecycle.setLayoutManager(layoutManager);
        workSpeedAdapter = new WorkSpeedAdapter(R.layout.item_work_list);
        joinSpeedRecycle.setAdapter(workSpeedAdapter);

    }

    @Override
    public void initDatas() {
        //进度列表
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        workSpeedAdapter.setNewData(list);
        workSpeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static WorkSpeedFragment newInstance() {
        return new WorkSpeedFragment();
    }

    private void initView() {

    }
}