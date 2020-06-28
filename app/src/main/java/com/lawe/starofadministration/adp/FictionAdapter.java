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
import com.lawe.starofadministration.bean.FictionListBean;

import java.util.List;
import java.util.Map;

/**
 * author :  fuke
 * date : 2020/4/29 15:52
 * description : 公文拟制列表
 */
public class FictionAdapter extends BaseQuickAdapter<FictionListBean.PageBean.ListBean, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    private String depUserId;
    private String personType;

    public void setDepUserId(String depUserId){
        this.depUserId = depUserId;
    }

    public FictionAdapter(int layoutResId,  List<FictionListBean.PageBean.ListBean> data) {
        super(layoutResId, data);
    }

    public FictionAdapter(int item_fiction) {
        super(item_fiction);
    }

    @Override
    protected void convert(BaseViewHolder holder, FictionListBean.PageBean.ListBean listBean) {

        TextView itemFictionTitle = holder.itemView.findViewById(R.id.item_fiction_title);
        LinearLayout item_fiction_linear = holder.itemView.findViewById(R.id.item_fiction_linear);
        itemFictionTitle.setTypeface(getTextMedium);
        TextView item_fiction_title = holder.itemView.findViewById(R.id.item_fiction_title);
        TextView item_fiction_person = holder.itemView.findViewById(R.id.item_fiction_person);
        TextView item_fiction_state = holder.itemView.findViewById(R.id.item_fiction_state);
        TextView item_fiction_time = holder.itemView.findViewById(R.id.item_fiction_time);
        //获取创建人的id
        String creatorId = listBean.getCreatorId();
        //判断比较两个id，是执行者还是创建者
        if(depUserId.equals(creatorId)){
            personType = "1";  //创建者
        }else{
            personType = "2";  //执行者
        }
        item_fiction_title.setText(listBean.getDocTitle());
        item_fiction_person.setText("起草人："+listBean.getDname());
        String state = listBean.getState();
        String archivedState = listBean.getArchivedState();
        if (archivedState==null){
            archivedState  = "3";
        }
        if (state == null){
            state = "10";
        }
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
        item_fiction_time.setText("起草日期："+listBean.getCreateTime());
        //哪个地方呀
        item_fiction_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DraftActivity.class);
                intent.putExtra("flagSpeed","2");
                intent.putExtra("yid",listBean.getId());
                intent.putExtra("personType",personType);
                getContext().startActivity(intent);
            }
        });
    }
}
