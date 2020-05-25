package com.lawe.starofadministration.adp;

import android.content.Context;
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
import com.lawe.starofadministration.aty.NoticeLookActivity;

import java.util.List;

/**
 * author :
 * date : 2020/5/25 15:52
 * description : 公告通知
 */
public class NoticeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public NoticeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public NoticeAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemFictionTitle = helper.itemView.findViewById(R.id.item_fiction_title);
        LinearLayout itemFileLinear = helper.itemView.findViewById(R.id.item_file_linear);
        itemFictionTitle.setTypeface(getTextMedium);

        itemFileLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeLookActivity.class);
                intent.putExtra("noticeFlag","1");
                getContext().startActivity(intent);
            }
        });
    }
}
