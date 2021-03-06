package com.lawe.starofadministration.adp;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ChatActivity;

import java.util.List;

/**
 * author :
 * date : 2020/4/13 15:15
 * description :
 */
public class NoticeMessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public NoticeMessageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public NoticeMessageAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LinearLayout chat_linear = helper.itemView.findViewById(R.id.chat_linear);

        chat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
