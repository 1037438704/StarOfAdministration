package com.lawe.starofadministration.adp;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.zhouwei.library.CustomPopWindow;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.map.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * author : fuke
 * date : 2020/4/13 15:15
 * description :  附件列表
 */
public class EnclosureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    public Typeface getTextBold = MyApplication.getTextBold;
    private int chatflag = 1;
    private CustomPopWindow popWindow;
    private String id;

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
                //显示PopupWindow
                View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_enclosure_quanxian,null);
                //处理popWindow 显示内容
                handleLogic(contentView);
                popWindow = new CustomPopWindow.PopupWindowBuilder(getContext())
                        .setView(contentView)//显示的布局，还可以通过设置一个View
                        //.size(600,400) //设置显示的大小，不设置就默认包裹内容
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .create()//创建PopupWindow
                        .showAsDropDown(item_more,0,10);
            }
        });

    }
    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popWindow!=null){
                    popWindow.dissmiss();
                }
                switch (v.getId()){
                    case R.id.pop_fujian_quan:
                        Toast.makeText(getContext(),"全体可见",Toast.LENGTH_SHORT).show();
                        //调用修改接口
                        updateFile();
                        break;
                    case R.id.pop_fujian_chuang:
                        Toast.makeText(getContext(),"仅创建者可见",Toast.LENGTH_SHORT).show();
                        //调用修改接口

                        break;
                    case R.id.pop_fujian_delete:
                        Toast.makeText(getContext(),"删除附件",Toast.LENGTH_SHORT).show();
                        //删除附件接口
                        HttpRequest.build(getContext(), Constants.DELETEFUJIAN + id)
                                .setResponseListener(new ResponseListener() {
                                    @Override
                                    public void onResponse(String response, Exception error) {
                                        if(error == null){
                                           //Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);

                                        }else{
                                            error.getMessage();
                                        }
                                    }
                                }).doGet();
                        //刷新
                        break;
                }
            }
        };
        contentView.findViewById(R.id.pop_fujian_quan).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_fujian_chuang).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_fujian_delete).setOnClickListener(listener);
    }

    private void updateFile() {
        JSONObject json = new JSONObject();
        try {
            json.put("id",id);
          /*  json.put("relationId", relationId);
            json.put("file",fileurl);
            json.put("fileName",fileName);
            json.put("depUserId",depUserId);
            json.put("state",1);
            json.put("departmentId", departmentId);
            json.put("departmentName",departFullName);
            json.put("depUserName",name);*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonUpdate = String.valueOf(json);
        HttpRequest.JSONPOST(getContext(), Constants.UPDATEFILE, jsonUpdate, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {

            }
        });
    }

}
