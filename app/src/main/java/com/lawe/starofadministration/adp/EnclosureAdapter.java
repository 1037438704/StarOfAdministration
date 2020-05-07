package com.lawe.starofadministration.adp;

import android.app.Dialog;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhouwei.library.CustomPopWindow;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author :
 * date : 2020/4/13 15:15
 * description :
 */
public class EnclosureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    public Typeface getTextBold = MyApplication.getTextBold;
    private int chatflag = 1;

    public EnclosureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public EnclosureAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView item_num = helper.itemView.findViewById(R.id.enclosure_item_num);
        TextView item_title = helper.itemView.findViewById(R.id.enclosure_item_title);
        TextView item_time = helper.itemView.findViewById(R.id.enclosure_item_time);
        ImageView item_more = helper.itemView.findViewById(R.id.enclosure_item_more);
        //设置字体
        item_num.setTypeface(getTextBold);
        item_title.setTypeface(getTextMedium);
        item_time.setTypeface(getTextBold);

        item_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                        .setView(R.layout.pop_enclosure_quanxian)//显示的布局，还可以通过设置一个View
                        //     .size(600,400) //设置显示的大小，不设置就默认包裹内容
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .create()//创建PopupWindow
                        .showAsDropDown(item_more,0,10);//显示PopupWindow
            }
        });

    }
}
