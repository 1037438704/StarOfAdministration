package com.lawe.starofadministration.adp;

import android.widget.TextView;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.MessageBean;
import java.util.List;

/**
 * author :  fuke
 * date : 2020/4/13 15:15
 * description : 首页---待办
 */
public class MessageAdapter extends BaseQuickAdapter< MessageBean.PageBean.ListBean, BaseViewHolder> {

    public MessageAdapter(int layoutResId, List<MessageBean.PageBean.ListBean> data) {
        super(layoutResId, data);
    }

    public MessageAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.PageBean.ListBean listBeans) {
        helper.setText(R.id.message_title,listBeans.getDocTitle());
        helper.setText(R.id.number,"编号："+listBeans.getQuasiNumber());
        helper.setText(R.id.timer,listBeans.getCreateTime());
        helper.setText(R.id.banjieTimer,"要求办结："+listBeans.getDeadlineTime());
        helper.setText(R.id.shouTimer,"接收时间："+listBeans.getCreateTime());
        helper.setText(R.id.laiwenDanwei,"来文单位："+listBeans.getDocumentReportUnit());
        helper.setText(R.id.fujianNUmber,listBeans.getTotal());
        TextView background = helper.itemView.findViewById(R.id.background);
        /*String category = listBeans.getCategory();
        if (category.equals("1")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }else if (category.equals("2")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }else if (category.equals("3")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }else if (category.equals("5")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }else if (category.equals("6")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }else if (category.equals("7")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }else if (category.equals("8")&&category != null){
            background.setBackgroundResource(R.drawable.shape_radius100);
        }*/
    }
}
