package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.ByTypeBean;
import com.lawe.starofadministration.bean.EventFactionBean;
import com.lawe.starofadministration.bean.ZhutiFindAllBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.PickerView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private TextView setmesTextTime;
    private TextView setmesTextDanwei;

    @Override
    public void initViews() {
        setmesType = (LinearLayout) findViewById(R.id.setmes_type);
        setmesTextType = (TextView) findViewById(R.id.setmes_text_type);
        setmesZhuti = (LinearLayout) findViewById(R.id.setmes_zhuti);
        setmesTextZhuti = (TextView) findViewById(R.id.setmes_text_zhuti);
        setmesTiaoJian = (LinearLayout) findViewById(R.id.setmes_tiaojian);
        setmesTextTiaojian = (TextView) findViewById(R.id.setmes_text_tiaojian);
        setmesTextDanwei = (TextView) findViewById(R.id.setmes_text_danwei);
        setmesHuiqian = (LinearLayout) findViewById(R.id.setmes_huiqian);
        setmesTime = (LinearLayout) findViewById(R.id.setmes_time);
        setmesTextTime = (TextView) findViewById(R.id.setmes_text_time);

        eventFactionBean = new EventFactionBean();
        eventFactionBean.type = 1;
        EventBus.getDefault().postSticky(eventFactionBean);

        String flagSpeed = Preferences.getInstance().getString(getActivity(), "doc", "flagSpeed");
        if (flagSpeed.equals("2")){
            String them = Preferences.getInstance().getString(getActivity(),"doc","them");
            String type = Preferences.getInstance().getString(getActivity(),"doc","type");
            String sendTime = Preferences.getInstance().getString(getActivity(),"doc","sendTime");
            String publicProperty = Preferences.getInstance().getString(getActivity(),"doc","publicProperty");
            setmesTextType.setText(type);
            setmesTextZhuti.setText(them);
            setmesTextTime.setText(sendTime);
            if (publicProperty.equals("0")){
                setmesTextTiaojian.setText("不公开");
            }else if (publicProperty.equals("1")){
                setmesTextTiaojian.setText("公开");
            }else if (publicProperty.equals("2")){
                setmesTextTiaojian.setText("依申请公开");
            }
        }
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
                eventFactionBean.setPartMent(setmesTextDanwei.getText().toString());
                EventBus.getDefault().postSticky(eventFactionBean);
                //jump(ChooseCompanyActivity.class);
            }
        });

        //定时发送
        setmesTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                choosePlot();
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
        HttpRequest.GET(me, Constants.BYTYPE, new Parameter().add("dataType",dataType), new ResponseListener() {
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

    //定时发送
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void choosePlot() {
        final Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        View views = View.inflate(getActivity(), R.layout.pop_community, null);
        dialog.setContentView(views);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout ll_select_one = views.findViewById(R.id.ll_select_one);
        LinearLayout ll_select_two = views.findViewById(R.id.ll_select_two);
        LinearLayout li_choose_plot = views.findViewById(R.id.li_choose_plot);
        LinearLayout li_choose_house = views.findViewById(R.id.li_choose_house);

        TextView tv_select_data = views.findViewById(R.id.tv_select_data);
        TextView tv_select_time = views.findViewById(R.id.tv_select_time);
        View v_line_one = views.findViewById(R.id.v_line_one);
        View v_line_two = views.findViewById(R.id.v_line_two);

        TimePicker mTimepicker = (TimePicker) views.findViewById(R.id.timepicker);
        DatePicker datePicker = views.findViewById(R.id.datePicker);
        TextView pop_finish = views.findViewById(R.id.pop_finish);
        mTimepicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);  //设置点击事件不弹键盘
        mTimepicker.setIs24HourView(true);   //设置时间显示为24小时

        mTimepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //获取当前选择的时间
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                tv_select_time.setText(hourOfDay+":"+minute);
            }
        });

        datePicker.init(2020, 10, 12, new DatePicker.OnDateChangedListener() {
            //当dp日期改变时回调onDateChanged方法
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //获取dp的年月日的值，在textView上显示出来
                tv_select_data.setText(+datePicker.getYear()+"-"+
                        (datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth());
            }
        });

        ll_select_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                li_choose_house.setVisibility(View.VISIBLE);
                li_choose_plot.setVisibility(View.GONE);

                tv_select_data.setTextColor(getResources().getColor(R.color.color_db322b));
                v_line_one.setVisibility(View.VISIBLE);
                tv_select_time.setTextColor(getResources().getColor(R.color.color_3E3E41));
                v_line_two.setVisibility(View.GONE);
            }
        });

        ll_select_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                li_choose_house.setVisibility(View.GONE);
                li_choose_plot.setVisibility(View.VISIBLE);

                tv_select_time.setTextColor(getResources().getColor(R.color.color_db322b));
                v_line_one.setVisibility(View.GONE);
                tv_select_data.setTextColor(getResources().getColor(R.color.color_3E3E41));
                v_line_two.setVisibility(View.VISIBLE);
            }
        });

        pop_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setmesTextTime.setText(datePicker.getYear()+"-"+
                        (datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+tv_select_time.getText().toString()+":00");
                eventFactionBean.setDocTime(setmesTextTime.getText().toString());
                EventBus.getDefault().postSticky(eventFactionBean);
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public static SetMessageFragment newInstance() {
        return new SetMessageFragment();
    }

}
