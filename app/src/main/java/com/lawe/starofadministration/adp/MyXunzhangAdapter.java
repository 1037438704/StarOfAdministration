package com.lawe.starofadministration.adp;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.MessageBean;
import com.lawe.starofadministration.bean.PersonMessBean;
import com.lawe.starofadministration.utils.GlidUtils;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/4/13 15:15
 * description : 我的勋章
 */
public class MyXunzhangAdapter extends BaseQuickAdapter<PersonMessBean.MapBean.ListBean, BaseViewHolder> {

    public MyXunzhangAdapter(int layoutResId, List<PersonMessBean.MapBean.ListBean> data) {
        super(layoutResId, data);
    }

    public MyXunzhangAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonMessBean.MapBean.ListBean listBeans) {
        ImageView itemImg = helper.itemView.findViewById(R.id.itemImg);
        GlidUtils.defaultGlid(getContext(),listBeans.getMedalPhoto(),itemImg);
        helper.setText(R.id.itemName,listBeans.getMedalName());
    }
}
