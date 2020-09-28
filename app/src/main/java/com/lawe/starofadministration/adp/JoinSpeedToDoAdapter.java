package com.lawe.starofadministration.adp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;
import com.lawe.starofadministration.bean.JoinSpeedToDoBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.GlidUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author : fuke
 * date : 2020/5/11 15:02
 * description :  进度
 */
public class JoinSpeedToDoAdapter extends BaseQuickAdapter<JoinSpeedToDoBean.TaskMapBean.SumBean, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public JoinSpeedToDoAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinSpeedToDoBean.TaskMapBean.SumBean dataBean) {

        TextView liuchengTitle = helper.itemView.findViewById(R.id.liucheng_title);
        liuchengTitle.setTypeface(getTextMedium);
        ImageView srcBg = helper.itemView.findViewById(R.id.srcBg);
        ImageView liuchengHead = helper.itemView.findViewById(R.id.liucheng_head);
        TextView liuchengName = helper.itemView.findViewById(R.id.liucheng_name);
        TextView liuchengTime = helper.itemView.findViewById(R.id.liucheng_time);
        TextView liuchengContext = helper.itemView.findViewById(R.id.liucheng_context);
        TextView liucheng_yijiancuiban = helper.itemView.findViewById(R.id.liucheng_yijiancuiban);
        TextView liucheng_cuiban = helper.itemView.findViewById(R.id.liucheng_cuiban);

        liuchengContext.setText(dataBean.content);
        srcBg.setBackgroundResource(R.mipmap.icon_will_slices);
       liuchengTitle.setText("等待处理");
       if (dataBean.imgHead == null){
           liuchengHead.setBackgroundResource(R.mipmap.me);
       }else{
           GlidUtils.defaultGlid2(getContext(),dataBean.imgHead ,liuchengHead);
       }
        liuchengName.setText(dataBean.departName+"-"+dataBean.jobName+"-"+dataBean.name);
        //liuchengTime.setText(dataBean.);

        liucheng_cuiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences fictionIdSp = getContext().getSharedPreferences("fictionId", Context.MODE_PRIVATE);
                String fictionId = fictionIdSp.getString("fictionId", "");
                String depUserId = Preferences.getInstance().getString(getContext(),"login","depUserId");

                JSONObject json = new JSONObject();
                try {
                    json.put("businessKey",fictionId);
                    json.put("businessKeyType","1");
                    json.put("delayTime","");
                    json.put("id","");
                    json.put("messContent","");
                    json.put("messType","8");
                    json.put("messageCount","");
                    json.put("sendTime","");
                    json.put("sysUserId",depUserId);
                    json.put("title","");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json转化为string类型
                String jsonMess = String.valueOf(json);
                HttpRequest.JSONPOST(getContext(), Constants.ONECLICKREMINDER, jsonMess, new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error==null){
                            Toast.makeText(getContext(),"一键催办成功",Toast.LENGTH_SHORT).show();
                        }else{
                            error.getMessage();
                        }
                    }
                });
            }
        });
    }
}
