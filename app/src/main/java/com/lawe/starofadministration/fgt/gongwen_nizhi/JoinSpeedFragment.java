package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.JoinSpeedAdapter;
import com.lawe.starofadministration.aty.DraftActivity;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;
import com.lawe.starofadministration.config.Constants;

import org.json.JSONException;
import org.json.JSONObject;

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
    private String id = null;

    private JoinSpeedAdapter joinSpeedAdapter;
    private RecyclerView joinSpeedTodoRecycle;
    private String personType;

    @Override
    public void initViews() {
        super.initViews();
        joinSpeedRecycle = (RecyclerView) findViewById(R.id.join_speed_recycle);
        joinSpeedTodoRecycle = (RecyclerView) findViewById(R.id.join_speed_todo_recycle);

        id = ((DraftActivity)fgtContext).toValue();
        personType = ((DraftActivity)fgtContext).toPerson();


        //进度列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(me);
        joinSpeedRecycle.setLayoutManager(layoutManager);
        joinSpeedAdapter = new JoinSpeedAdapter(R.layout.item_speed_list);
        joinSpeedAdapter.setPersonType(personType);
        joinSpeedRecycle.setAdapter(joinSpeedAdapter);

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
        JSONObject json = new JSONObject();
        try {
            json.put("id",id);  //业务ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonTodo = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.CURRENTTODOTASKPROCESS, jsonTodo, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {

            }
        });
    }

    //查询历史任务
    private void getHistory() {
        JSONObject json = new JSONObject();
        try {
            json.put("id",id);  //业务ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonHistory = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.CURRRNTTASKPROCESS, jsonHistory, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                JoinSpeedHistoryBean joinSpeedHistoryBean = gson.fromJson(response, JoinSpeedHistoryBean.class);
                Log.e("xaxaxaxa",joinSpeedHistoryBean+"-------------------");
                List<JoinSpeedHistoryBean.HistoryTaskMapBean.QicaoBean> qicao = joinSpeedHistoryBean.getHistoryTaskMap().getQicao();
                Log.e("xaxaxaxa",qicao.toString());
                joinSpeedAdapter.setNewData(qicao);
            }
        });
    }

    public static JoinSpeedFragment newInstance() {
        return new JoinSpeedFragment();
    }
}