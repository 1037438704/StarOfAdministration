package com.lawe.starofadministration.adp;

import android.widget.TextView;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.MessageBean;
import java.util.List;

/**
 * author :  fuke
 * date : 2020/4/13 15:15
 * description : 首页---待办
 */
public class MessageAdapter extends BaseQuickAdapter< MessageBean.PageBean.ListBean, BaseViewHolder> {

    public MessageAdapter(int layoutResId, List<MessageBean.PageBean.ListBean> data) {
        super(layoutResId, data);
    }

    public MessageAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.PageBean.ListBean listBeans) {
        helper.setText(R.id.message_title,listBeans.getDocTitle());
        helper.setText(R.id.number,listBeans.getPitId());
        helper.setText(R.id.timer,listBeans.getCreateTime());
    }
}
