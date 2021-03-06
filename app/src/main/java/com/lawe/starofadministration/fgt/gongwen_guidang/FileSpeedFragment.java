package com.lawe.starofadministration.fgt.gongwen_guidang;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.JoinSpeedAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/22 14:09
 * description : 公文归档---办理进度
 */
@Layout(R.layout.fgt_join_speed)
public class FileSpeedFragment extends BaseFgt {

    private RecyclerView joinSpeedRecycle;

    //空集合
    private List<String> list;
    private JoinSpeedAdapter joinSpeedAdapter;

    @Override
    public void initViews() {
        joinSpeedRecycle = (RecyclerView) findViewById(R.id.join_speed_recycle);
        //进度列表
        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(me);
        joinSpeedRecycle.setLayoutManager(layoutManager);
        joinSpeedAdapter = new JoinSpeedAdapter(R.layout.item_speed_list);
        joinSpeedRecycle.setAdapter(joinSpeedAdapter);

    }

    @Override
    public void initDatas() {
        //进度列表
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        //joinSpeedAdapter.setNewData(list);
        joinSpeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static FileSpeedFragment newInstance() {
        return new FileSpeedFragment();
    }

}