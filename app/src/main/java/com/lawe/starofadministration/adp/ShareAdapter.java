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
 * author :
 * date : 2020/5/25 15:52
 * description : 共享文件
 */
public class ShareAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public ShareAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public ShareAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemFictionTitle = helper.itemView.findViewById(R.id.item_fiction_title);
        itemFictionTitle.setTypeface(getTextMedium);

    }
}
