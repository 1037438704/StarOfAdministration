package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
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
import com.lawe.starofadministration.aty.DraftActivity;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;
import com.lawe.starofadministration.bean.JoinSpeedToDoBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.map.JSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
       //LinearLayoutManager layoutManager = new LinearLayoutManager(me);
        joinSpeedRecycle.setLayoutManager(new LinearLayoutManager(me));
        joinSpeedTodoRecycle.setLayoutManager(new LinearLayoutManager(me));

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
                List<JoinSpeedToDoBean.TaskMapBean.CreateFileBean> createFile = joinSpeedToDoBean.getTaskMap().getCreateFile();

                joinSpeedToDoAdapter.setNewData(createFile);
                joinSpeedAdapter.addChildClickViewIds(R.id.liucheng_yijiancuiban);
                joinSpeedAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        List<JoinSpeedHistoryBean.HistoryTaskMapBean.SumBean> data = joinSpeedAdapter.getData();
                        oneClickReminder();
                    }
                });
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

                joinSpeedAdapter.addChildClickViewIds(R.id.liucheng_yijiancuiban);
                joinSpeedAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        List<JoinSpeedHistoryBean.HistoryTaskMapBean.SumBean> data = joinSpeedAdapter.getData();
                        oneClickReminder();
                    }
                });
            }
        });
    }

    //一键催办
    private void oneClickReminder() {
        JSONObject json = new JSONObject();
        try {
           json.put("businessKey",fictionId);
           json.put("businessKeyType","1");
           json.put("delayTime","");
           json.put("id","");
           json.put("messContent","");
           json.put("messType","8");
           json.put("messageCount","");
           json.put("sendTime","");
           json.put("sysUserId",depUserId);
           json.put("title","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.ONECLICKREMINDER, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error==null){
                    toast("一键催办成功");
                }else{
                    error.getMessage();
                }
            }
        });
    }

    public static JoinSpeedFragment newInstance() {
        return new JoinSpeedFragment();
    }
}