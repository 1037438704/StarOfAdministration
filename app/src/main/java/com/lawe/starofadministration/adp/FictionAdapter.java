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
import com.lawe.starofadministration.aty.DraftActivity;
import com.lawe.starofadministration.aty.FictionActivity;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/4/29 15:52
 * description : 公文拟制列表
 */
public class FictionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public FictionAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public FictionAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemFictionTitle = helper.itemView.findViewById(R.id.item_fiction_title);
        LinearLayout item_fiction_linear = helper.itemView.findViewById(R.id.item_fiction_linear);
        itemFictionTitle.setTypeface(getTextMedium);

        item_fiction_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DraftActivity.class);
                intent.putExtra("flagSpeed","2");
                getContext().startActivity(intent);
            }
        });
    }
}
