package com.lawe.starofadministration.adp;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ReceiptActivity;
import com.lawe.starofadministration.aty.SimulacrumActivity;

import java.util.List;

/**
 * author :
 * date : 2020/5/22 15:52
 * description :
 */
public class FileAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    private int flag = 1;

    public FileAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public FileAdapter(int item_message_layout) {
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
                //1:拟制文件归档    2：收文归档
                if (flag == 1){
                    Intent intent = new Intent(getContext(), SimulacrumActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 2){
                    Intent intent = new Intent(getContext(), ReceiptActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });
    }
}
