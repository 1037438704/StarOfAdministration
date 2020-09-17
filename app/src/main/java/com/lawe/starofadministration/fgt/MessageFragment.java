package com.lawe.starofadministration.fgt;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.MessageBean;
import com.lawe.starofadministration.config.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/4/8 10:23
 * description :待办——待办信息
 */
@Layout(R.layout.fgt_message)
public class MessageFragment extends BaseFgt implements CompoundButton.OnCheckedChangeListener{

    private TextView textCategory;
    private ImageView daibanDown1;
    private TextView textTimer;
    private ImageView daibanDown2;
    private TextView textUrgent;
    private ImageView daibanDown3;
    private LinearLayout textChoose;
    private RecyclerView recycleMessage;
    private LinearLayout linearLeibie;
    private LinearLayout linear_urgent;
    private LinearLayout linear_time;
    private LinearLayout choose_leibie;
    private int flag = 1; //类别默认标识
    private int flag_urgent = 1; //类别默认标识
    private int flag_time = 1; //类别默认标识
    private DrawerLayout drawerLayout;
    private Button drawer_quxiao;
    private MessageAdapter messageAdapter;
    private LinearLayout dealtLinerarTime;
    private LinearLayout dealtLinerarUrgent;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;
    private int limit = 10;
    private String category = null;
    private String warningState = null;
    private String orderType = null;
    private TextView typeLeibie;
    private TextView typeNizhi;
    private TextView typeDuban;
    private TextView typeChuanyue;
    private TextView typeJihua;
    private TextView typeGuidang;
    private TextView typeBaofa;
    private TextView typeMoren;
    private TextView typeJInji;
    private TextView typeNojinji;
    private TextView timeMoren;
    private TextView timeSheng;
    private TextView timeJiang;
    private Button drawBefore;
    private Button drawAfter;
    private CheckBox[] checkBoxestime = new CheckBox[4];
    private TextView fictionTimeStart;
    private TextView fictionTimeEnd;
    private String day;
    private String startTime;
    private String endTime;
    Calendar calendar= Calendar.getInstance(Locale.CHINA);
    private Button buttonSure;

    @Override
    public void initViews() {
        super.initViews();
        textCategory = (TextView) findViewById(R.id.text_category);
        daibanDown1 = (ImageView) findViewById(R.id.daiban_down1);
        textTimer = (TextView) findViewById(R.id.text_timer);
        daibanDown2 = (ImageView) findViewById(R.id.daiban_down2);
        textUrgent = (TextView) findViewById(R.id.text_urgent);
        daibanDown3 = (ImageView) findViewById(R.id.daiban_down3);
        recycleMessage = (RecyclerView) findViewById(R.id.recycle_message);
        choose_leibie = (LinearLayout) findViewById(R.id.choose_leibie);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        linear_urgent = (LinearLayout) findViewById(R.id.linear_urgent);
        linear_time = (LinearLayout) findViewById(R.id.linear_time);
        dealtLinerarTime = (LinearLayout) findViewById(R.id.dealt_linerar_time);
        dealtLinerarUrgent = (LinearLayout) findViewById(R.id.dealt_linerar_urgent);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);

