package com.lawe.starofadministration.adp;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author :
 * date : 2020/4/13 15:15
 * description :
 */
public class EnclosureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    public Typeface getTextBold = MyApplication.getTextBold;

    public EnclosureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public EnclosureAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView item_num = helper.itemView.findViewById(R.id.enclosure_item_num);
        TextView item_title = helper.itemView.findViewById(R.id.enclosure_item_title);
        TextView item_time = helper.itemView.findViewById(R.id.enclosure_item_time);
        //设置字体
        item_num.setTypeface(getTextBold);
        item_title.setTypeface(getTextMedium);
        item_time.setTypeface(getTextBold);

    }
}
