package com.lawe.starofadministration.adp;

import android.widget.TextView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/5/8 15:15
 * description : 常用组
 */
public class LookGroupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public LookGroupAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public LookGroupAdapter(int item_message_layout) {
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
