package com.lawe.starofadministration.adp;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.zhouwei.library.CustomPopWindow;
import com.lawe.starofadministration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  fuke
 * date : 2020/5/8 15:15
 * description : 常用组
 */
public class LookGroupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private  int flagRecy = 1;
    private List<String> list;

    public LookGroupAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public LookGroupAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LinearLayout itemGroupLinear = helper.itemView.findViewById(R.id.item_group_linear);
        RecyclerView itemGroupRecycle = helper.itemView.findViewById(R.id.item_group_recycle);
        ImageView itemGroupImg = helper.itemView.findViewById(R.id.item_group_img);
        TextView itemGroupName = helper.itemView.findViewById(R.id.item_group_name);

        //分组下的人员信息
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),4);
        itemGroupRecycle.setLayoutManager(layoutManager);

        LookGroupPeopleAdapter lookGroupPeopleAdapter = new LookGroupPeopleAdapter(R.layout.item_group_people);
        itemGroupRecycle.setAdapter(lookGroupPeopleAdapter);

        list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        lookGroupPeopleAdapter.setNewData(list);
        lookGroupPeopleAdapter.notifyDataSetChanged();

        //显示隐藏联系人列表
        itemGroupLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagRecy == 1){
                    itemGroupRecycle.setVisibility(View.VISIBLE);
                    itemGroupImg.setImageResource(R.mipmap.icon_chang_yong_yi_xuan);
                    flagRecy = 2;
                }else if(flagRecy == 2){
                    itemGroupRecycle.setVisibility(View.GONE);
                    itemGroupImg.setImageResource(R.mipmap.icon_zhan_kai);
                    flagRecy = 1;
                }
            }
        });

        //长按常用组重命名
        itemGroupLinear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_group_update_name,null);
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(getContext())
                        .setView(contentView)//显示的布局，还可以通过设置一个View
                        //     .size(600,400) //设置显示的大小，不设置就默认包裹内容
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .create()//创建PopupWindow
                        .showAsDropDown(itemGroupName,0,10);//显示PopupWindow

                TextView popUpdateName = contentView.findViewById(R.id.pop_update_name);

                popUpdateName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //弹出更改名字的pop是dialog
                        Toast.makeText(getContext(),"弹出更改名字的pop是dialog",Toast.LENGTH_SHORT).show();
                    }
                });

                return false;
            }
        });
    }
}
