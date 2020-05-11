package com.lawe.starofadministration.fgt;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.DraftChatAdapter;
import com.lawe.starofadministration.adp.JoinSpeedAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.ListChatBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 14:09
 * description : 公文会签---办理进度
 */
@Layout(R.layout.fgt_join_speed)
public class JoinSpeedFragment extends BaseFgt {

    private RecyclerView joinSpeedRecycle;

    //空集合
    private List<String> list;
    private JoinSpeedAdapter joinSpeedAdapter;

    @Override
    public void initViews() {
        initView();

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

    public static JoinSpeedFragment newInstance() {
        return new JoinSpeedFragment();
    }

    private void initView() {
        joinSpeedRecycle = (RecyclerView) findViewById(R.id.join_speed_recycle);
    }
}
