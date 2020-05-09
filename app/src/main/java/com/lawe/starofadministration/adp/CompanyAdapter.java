package com.lawe.starofadministration.adp;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * author :
 * date : 2020/4/13 15:15
 * description :
 */
public class CompanyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public CompanyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public CompanyAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
