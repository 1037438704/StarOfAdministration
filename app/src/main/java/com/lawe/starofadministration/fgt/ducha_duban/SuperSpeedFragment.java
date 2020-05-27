package com.lawe.starofadministration.fgt.ducha_duban;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * date : 2020/5/26 14:09
 * description : 督察督办---办理流程
 */
@Layout(R.layout.fgt_join_speed)
public class SuperSpeedFragment extends BaseFgt {

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
        joinSpeedAdapter.setNewData(list);
        joinSpeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static SuperSpeedFragment newInstance() {
        return new SuperSpeedFragment();
    }

    private void initView() {

    }
}