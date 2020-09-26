package com.lawe.starofadministration.fgt.chat;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : fuke
 * date : 2020/6/1 17:16
 * description : 个人信息---勋章信息
 */
@Layout(R.layout.fgt_chat_xunzhang)
public class ChatxunzhangFragment extends BaseFgt {

    private RecyclerView chatXunJichuRecycle;
    private RecyclerView chatXunRongRecycle;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void initViews() {
        chatXunJichuRecycle = (RecyclerView) findViewById(R.id.chat_xun_jichu_recycle);
        chatXunRongRecycle = (RecyclerView) findViewById(R.id.chat_xun_rong_recycle);

        gridLayoutManager = new GridLayoutManager(me, 4);

        chatXunJichuRecycle.setLayoutManager(gridLayoutManager);
        chatXunRongRecycle.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static ChatxunzhangFragment newInstance() {
        return new ChatxunzhangFragment();
    }

}
