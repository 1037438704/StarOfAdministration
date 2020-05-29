package com.lawe.starofadministration.fgt.work_plan;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.WorkPlanAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/28 11:50
 * description : 工作计划---进行中
 */
@Layout(R.layout.fgt_work_plan)
public class ConductFragment extends BaseFgt {

    private RecyclerView workRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private WorkPlanAdapter workPlanAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView workTop;

    @Override
    public void initViews() {
        workRecycle = (RecyclerView) findViewById(R.id.work_recycle);
        workTop = (ImageView) findViewById(R.id.work_top);
        //列表
        list = new ArrayList<>();
        workPlanAdapter = new WorkPlanAdapter(R.layout.item_work_conduct);
    }

    @Override
    public void initDatas() {
        layoutManager = new LinearLayoutManager(me);
        workRecycle.setLayoutManager(layoutManager);
        workRecycle.setAdapter(workPlanAdapter);

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        workPlanAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        workRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount", firstCompletelyVisibleItemPosition + "");
                if (firstCompletelyVisibleItemPosition >= maxRecycleCount) {
                    workTop.setVisibility(View.VISIBLE);
                } else if (firstCompletelyVisibleItemPosition == 0) {
                    workTop.setVisibility(View.GONE);
                }
            }

        });

        //回到第一条item
        workTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workRecycle.smoothScrollToPosition(0);
            }
        });
    }

    public static ConductFragment newInstance() {
        return new ConductFragment();
    }

}
