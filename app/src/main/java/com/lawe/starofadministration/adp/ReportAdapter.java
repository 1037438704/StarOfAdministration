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
import com.lawe.starofadministration.aty.IssueActivity;

import java.util.List;

/**
 * author :
 * date : 2020/5/25 15:52
 * description : 文件报发
 */
public class ReportAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public ReportAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public ReportAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemFictionTitle = helper.itemView.findViewById(R.id.item_fiction_title);
        LinearLayout item_report_linear = helper.itemView.findViewById(R.id.item_report_linear);
        itemFictionTitle.setTypeface(getTextMedium);

        item_report_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), IssueActivity.class);
                intent.putExtra("flagSpeed","2");
                getContext().startActivity(intent);
            }
        });

    }
}
