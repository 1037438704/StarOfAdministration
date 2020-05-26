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
import com.lawe.starofadministration.aty.NoticeLookActivity;
import com.lawe.starofadministration.aty.SuperLookActivity;

import java.util.List;

/**
 * author :
 * date : 2020/5/26 15:52
 * description : 督察督办
 */
public class SupervisionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public SupervisionAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public SupervisionAdapter(int item_message_layout) {
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
                Intent intent = new Intent(getContext(), SuperLookActivity.class);
                intent.putExtra("superFlag","1");
                intent.putExtra("typePerson","false");
                getContext().startActivity(intent);
            }
        });
    }
}
