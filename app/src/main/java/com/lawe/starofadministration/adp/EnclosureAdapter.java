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
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.EnclosureListBean;
import com.lawe.starofadministration.config.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : fuke
 * date : 2020/4/13 15:15
 * description :  附件列表
 */
public class EnclosureAdapter extends BaseQuickAdapter<EnclosureListBean.PageBean.ListBean, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    public Typeface getTextBold = MyApplication.getTextBold;
    private int size;
    private CustomPopWindow popWindow;

    public void setSize(int size) {
        this.size = size;
    }

    public EnclosureAdapter(int layoutResId, @Nullable List<EnclosureListBean.PageBean.ListBean> data) {
        super(layoutResId, data);
    }

    public EnclosureAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, EnclosureListBean.PageBean.ListBean item) {
        TextView item_num = helper.itemView.findViewById(R.id.enclosure_item_num);
        TextView item_title = helper.itemView.findViewById(R.id.enclosure_item_title);
        TextView item_time = helper.itemView.findViewById(R.id.enclosure_item_time);
        ImageView item_more = helper.itemView.findViewById(R.id.enclosure_item_more);
        TextView enclosure_item_name = helper.itemView.findViewById(R.id.enclosure_item_name);
        //设置字体
        item_num.setTypeface(getTextBold);
        item_title.setTypeface(getTextMedium);
        item_time.setTypeface(getTextBold);

        for (int i = 0; i < size; i++) {
            item_num.setText(i+1+"");
        }
        item_title.setText(item.getUploadifyTitle());
        item_time.setText(item.getUpdateTime());
        enclosure_item_name.setText(item.getUploadUserName());
        item_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depUserId = Preferences.getInstance().getString(getContext(),"login","depUserId");
                if (!depUserId.equals(item.getUploadUserId())){
                    Toast.makeText(getContext(),"此附件不是您上传，无权修改",Toast.LENGTH_SHORT).show();
                    return;
                }
                //显示PopupWindow
                View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_enclosure_quanxian,null);
                //处理popWindow 显示内容
                String id = item.getId();
                handleLogic(contentView,id);
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
    private void handleLogic(View contentView,String id){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depUserId = Preferences.getInstance().getString(getContext(),"login","depUserId");
                if(popWindow!=null){
                    popWindow.dissmiss();
                }
                switch (v.getId()){
                    case R.id.pop_fujian_quan:
                        Toast.makeText(getContext(),"参与人可见",Toast.LENGTH_SHORT).show();
                        //调用修改接口
                        updateFile("1",id);
                        break;
                    case R.id.pop_fujian_chuang:
                        Toast.makeText(getContext(),"仅创建者可见",Toast.LENGTH_SHORT).show();
                        //调用修改接口
                        updateFile("2",id);
                        break;
                    case R.id.pop_fujian_delete:
                        //删除附件接口
                        HttpRequest.GET(getContext(), Constants.DELETEFUJIAN, new Parameter().add("depUserId", depUserId).add("id", id), new ResponseListener() {
                            @Override
                            public void onResponse(String response, Exception error) {
                                if(error == null){
                                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_SHORT).show();
                                }else{
                                    error.getMessage();
                                }
                            }
                        });
                        break;
                }
            }
        };
        contentView.findViewById(R.id.pop_fujian_quan).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_fujian_chuang).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_fujian_delete).setOnClickListener(listener);
    }

    //修改
    private void updateFile(String state,String id) {
        JSONObject json = new JSONObject();
        String depUserId = Preferences.getInstance().getString(getContext(),"login","depUserId");
        try {
            json.put("id",id);
            json.put("depUserId",depUserId);
            json.put("state",state);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonUpdate = String.valueOf(json);
        HttpRequest.JSONPOST(getContext(), Constants.UPDATEFILE, jsonUpdate, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null){
                    Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                }else{
                    error.getMessage();
                }
            }
        });
    }


}
