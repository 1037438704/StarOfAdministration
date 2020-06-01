package com.lawe.starofadministration.adp;

import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/6/1 15:52
 * description : 共享地图
 */
public class MapAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public MapAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public MapAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemTitle = helper.itemView.findViewById(R.id.item_map_title);
        LinearLayout itemLinear = helper.itemView.findViewById(R.id.item_map);
        itemTitle.setTypeface(getTextMedium);

    }
}
