package com.lawe.starofadministration.fgt;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.adp.NoticeMessageAdapter;
import com.lawe.starofadministration.aty.ChatActivity;
import com.lawe.starofadministration.aty.LookAllActivity;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dell
 * date : 2020/4/8 10:23
 * description :待办----通知信息
 */
@Layout(R.layout.fgt_notice)
public class NoticeFragment extends BaseFgt {

    private TextView text_lookAll;
    private TextView text_num;
    private RecyclerView recycleMessageList;
    //空集合
    private List<String> list;
    private NoticeMessageAdapter noticeAdapter;

    @Override
    public void initViews() {
        text_lookAll = (TextView) findViewById(R.id.text_lookAll);
        text_num = (TextView) findViewById(R.id.text_num);
        recycleMessageList = (RecyclerView) findViewById(R.id.recycle_messageList);

        list = new ArrayList<>();
        //待办信息
        recycleMessageList.setLayoutManager(new LinearLayoutManager(me));
        noticeAdapter = new NoticeMessageAdapter(R.layout.item_message_list);
        recycleMessageList.setAdapter(noticeAdapter);
    }

    @Override
    public void initDatas() {

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        noticeAdapter.setNewData(list);
        noticeAdapter.notifyDataSetChanged();

    }

    @Override
    public void setEvents() {
        //查看全部--跳转
        text_lookAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(LookAllActivity.class);
            }
        });

        //适配器点击事件
       noticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               jump(ChatActivity.class);
           }
       });

    }

    public static NoticeFragment newInstance() {
        return new NoticeFragment();
    }

}
