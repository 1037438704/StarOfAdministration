package com.lawe.starofadministration.fgt;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.MessageBean;
import com.lawe.starofadministration.config.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/8 10:23
 * description :待办——待办信息
 */
@Layout(R.layout.fgt_message)
public class MessageFragment extends BaseFgt {

    private TextView textCategory;
    private ImageView daibanDown1;
    private TextView textTimer;
    private ImageView daibanDown2;
    private TextView textUrgent;
    private ImageView daibanDown3;
    private LinearLayout textChoose;
    private RecyclerView recycleMessage;
    private LinearLayout linearLeibie;
    private LinearLayout linear_urgent;
    private LinearLayout linear_time;
    private LinearLayout choose_leibie;
    private int flag = 1; //类别默认标识
    private int flag_urgent = 1; //类别默认标识
    private int flag_time = 1; //类别默认标识
    private DrawerLayout drawerLayout;
    private Button drawer_quxiao;

    private MessageAdapter messageAdapter;
    private LinearLayout dealtLinerarTime;
    private LinearLayout dealtLinerarUrgent;
    private CardView cardView;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;
    private int limit = 10;

    @Override
    public void initViews() {
        super.initViews();
        textCategory = (TextView) findViewById(R.id.text_category);
        daibanDown1 = (ImageView) findViewById(R.id.daiban_down1);
        textTimer = (TextView) findViewById(R.id.text_timer);
        daibanDown2 = (ImageView) findViewById(R.id.daiban_down2);
        textUrgent = (TextView) findViewById(R.id.text_urgent);
        daibanDown3 = (ImageView) findViewById(R.id.daiban_down3);
        recycleMessage = (RecyclerView) findViewById(R.id.recycle_message);
        choose_leibie = (LinearLayout) findViewById(R.id.choose_leibie);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        linear_urgent = (LinearLayout) findViewById(R.id.linear_urgent);
        linear_time = (LinearLayout) findViewById(R.id.linear_time);
        dealtLinerarTime = (LinearLayout) findViewById(R.id.dealt_linerar_time);
        dealtLinerarUrgent = (LinearLayout) findViewById(R.id.dealt_linerar_urgent);
        cardView = (CardView) findViewById(R.id.cardview);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);

        textChoose = (LinearLayout) findViewById(R.id.text_choose);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_shaixuan);
        drawer_quxiao = getActivity().findViewById(R.id.drawer_quxiao);

        //待办信息
        recycleMessage.setLayoutManager(new LinearLayoutManager(me));
        messageAdapter = new MessageAdapter(R.layout.item_message_layout);
        recycleMessage.setAdapter(messageAdapter);

        //加载首页数据
        messageData();
    }

    @Override
    public void initDatas() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                limit = 10;
                messageData();
            }
        });
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                messageData();
            }
        });
    }

    //查询所有数据
    private void messageData() {
        showPopDialog();
        JSONObject json = new JSONObject();
        try {
            json.put("page", page);
            json.put("limit", limit);
            json.put("depUserId",depUserId);
            json.put("warningState",null);  //紧急度
            json.put("orderType",null); //接收时间
            json.put("category",null);   //类别
            json.put("beAboutToOverdue",null);   //即将逾期靠前
            json.put("day",null);   //最近幾天
            json.put("startTime",null);   //开始时间
            json.put("endTime",null);   //结束时间
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.LISTFINDALLBYCURRENTUSER, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                MessageBean messageBean = gson.fromJson(response, MessageBean.class);
                List<MessageBean.PageBean.ListBean> list = messageBean.getPage().getList();
                if (page == 1){
                    messageAdapter.setNewData(list);
                    refreshLayout.finishRefresh();
                }else{
                    if(list == null || list.size() == 0){
                        toast("没有更多数据了...");
                        return;
                    }
                    messageAdapter.addData(list);
                    refreshLayout.finishLoadMore();
                }

            }
        });
    }

    @Override
    public void setEvents() {
        //类别选择
        choose_leibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_time.setVisibility(View.GONE);
                linear_urgent.setVisibility(View.GONE);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                flag_urgent = 1;
                if (flag == 1) {
                    linearLeibie.setVisibility(View.VISIBLE);
                    daibanDown1.setImageResource(R.mipmap.shaixuan_3);
                    flag = 2;
                } else {
                    linearLeibie.setVisibility(View.GONE);
                    daibanDown1.setImageResource(R.mipmap.daiban_down);
                    flag = 1;
                }
            }
        });

        //接收时间
        dealtLinerarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                linear_urgent.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                flag_urgent = 1;
                if (flag_time == 1) {
                    linear_time.setVisibility(View.VISIBLE);
                    daibanDown2.setImageResource(R.mipmap.shaixuan_3);
                    flag_time = 2;
                } else {
                    linear_time.setVisibility(View.GONE);
                    daibanDown2.setImageResource(R.mipmap.daiban_down);
                    flag_time = 1;
                }
            }
        });

        //紧急度
        dealtLinerarUrgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                linear_time.setVisibility(View.GONE);daibanDown2.setImageResource(R.mipmap.daiban_down);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                flag = 1;

                if (flag_urgent == 1) {
                    linear_urgent.setVisibility(View.VISIBLE);
                    daibanDown3.setImageResource(R.mipmap.shaixuan_3);
                    flag_urgent = 2;
                } else {
                    linear_urgent.setVisibility(View.GONE);
                    daibanDown3.setImageResource(R.mipmap.daiban_down);
                    flag_urgent = 1;
                }
            }
        });

        //筛选弹出pop
        textChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        drawer_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

}
