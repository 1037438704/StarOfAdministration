package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.JoinSpeedAdapter;
import com.lawe.starofadministration.adp.JoinSpeedToDoAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;
import com.lawe.starofadministration.bean.JoinSpeedToDoBean;
import com.lawe.starofadministration.config.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 14:09
 * description : 公文会签---办理进度
 */
@Layout(R.layout.fgt_join_speed)
public class JoinSpeedFragment extends BaseFgt {

    private RecyclerView joinSpeedRecycle;
    private String fictionId = null;

    private JoinSpeedAdapter joinSpeedAdapter;
    private RecyclerView joinSpeedTodoRecycle;
    private String personType;
    private JoinSpeedToDoAdapter joinSpeedToDoAdapter;

    @Override
    public void initViews() {
        super.initViews();
        joinSpeedRecycle = (RecyclerView) findViewById(R.id.join_speed_recycle);
        joinSpeedTodoRecycle = (RecyclerView) findViewById(R.id.join_speed_todo_recycle);

        SharedPreferences fictionIdSp = getContext().getSharedPreferences("fictionId", Context.MODE_PRIVATE);
        fictionId = fictionIdSp.getString("fictionId", "");
        personType = fictionIdSp.getString("personType", "");

        //进度列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(me) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(me) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        joinSpeedRecycle.setLayoutManager(layoutManager);
        joinSpeedTodoRecycle.setLayoutManager(layoutManager2);

        joinSpeedAdapter = new JoinSpeedAdapter(R.layout.item_speed_list);
        joinSpeedRecycle.setAdapter(joinSpeedAdapter);

        joinSpeedToDoAdapter = new JoinSpeedToDoAdapter(R.layout.item_speed_list);
        joinSpeedTodoRecycle.setAdapter(joinSpeedToDoAdapter);
    }

    @Override
    public void initDatas() {
        //查询历史进度
        getHistory();
        //查询待办进度
        getTodo();
    }

    @Override
    public void setEvents() {

    }

    //查询待办进度
    private void getTodo() {
        HttpRequest.POST(me, Constants.CURRENTTODOTASKPROCESS, new Parameter().add("id",fictionId), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                JoinSpeedToDoBean joinSpeedToDoBean = gson.fromJson(response, JoinSpeedToDoBean.class);
                List<JoinSpeedToDoBean.TaskMapBean.SumBean> createFile = joinSpeedToDoBean.getTaskMap().getListSumBean();

                joinSpeedToDoAdapter.setNewData(createFile);
                joinSpeedAdapter.addChildClickViewIds(R.id.liucheng_yijiancuiban);
            }
        });
    }

    //查询历史任务
    private void getHistory() {

        HttpRequest.POST(me, Constants.CURRRNTTASKPROCESS, new Parameter().add("id",fictionId), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                JoinSpeedHistoryBean joinSpeedHistoryBean = gson.fromJson(response, JoinSpeedHistoryBean.class);
                JoinSpeedHistoryBean.HistoryTaskMapBean historyTaskMap = joinSpeedHistoryBean.getHistoryTaskMap();
                List<JoinSpeedHistoryBean.HistoryTaskMapBean.SumBean> listSumBean = historyTaskMap.getListSumBean();
                joinSpeedAdapter.setNewData(listSumBean);
            }
        });
    }

    public static JoinSpeedFragment newInstance() {
        return new JoinSpeedFragment();
    }
}