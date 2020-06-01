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
import com.lawe.starofadministration.aty.ChatActivity;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/6/1 15:52
 * description : 我的群聊
 */
public class MyGroupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public MyGroupAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public MyGroupAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        /*TextView itemTitle = helper.itemView.findViewById(R.id.item_map_title);
        LinearLayout itemLinear = helper.itemView.findViewById(R.id.item_map);
        itemTitle.setTypeface(getTextMedium);*/
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
