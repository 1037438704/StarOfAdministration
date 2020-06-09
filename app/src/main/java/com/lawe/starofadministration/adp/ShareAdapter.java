package com.lawe.starofadministration.adp;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ShareLookActivity;

import java.util.List;

/**
 * author :  fuke
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
        LinearLayout item_file_linear = helper.itemView.findViewById(R.id.item_file_linear);
        itemFictionTitle.setTypeface(getTextMedium);

        item_file_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShareLookActivity.class);
                getContext().startActivity(intent);
            }
        });

    }
}
