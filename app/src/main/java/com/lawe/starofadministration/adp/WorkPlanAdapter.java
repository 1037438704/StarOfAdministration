package com.lawe.starofadministration.adp;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.LookWorkActivity;

import java.util.List;

/**
 * author :
 * date : 2020/5/28 15:52
 * description : 工作计划
 */
public class WorkPlanAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public WorkPlanAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public WorkPlanAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemTitle = helper.itemView.findViewById(R.id.item_title);
        LinearLayout itemLinear = helper.itemView.findViewById(R.id.item_linear);
        itemTitle.setTypeface(getTextMedium);

        itemLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LookWorkActivity.class);
                //1:创建者   2：执行者    true:项目    flase:任务
                intent.putExtra("personType","2");
                intent.putExtra("workType","true");
                getContext().startActivity(intent);
            }
        });
    }
}