        textChoose = (LinearLayout) findViewById(R.id.text_choose);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_shaixuan);
        buttonSure = getActivity().findViewById(R.id.buttonSure);
        drawer_quxiao = getActivity().findViewById(R.id.drawer_quxiao);
        drawBefore = getActivity().findViewById(R.id.drawBefore);
        drawAfter = getActivity().findViewById(R.id.drawAfter);

        //类别里面
        typeLeibie = (TextView) findViewById(R.id.typeLeibie);
        typeNizhi = (TextView) findViewById(R.id.typeNizhi);
        typeDuban = (TextView) findViewById(R.id.typeDuban);
        typeChuanyue = (TextView) findViewById(R.id.typeChuanyue);
        typeJihua = (TextView) findViewById(R.id.typeJihua);
        typeGuidang = (TextView) findViewById(R.id.typeGuidang);
        typeBaofa = (TextView) findViewById(R.id.typeBaofa);

        //紧急度
        typeMoren = (TextView) findViewById(R.id.typeMoren);
        typeJInji = (TextView) findViewById(R.id.typeJInji);
        typeNojinji = (TextView) findViewById(R.id.typeNojinji);

        //时间
        timeMoren = (TextView) findViewById(R.id.timeMoren);
        timeSheng = (TextView) findViewById(R.id.timeSheng);
        timeJiang = (TextView) findViewById(R.id.timeJiang);

        //待办信息
        recycleMessage.setLayoutManager(new LinearLayoutManager(me));
        messageAdapter = new MessageAdapter(R.layout.item_message_layout);
        recycleMessage.setAdapter(messageAdapter);

        checkBoxestime[0] = getActivity().findViewById(R.id.fiction_text_jin3);
        checkBoxestime[0] = getActivity().findViewById(R.id.fiction_text_jin3);
        checkBoxestime[1] = getActivity().findViewById(R.id.fiction_text_jin7);
        checkBoxestime[2] = getActivity().findViewById(R.id.fiction_text_jin30);
        checkBoxestime[3] = getActivity().findViewById(R.id.fiction_text_zidingyi);
        checkBoxestime[0].setOnCheckedChangeListener(this);
        checkBoxestime[1].setOnCheckedChangeListener(this);
        checkBoxestime[2].setOnCheckedChangeListener(this);
        checkBoxestime[3].setOnCheckedChangeListener(this);

        //加载首页数据
        messageData();
    }

    @Override
    public void initDatas() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                limit = 10;
                messageData();
            }
        });
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                messageData();
            }
        });
    }

    //查询所有数据
    private void messageData() {
        showPopDialog();
        JSONObject json = new JSONObject();
        try {
            json.put("page", page);
            json.put("limit", limit);
            json.put("depUserId",depUserId);
            json.put("warningState",warningState);  //紧急度

            json.put("order",orderType); //接收时间
            json.put("order","createTime");
            json.put("category",category);   //类别
            json.put("beAboutToOverdue",null);   //即将逾期靠前
            json.put("day",null);   //最近幾天
            json.put("startTime",null);   //开始时间
            json.put("endTime",null);   //结束时间
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.LISTFINDALLBYCURRENTUSER, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                MessageBean messageBean = gson.fromJson(response, MessageBean.class);
                List<MessageBean.PageBean.ListBean> list = messageBean.getPage().getList();
                if (page == 1){
                    messageAdapter.setNewData(list);
                    refreshLayout.finishRefresh();
                }else{
                    if(list == null || list.size() == 0){
                        toast("没有更多数据了...");
                        refreshLayout.finishLoadMore();
                        return;
                    }
                    messageAdapter.addData(list);
                }

            }
        });
    }

    @Override
    public void setEvents() {
        //类别选择
        choose_leibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_time.setVisibility(View.GONE);
                linear_urgent.setVisibility(View.GONE);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                flag_urgent = 1;
                if (flag == 1) {
                    linearLeibie.setVisibility(View.VISIBLE);
                    daibanDown1.setImageResource(R.mipmap.shaixuan_3);
                    flag = 2;
                } else {
                    linearLeibie.setVisibility(View.GONE);
                    daibanDown1.setImageResource(R.mipmap.daiban_down);
                    flag = 1;
                }
            }
        });

        //接收时间
        dealtLinerarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                linear_urgent.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                flag_urgent = 1;
                if (flag_time == 1) {
                    linear_time.setVisibility(View.VISIBLE);
                    daibanDown2.setImageResource(R.mipmap.shaixuan_3);
                    flag_time = 2;
                } else {
                    linear_time.setVisibility(View.GONE);
                    daibanDown2.setImageResource(R.mipmap.daiban_down);
                    flag_time = 1;
                }
            }
        });

        //紧急度
        dealtLinerarUrgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                linear_time.setVisibility(View.GONE);daibanDown2.setImageResource(R.mipmap.daiban_down);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                flag = 1;

                if (flag_urgent == 1) {
                    linear_urgent.setVisibility(View.VISIBLE);
                    daibanDown3.setImageResource(R.mipmap.shaixuan_3);
                    flag_urgent = 2;
                } else {
                    linear_urgent.setVisibility(View.GONE);
                    daibanDown3.setImageResource(R.mipmap.daiban_down);
                    flag_urgent = 1;
                }
            }
        });

        //类别
        typeLeibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = null;
                textCategory.setText(typeLeibie.getText().toString());
                messageData();
            }
        });
        //公文拟制
        typeNizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = "1";
                textCategory.setText(typeNizhi.getText().toString());
                messageData();
            }
        });
        //督察督办
        typeDuban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = "2";
                textCategory.setText(typeDuban.getText().toString());
                messageData();
            }
        });
        //收文传阅
        typeChuanyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = "3";
                textCategory.setText(typeChuanyue.getText().toString());
                messageData();
            }
        });
        //工作计划
        typeJihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = "5";
                textCategory.setText(typeJihua.getText().toString());
                messageData();
            }
        });
        //公文归档
        typeGuidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = "6";
                textCategory.setText(typeGuidang.getText().toString());
                messageData();
            }
        });
        //文件报发
        typeBaofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLeibie.setVisibility(View.GONE);
                daibanDown1.setImageResource(R.mipmap.daiban_down);
                flag = 1;
                page = 1;
                category = "7";
                textCategory.setText(typeBaofa.getText().toString());
                messageData();
            }
        });

        //紧急
        typeJInji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_urgent.setVisibility(View.GONE);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag_urgent = 1;
                page = 1;
                warningState = "1";
                textUrgent.setText(typeJInji.getText().toString());
                messageData();
            }
        });
        //非紧急
        typeNojinji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_urgent.setVisibility(View.GONE);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag_urgent = 1;
                page = 1;
                warningState = "0";
                textTimer.setText(typeNojinji.getText().toString());
                messageData();
            }
        });
        //默认
        typeMoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_urgent.setVisibility(View.GONE);
                daibanDown3.setImageResource(R.mipmap.daiban_down);
                flag_urgent = 1;
                page = 1;
                warningState = null;
                textUrgent.setText(typeMoren.getText().toString());
                messageData();
            }
        });

        //接收时间默认
        timeMoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_time.setVisibility(View.GONE);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                page = 1;
                orderType = null;
                textUrgent.setText(timeMoren.getText().toString());
                messageData();
            }
        });
        //升序
        timeSheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_time.setVisibility(View.GONE);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                page = 1;
                orderType = "asc";
                textTimer.setText(timeSheng.getText().toString());
                messageData();
            }
        });
        //降序
        timeJiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_time.setVisibility(View.GONE);
                daibanDown2.setImageResource(R.mipmap.daiban_down);
                flag_time = 1;
                page = 1;
                orderType = "desc";
                textTimer.setText(timeJiang.getText().toString());
                messageData();
            }
        });

        //聊天留言靠前
        drawBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawerLayout.closeDrawer(Gravity.RIGHT);
                String s = drawBefore.getText().toString();
            }
        });

        //预警信息靠前
        drawAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // drawerLayout.closeDrawer(Gravity.RIGHT);
                String s = drawAfter.getText().toString();
            }
        });

        buttonSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                messageData();
            }
        });

        //筛选弹出pop
        textChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        drawer_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    //筛选条件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //时间
        if (isChecked){
            for (int k = 0; k < checkBoxestime.length; k++) {
                //不等于当前选中的就变成false
                if (checkBoxestime[k].getText().toString().equals(buttonView.getText().toString())) {
                    checkBoxestime[k].setChecked(true);
                    if (buttonView.getText().toString().equals("最近3天")){
                        day = "3";
                    }else if(buttonView.getText().toString().equals("最近7天")){
                        day = "7";
                    }else if(buttonView.getText().toString().equals("最近30天")){
                        day = "30";
                    }
                } else {
                    checkBoxestime[k].setChecked(false);
                }
            }

            checkBoxestime[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                    View view = View.inflate(me, R.layout.pop_check_time, null);
                    dialog.setContentView(view);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    //设置对话框大小
                    window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    fictionTimeStart = view.findViewById(R.id.fiction_time_start);
                    fictionTimeEnd = view.findViewById(R.id.fiction_time_end);
                    Button sure = view.findViewById(R.id.pop_sure);

                    //时间选择器
                    fictionTimeStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showStartDatePickerDialog(me,  2, fictionTimeStart, calendar);
                        }
                    });

                    fictionTimeEnd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showEndDatePickerDialog(me,  2, fictionTimeEnd, calendar);
                        }
                    });

                    sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //调用接口查询
                            day = null;
                            startTime = fictionTimeStart.getText().toString();
                            endTime = fictionTimeEnd.getText().toString();
                            dialog.cancel();

                        }
                    });

                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }
            });
        }
    }

    // 日期选择
    public static void showStartDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void showEndDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

}
