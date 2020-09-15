package com.lawe.starofadministration.adp;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.MyMedalBean;
import com.lawe.starofadministration.bean.PersonMessBean;
import com.lawe.starofadministration.utils.GlidUtils;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/4/13 15:15
 * description : 我的勋章
 */
public class MyMedalsAdapter extends BaseQuickAdapter<MyMedalBean.MapBean.ListBean.MedalsBean, BaseViewHolder> {

    public MyMedalsAdapter(int layoutResId, List<MyMedalBean.MapBean.ListBean.MedalsBean> data) {
        super(layoutResId, data);
    }

    public MyMedalsAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMedalBean.MapBean.ListBean.MedalsBean listBeans) {
        ImageView itemImg = helper.itemView.findViewById(R.id.itemImg);
        GlidUtils.defaultGlid(getContext(),listBeans.getMedalPhoto(),itemImg);
        helper.setText(R.id.itemName,listBeans.getMedalName());
    }
}
