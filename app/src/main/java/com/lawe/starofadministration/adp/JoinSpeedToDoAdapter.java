package com.lawe.starofadministration.adp;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;
import com.lawe.starofadministration.bean.JoinSpeedToDoBean;
import com.lawe.starofadministration.utils.GlidUtils;

/**
 * author : fuke
 * date : 2020/5/11 15:02
 * description :  进度
 */
public class JoinSpeedToDoAdapter extends BaseQuickAdapter<JoinSpeedToDoBean.TaskMapBean.CreateFileBean, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public JoinSpeedToDoAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinSpeedToDoBean.TaskMapBean.CreateFileBean dataBean) {

        TextView liuchengTitle = helper.itemView.findViewById(R.id.liucheng_title);
        liuchengTitle.setTypeface(getTextMedium);
        ImageView srcBg = helper.itemView.findViewById(R.id.srcBg);
        ImageView liuchengHead = helper.itemView.findViewById(R.id.liucheng_head);
        TextView liuchengName = helper.itemView.findViewById(R.id.liucheng_name);
        TextView liuchengTime = helper.itemView.findViewById(R.id.liucheng_time);
        TextView liuchengContext = helper.itemView.findViewById(R.id.liucheng_context);
        TextView liucheng_yijiancuiban = helper.itemView.findViewById(R.id.liucheng_yijiancuiban);
        TextView liucheng_cuiban = helper.itemView.findViewById(R.id.liucheng_cuiban);

        liuchengContext.setText(dataBean.getMessage());
        srcBg.setBackgroundResource(R.mipmap.icon_will_slices);
       liuchengTitle.setText("等待处理");
       if (dataBean.getUserPhoto() == null){
           liuchengHead.setBackgroundResource(R.mipmap.me);
       }else{
           GlidUtils.defaultGlid2(getContext(),dataBean.getUserPhoto() ,liuchengHead);
       }
        liuchengName.setText(dataBean.getDepartmentName()+"-"+dataBean.getJobName()+"-"+dataBean.getDname());
        liuchengTime.setText(dataBean.getCreateTime());
    }
}
