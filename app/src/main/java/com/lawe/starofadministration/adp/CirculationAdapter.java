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
import com.lawe.starofadministration.aty.EntryActivity;
import com.lawe.starofadministration.aty.EntryEntryActivity;
import com.lawe.starofadministration.aty.HandledActivity;
import com.lawe.starofadministration.aty.HandledLookActivity;
import com.lawe.starofadministration.aty.InstructionsActivity;
import com.lawe.starofadministration.aty.PropsedActivity;
import com.lawe.starofadministration.aty.SignActivity;
import com.lawe.starofadministration.aty.SponsorActivity;
import com.lawe.starofadministration.aty.UndertakeActivity;

import java.util.List;

/**
 * author :  fuke
 * date : 2020/6/9 15:52
 * description : 收文传阅列表
 */
public class CirculationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    private int flag = 7;

    public CirculationAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public CirculationAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView itemFictionTitle = helper.itemView.findViewById(R.id.item_fiction_title);
        LinearLayout item_circula_linear = helper.itemView.findViewById(R.id.item_circula_linear);
        itemFictionTitle.setTypeface(getTextMedium);

        item_circula_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*// 办理人查看：1
                    创建人查看：2
                    办理 ：3
                    协办：4
                    批示：5
                    承办：6
                    拟办：7
                    收文录入：8
                    文件签收：9*/

                if (flag == 1){
                    Intent intent = new Intent(getContext(), HandledLookActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 2){
                    Intent intent = new Intent(getContext(), EntryActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 3){
                    Intent intent = new Intent(getContext(), HandledActivity.class);
                    getContext().startActivity(intent);
                } else if(flag == 4){
                    Intent intent = new Intent(getContext(), SponsorActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 5){
                    Intent intent = new Intent(getContext(), InstructionsActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 6){
                    Intent intent = new Intent(getContext(), UndertakeActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 7){
                    Intent intent = new Intent(getContext(), PropsedActivity.class);
                    getContext().startActivity(intent);
                }else if(flag == 9){
                    Intent intent = new Intent(getContext(), SignActivity.class);
                    getContext().startActivity(intent);
                }

               /* Intent intent = new Intent(getContext(), DraftActivity.class);
                intent.putExtra("flagSpeed","2");
                getContext().startActivity(intent);*/
            }
        });
    }
}
