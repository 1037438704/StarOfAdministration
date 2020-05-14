package com.lawe.starofadministration.fgt;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
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
    private RecyclerView recyclerLeibie;
    private LinearLayout choose_leibie;
    private int flag = 1; //类别默认标识
    private DrawerLayout drawerLayout;
    private Button drawer_quxiao;

    //空集合
    private List<String> list;
    private MessageAdapter messageAdapter;
    private TextView messageTitle;

    @Override
    public void initViews() {
        textCategory = (TextView) findViewById(R.id.text_category);
        daibanDown1 = (ImageView) findViewById(R.id.daiban_down1);
        textTimer = (TextView) findViewById(R.id.text_timer);
        daibanDown2 = (ImageView) findViewById(R.id.daiban_down2);
        textUrgent = (TextView) findViewById(R.id.text_urgent);
        daibanDown3 = (ImageView) findViewById(R.id.daiban_down3);
        recycleMessage = (RecyclerView) findViewById(R.id.recycle_message);
        choose_leibie = (LinearLayout) findViewById(R.id.choose_leibie);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);
        textChoose = (LinearLayout) findViewById(R.id.text_choose);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_shaixuan);
        drawer_quxiao = getActivity().findViewById(R.id.drawer_quxiao);
        list = new ArrayList<>();
        //待办信息
        recycleMessage.setLayoutManager(new LinearLayoutManager(me));
        messageAdapter = new MessageAdapter(R.layout.item_message_layout);
        recycleMessage.setAdapter(messageAdapter);
    }

    @Override
    public void initDatas() {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        messageAdapter.setNewData(list);
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        //类别选择
        choose_leibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
