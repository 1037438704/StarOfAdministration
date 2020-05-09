package com.lawe.starofadministration.adp;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author :
 * date : 2020/4/13 15:15
 * description :
 */
public class TemplateAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public TemplateAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public TemplateAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView messageTitle = helper.itemView.findViewById(R.id.message_title);
        //messageTitle.setTypeface(getTextMedium);

      /*   = (TextView) item.findViewById();
        //设置字体
        ;*/
    }
}
