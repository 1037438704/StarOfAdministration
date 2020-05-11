package com.lawe.starofadministration.adp;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/5/8 15:15
 * description : 常用组---分组下的人
 */
public class LookGroupPeopleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public LookGroupPeopleAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public LookGroupPeopleAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
