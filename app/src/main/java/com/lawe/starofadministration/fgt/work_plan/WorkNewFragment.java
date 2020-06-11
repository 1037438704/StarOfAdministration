package com.lawe.starofadministration.fgt.work_plan;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.NewWorkAdapter;
import com.lawe.starofadministration.aty.NewWorkActivity;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/29 14:08
 * description : 工作计划----任务
 */
@Layout(R.layout.fgt_work_new)
public class WorkNewFragment extends BaseFgt {

    private RecyclerView enclosureRecycle;

    private NewWorkAdapter newWorkAdapter;
    //空集合
    private List<String> list;
    private LinearLayout newNewChild;

    @Override
    public void initViews() {
        super.initViews();
        enclosureRecycle = (RecyclerView) findViewById(R.id.enclosure_recycle);
        newNewChild = (LinearLayout) findViewById(R.id.new_new_child);
        //任务列表
        list = new ArrayList<>();
        enclosureRecycle.setNestedScrollingEnabled(false);
        enclosureRecycle.setLayoutManager(new LinearLayoutManager(me));
        newWorkAdapter = new NewWorkAdapter(R.layout.item_new_work);
        enclosureRecycle.setAdapter(newWorkAdapter);

    }

    @Override
    public void initDatas() {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        newWorkAdapter.setNewData(list);
        newWorkAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        newNewChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(NewWorkActivity.class,new JumpParameter()
                    .put("newWorkFlag","2")
                    );
            }
        });
    }


    public static WorkNewFragment newInstance() {
        return new WorkNewFragment();
    }

}
