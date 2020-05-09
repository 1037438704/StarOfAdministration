package com.lawe.starofadministration.adp;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/5/8 11:15
 * description :  其他部门列表
 */
public class OtherBumenAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public OtherBumenAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public OtherBumenAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemChooseBuMenName = helper.itemView.findViewById(R.id.item_choose_buMen_name);
       itemChooseBuMenName.setTypeface(getTextMedium);
    }
}
