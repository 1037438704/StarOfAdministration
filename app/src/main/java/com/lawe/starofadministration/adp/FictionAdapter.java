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
import java.util.Map;

/**
 * author :  fuke
 * date : 2020/4/29 15:52
 * description : 公文拟制列表
 */
public class FictionAdapter extends BaseQuickAdapter<Map<String,String>, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public FictionAdapter(int layoutResId, @Nullable List<Map<String,String>> data) {
        super(layoutResId, data);
    }

    public FictionAdapter(int item_fiction) {
        super(item_fiction);
    }


    @Override
    protected void convert(BaseViewHolder holder, Map<String, String> itemData) {
        TextView itemFictionTitle = holder.itemView.findViewById(R.id.item_fiction_title);
        LinearLayout item_fiction_linear = holder.itemView.findViewById(R.id.item_fiction_linear);
        itemFictionTitle.setTypeface(getTextMedium);
        TextView item_fiction_title = holder.itemView.findViewById(R.id.item_fiction_title);
        TextView item_fiction_person = holder.itemView.findViewById(R.id.item_fiction_person);
        TextView item_fiction_state = holder.itemView.findViewById(R.id.item_fiction_state);
        TextView item_fiction_time = holder.itemView.findViewById(R.id.item_fiction_time);

        item_fiction_title.setText(itemData.get("docTitle"));
        item_fiction_person.setText(itemData.get("等.."));
        String state = itemData.get("state");
        String archivedState = itemData.get("archivedState");
        if (state.equals("-1")){
            item_fiction_state.setText("状态：驳回");
        }else if(state.equals("0")){
            item_fiction_state.setText("状态：草稿");
        }else if(state.equals("1")){
            item_fiction_state.setText("状态：审核中");
        }else if(state.equals("2") && archivedState.equals("0")){
            item_fiction_state.setText("状态：已完成");
        }else if(state.equals("2") && archivedState.equals("1")){
            item_fiction_state.setText("状态：已归档");
        }
        item_fiction_time.setText(itemData.get("createTime"));

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
