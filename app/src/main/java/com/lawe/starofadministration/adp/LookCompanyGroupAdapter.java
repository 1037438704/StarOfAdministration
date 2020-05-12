package com.lawe.starofadministration.adp;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/5/12 9:41
 * description : 常用组---分组下的人
 */
public class LookCompanyGroupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public LookCompanyGroupAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public LookCompanyGroupAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
