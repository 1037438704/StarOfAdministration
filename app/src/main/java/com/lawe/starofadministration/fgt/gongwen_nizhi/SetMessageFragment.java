package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ChooseCompanyActivity;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.ByTypeBean;
import com.lawe.starofadministration.bean.EventFactionBean;
import com.lawe.starofadministration.bean.LoginDefaltBean;
import com.lawe.starofadministration.bean.ZhutiFindAllBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.PickerView;
import com.lawe.starofadministration.utils.map.JSONUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author : fuke
 * date : 2020/4/30 16:40
 * description : 起草公文---设置信息
 **/
@Layout(R.layout.fgt_set_message)
public class SetMessageFragment extends BaseFgt {

    private LinearLayout setmesType;
    private TextView setmesTextType;
    private LinearLayout setmesZhuti;
    private LinearLayout setmesHuiqian;
    private LinearLayout setmesTime;
    private String textContext;
    private String publicProperty = "";

    List<String> data_type = new ArrayList<String>();
    List<String> data_zhuti = new ArrayList<String>();
    List<String> data_tiaojian = new ArrayList<String>();
    private String jsonType;
    private String dataType = "";
    private TextView setmesTextZhuti;
    private LinearLayout setmesTiaoJian;
    private TextView setmesTextTiaojian;
    private SharedPreferences.Editor edit;
    private PickerView minute_pv;
    private SharedPreferences sp;
    private EventFactionBean eventFactionBean;

    @Override
    public void initViews() {
        setmesType = (LinearLayout) findViewById(R.id.setmes_type);
        setmesTextType = (TextView) findViewById(R.id.setmes_text_type);
        setmesZhuti = (LinearLayout) findViewById(R.id.setmes_zhuti);
        setmesTextZhuti = (TextView) findViewById(R.id.setmes_text_zhuti);
        setmesTiaoJian = (LinearLayout) findViewById(R.id.setmes_tiaojian);
        setmesTextTiaojian = (TextView) findViewById(R.id.setmes_text_tiaojian);
        setmesHuiqian = (LinearLayout) findViewById(R.id.setmes_huiqian);
        setmesTime = (LinearLayout) findViewById(R.id.setmes_time);

        eventFactionBean = new EventFactionBean();
        eventFactionBean.type = 1;
        EventBus.getDefault().postSticky(eventFactionBean);

        sp = getActivity().getSharedPreferences("newFile",Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

        //公文类型
        setmesType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataType = "doc_type";
                getDocuType();
            }
        });

        //公文主题
        setmesZhuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDocuZhuti();
            }
        });

        //共享条件
        setmesTiaoJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
                View view = View.inflate(getContext(),R.layout.pop_document_type,null);
                dialog.setContentView(view);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.main_menu_animStyle);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                minute_pv = view.findViewById(R.id.minute_pv);
                TextView popFinish = view.findViewById(R.id.pop_finish);
                TextView popCancle = view.findViewById(R.id.pop_cancle);
                TextView popDocType = view.findViewById(R.id.pop_doc_type);
                popDocType.setText("请选择共享条件");
                String one = "公开";
                String two = "不公开";
                String three = "依申请公开";

                data_tiaojian.add(one);
                data_tiaojian.add(two);
                data_tiaojian.add(three);

                minute_pv.setData(data_tiaojian);
                minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
                    @Override
                    public void onSelect(String text) {
                        setmesTextTiaojian.setText(text);
                        if (text.equals("不公开")){
                            publicProperty = "0";
                        }else if(text.equals("公开")){
                            publicProperty = "1";
                        }else if(text.equals("依申请公开")){
                            publicProperty = "2";
                        }
                        eventFactionBean.setPublicProperty(publicProperty);
                        EventBus.getDefault().postSticky(eventFactionBean);
                    }
                });
                popFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.cancel();
                    }
                });
                popCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });

        //会签单位
        setmesHuiqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChooseCompanyActivity.class);
            }
        });

        //定时发送
        setmesTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //公文主题
    private void getDocuZhuti() {
        final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
        View view = View.inflate(getContext(),R.layout.pop_document_type,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        minute_pv = view.findViewById(R.id.minute_pv);
        TextView popFinish = view.findViewById(R.id.pop_finish);
        TextView popCancle = view.findViewById(R.id.pop_cancle);
        TextView popDocType = view.findViewById(R.id.pop_doc_type);
        popDocType.setText("请选择公文主题");

        JSONObject json = new JSONObject();
        try {
            json.put("parentId", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        jsonType = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.LISTFINDALL, jsonType, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                ZhutiFindAllBean zhutiFindAllBean = gson.fromJson(response, ZhutiFindAllBean.class);
                for (int i = 0; i < zhutiFindAllBean.getList().size(); i++) {
                    String themeName = zhutiFindAllBean.getList().get(i).getThemeName();
                    data_zhuti.add(themeName);
                }
                minute_pv.setData(data_zhuti);
                minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
                    @Override
                    public void onSelect(String text) {
                        setmesTextZhuti.setText(text);
                        //edit.putString("docTheme",text).commit();
                        eventFactionBean.setDocTheme(text);
                        EventBus.getDefault().postSticky(eventFactionBean);
                    }
                });
                popFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //setmesTextZhuti.setText(textContext);
                        dialog.cancel();
                    }
                });
                popCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
    }

    //公文类型
    private void getDocuType() {

        final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
        View view = View.inflate(getContext(),R.layout.pop_document_type,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        minute_pv = view.findViewById(R.id.minute_pv);
        TextView popFinish = view.findViewById(R.id.pop_finish);
        TextView popCancle = view.findViewById(R.id.pop_cancle);
        TextView popDocType = view.findViewById(R.id.pop_doc_type);
        popDocType.setText("请选择公文类型");

        JSONObject json = new JSONObject();
        try {
            json.put("dataType", dataType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        jsonType = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.BYTYPE, jsonType, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                ByTypeBean byTypeBean = gson.fromJson(response, ByTypeBean.class);
                for (int i = 0; i < byTypeBean.getDataDictList().size(); i++) {
                    String dataKey = byTypeBean.getDataDictList().get(i).getDataKey();
                    data_type.add(dataKey);
                }
                minute_pv.setData(data_type);
                minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
                    @Override
                    public void onSelect(String text) {
                        setmesTextType.setText(text);
                        //edit.putString("docType",text).commit();
                        eventFactionBean.setDocType(text);
                        EventBus.getDefault().postSticky(eventFactionBean);
                    }
                });
                popFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.cancel();
                    }
                });
                popCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
    }

    public static SetMessageFragment newInstance() {
        return new SetMessageFragment();
    }

}
