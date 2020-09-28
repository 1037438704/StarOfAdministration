package com.lawe.starofadministration.adp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;
import com.lawe.starofadministration.bean.JoinSpeedToDoBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.GlidUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 15:02
 * description :  进度
 */
public class JoinSpeedAdapter extends BaseQuickAdapter<JoinSpeedHistoryBean.HistoryTaskMapBean.SumBean, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    /*public JoinSpeedAdapter(int layoutResId, @Nullable List<JoinSpeedHistoryBean.HistoryTaskMapBean.SumBean> data) {
        super(layoutResId, data);
    }*/

    public JoinSpeedAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinSpeedHistoryBean.HistoryTaskMapBean.SumBean dataBean) {

        TextView liuchengTitle = helper.itemView.findViewById(R.id.liucheng_title);
        liuchengTitle.setTypeface(getTextMedium);
        ImageView liuchengHead = helper.itemView.findViewById(R.id.liucheng_head);
        TextView liuchengName = helper.itemView.findViewById(R.id.liucheng_name);
        TextView liuchengTime = helper.itemView.findViewById(R.id.liucheng_time);
        TextView liuchengContext = helper.itemView.findViewById(R.id.liucheng_context);
        TextView liucheng_yijiancuiban = helper.itemView.findViewById(R.id.liucheng_yijiancuiban);
        TextView liucheng_cuiban = helper.itemView.findViewById(R.id.liucheng_cuiban);
        LinearLayout linearChehui = helper.itemView.findViewById(R.id.linearChehui);

       liuchengTitle.setText(dataBean.typeTitle);
       if (dataBean.imgHead == null){
           liuchengHead.setBackgroundResource(R.mipmap.me);
       }else{
           GlidUtils.defaultGlid2(getContext(),dataBean.imgHead,liuchengHead);
       }
        liuchengName.setText(dataBean.departName+"-"+dataBean.jobName+"-"+dataBean.name);
        liuchengTime.setText(dataBean.time);
        if(TextUtils.isEmpty(dataBean.content)){
            liuchengContext.setText(" ");
        }else{
            liuchengContext.setText(dataBean.content);
        }

        SharedPreferences fictionIdSp = getContext().getSharedPreferences("fictionId", Context.MODE_PRIVATE);
        String personType = fictionIdSp.getString("personType", "");
        String fictionId = fictionIdSp.getString("fictionId", "");
        if (personType.equals("1")){  //创建人
            liucheng_yijiancuiban.setVisibility(View.VISIBLE);

        }else if(personType.equals("2")){  //执行者
            liucheng_yijiancuiban.setVisibility(View.GONE);
        }
        String names = Preferences.getInstance().getString(getContext(),"login","name");
        if(!TextUtils.isEmpty(names)){
            if (dataBean.name != null && dataBean.name.equals(names)){
                liucheng_cuiban.setText("撤回");
                liucheng_cuiban.setVisibility(View.VISIBLE);
            }else{
                //liucheng_cuiban.setVisibility(View.GONE);
                linearChehui.setVisibility(View.GONE);
            }
        }


        //创建人撤回
        liucheng_cuiban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest.POST(getContext(), Constants.TERMINATIONFILE+ fictionId, new Parameter(), new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null){
                            Toast.makeText(getContext(),"撤回成功",Toast.LENGTH_SHORT).show();
                        }else{
                            error.getMessage();
                        }
                    }
                });
            }
        });
        //一键催办
        liucheng_yijiancuiban.setOnClickListener(new View.OnClickListener() {
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
