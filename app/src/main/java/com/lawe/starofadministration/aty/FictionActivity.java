package com.lawe.starofadministration.aty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.FictionAdapter;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.FictionListBean;
import com.lawe.starofadministration.bean.LoginDefaltBean;
import com.lawe.starofadministration.bean.MessageBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.AppManager;
import com.lawe.starofadministration.utils.map.JSONUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * author : fuke
 * date : 2020/4/29 15:04
 * description : 公文拟制列表
 **/
@Layout(R.layout.activity_fiction)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class FictionActivity extends BaseAty implements CompoundButton.OnCheckedChangeListener {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView factionRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private FictionAdapter fictionAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView factionTop;
    private DrawerLayout fictionDrawer;
    private TextView fictionTimeStart;
    private TextView fictionTimeEnd;
    private String startTime = null;
    private String endTime = null;
    private int page = 1;
    private int pages = 1;
    private int limit = 10;
    private SmartRefreshLayout refreshLayout;
    private Handler handler = new Handler();
    private String searchText = null;
    private String docType = null;
    private String day = null;
    private String archivedState = null;
    private String state = null;
    private CheckBox[] checkBoxes = new CheckBox[13];
    private CheckBox[] checkBoxesstate = new CheckBox[3];
    private CheckBox[] checkBoxestime = new CheckBox[4];
    private Button fictionButtonSure;
    private Button fictionButtonCancle;

    @Override
    public void initViews() {
        super.initViews();
        fictionDrawer = findViewById(R.id.fiction_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        factionRecycle = findViewById(R.id.faction_recycle);
        factionTop = findViewById(R.id.faction_top);
        refreshLayout = findViewById(R.id.refreshLayout);
        fictionButtonSure = findViewById(R.id.fiction_button_sure);
        fictionButtonCancle = findViewById(R.id.fiction_button_cancle);

        layoutManager = new LinearLayoutManager(me);
        factionRecycle.setLayoutManager(layoutManager);
        fictionAdapter = new FictionAdapter(R.layout.item_fiction);
        fictionAdapter.setDepUserId(depUserId);
        factionRecycle.setAdapter(fictionAdapter);

        //checkbox--id
        checkbox();
        //查询信息
        getMessage();

    }

    private void checkbox() {
        checkBoxes[0] = findViewById(R.id.fiction_text_yian);
        checkBoxes[1] = findViewById(R.id.fiction_text_mingling);
        checkBoxes[2] = findViewById(R.id.fiction_text_jueding);
        checkBoxes[3] = findViewById(R.id.fiction_text_zhibiao);
        checkBoxes[4] = findViewById(R.id.fiction_text_gonggao);
        checkBoxes[5] = findViewById(R.id.fiction_text_tonggao);
        checkBoxes[6] = findViewById(R.id.fiction_text_tongzhi);
        checkBoxes[7] = findViewById(R.id.fiction_text_tongbao);
        checkBoxes[8] = findViewById(R.id.fiction_text_baogao);
        checkBoxes[9] = findViewById(R.id.fiction_text_qingshi);
        checkBoxes[10] = findViewById(R.id.fiction_text_pifu);
        checkBoxes[11] = findViewById(R.id.fiction_text_han);
        checkBoxes[12] = findViewById(R.id.fiction_text_huiyijiyao);
        checkBoxes[0].setOnCheckedChangeListener(this);
        checkBoxes[1].setOnCheckedChangeListener(this);
        checkBoxes[2].setOnCheckedChangeListener(this);
        checkBoxes[3].setOnCheckedChangeListener(this);
        checkBoxes[4].setOnCheckedChangeListener(this);
        checkBoxes[5].setOnCheckedChangeListener(this);
        checkBoxes[6].setOnCheckedChangeListener(this);
        checkBoxes[7].setOnCheckedChangeListener(this);
        checkBoxes[8].setOnCheckedChangeListener(this);
        checkBoxes[9].setOnCheckedChangeListener(this);
        checkBoxes[10].setOnCheckedChangeListener(this);
        checkBoxes[11].setOnCheckedChangeListener(this);
        checkBoxes[12].setOnCheckedChangeListener(this);
        checkBoxesstate[0] = findViewById(R.id.fiction_text_chuli);
        checkBoxesstate[1] = findViewById(R.id.fiction_text_yiguidang);
        checkBoxesstate[2] = findViewById(R.id.fiction_text_caogao);
        checkBoxesstate[0].setOnCheckedChangeListener(this);
        checkBoxesstate[1].setOnCheckedChangeListener(this);
        checkBoxesstate[2].setOnCheckedChangeListener(this);
        checkBoxestime[0] = findViewById(R.id.fiction_text_jin3);
        checkBoxestime[1] = findViewById(R.id.fiction_text_jin7);
        checkBoxestime[2] = findViewById(R.id.fiction_text_jin30);
        checkBoxestime[3] = findViewById(R.id.fiction_text_zidingyi);
        checkBoxestime[0].setOnCheckedChangeListener(this);
        checkBoxestime[1].setOnCheckedChangeListener(this);
        checkBoxestime[2].setOnCheckedChangeListener(this);
        checkBoxestime[3].setOnCheckedChangeListener(this);
        //checkBoxestime[0] = findViewById(R.id.fiction_time_end);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        titleNew.setVisibility(View.VISIBLE);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("公文拟制系统");

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getMessage();
            }
        });
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getMessage();
            }
        });

        //时时获取搜索框内容
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(delayRun!=null){
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }
                searchText = s.toString();
                //调用接口查询
                getMessageNone();
                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRun, 800);
            }
        });
    }

    @Override
    public void setEvents() {
        factionRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    factionTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    factionTop.setVisibility(View.GONE);
                }

            }

        });

        //返回
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //新建
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建的时候创建uuid--即relationId
                UUID own= UUID.randomUUID();
                Log.e("uuid",own+"");
                //打印输出own=“81199e92-b564-4366-b72b-e20ce463a26d”；
                SharedPreferences nizhi_uuid = getSharedPreferences("nizhi_uuid", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = nizhi_uuid.edit();
                edit.putString("ni_relationId",own+"").commit();

                Intent intent = new Intent(FictionActivity.this, DraftActivity.class);
                intent.putExtra("flagSpeed","1");
                startActivity(intent);
                AppManager.getInstance().killActivity(FictionActivity.class);
            }
        });

        //回到第一条item
        factionTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factionRecycle.smoothScrollToPosition(0);
            }
        });

        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fictionDrawer.openDrawer(Gravity.RIGHT);
            }
        });

        //筛选确定
        fictionButtonSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fictionDrawer.closeDrawer(Gravity.RIGHT);
                //调用接口查询
                getMessageNone();
            }
        });

        //筛选取消
        fictionButtonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fictionDrawer.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    //列表数据全部数据
    private void getMessage() {
        JSONObject json = new JSONObject();
        try {
            json.put("page", page);
            json.put("limit", limit);
            json.put("depUserId",depUserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.LISTFINDBYCURRENTUSER, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                FictionListBean fictionListBean = gson.fromJson(response, FictionListBean.class);
                List<FictionListBean.PageBean.ListBean> list = fictionListBean.getPage().getList();
                if (page == 1){
                    fictionAdapter.setNewData(list);
                    refreshLayout.finishRefresh();
                }else{
                    if(list == null || list.size() == 0){
                        toast("没有更多数据了...");
                        refreshLayout.finishLoadMore();
                        return;
                    }
                    fictionAdapter.addData(list);
                    refreshLayout.finishLoadMore();
                }

            }
        });
    }

    //搜索筛选数据
    private void getMessageNone() {
        JSONObject json = new JSONObject();

        try {
            json.put("page", pages);
            json.put("limit", "100");
            json.put("depUserId",depUserId);
            json.put("docNumber",null);  //公文文号(搜索框)
            json.put("docType",docType);    //公文类型
            json.put("docTitle",searchText);   //公文标题(搜索框)
            json.put("startTime",startTime);   //开始时间
            json.put("endTime",endTime);   //结束时间
            json.put("day",day);  //最近几天
            json.put("archivedState",archivedState);   //归档状态
            json.put("state",state);   //状态
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.LISTFINDBYCURRENTUSER, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                FictionListBean fictionListBean = gson.fromJson(response, FictionListBean.class);
                List<FictionListBean.PageBean.ListBean> list = fictionListBean.getPage().getList();
                if (page == 1){
                    fictionAdapter.setNewData(list);
                    refreshLayout.finishRefresh();
                }else{
                    if(list == null || list.size() == 0){
                        toast("没有更多数据了...");
                        refreshLayout.finishLoadMore();
                        return;
                    }
                    fictionAdapter.addData(list);
                    refreshLayout.finishLoadMore();
                }

                searchText = null;
                docType = null;
                day = null;
                archivedState = null;
                state = null;
            }
        });
    }

    //延迟线程，看是否还有下一个字符输入
    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
        }
    };

    //筛选条件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //状态
        if (isChecked){
            for (int j = 0; j < checkBoxesstate.length; j++) {
                //不等于当前选中的就变成false
                if (checkBoxesstate[j].getText().toString().equals(buttonView.getText().toString())) {
                    checkBoxesstate[j].setChecked(true);
                    String s = buttonView.getText().toString();
                    if (s.equals("处理中")){
                        state = "1";
                    }else if(s.equals("草稿")){
                        state = "0";
                    }else if(s.equals("已归档")){
                        archivedState = "1";
                    }
                } else {
                    checkBoxesstate[j].setChecked(false);
                }
            }
        }
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
                    window.setWindowAnimations(R.style.main_menu_animStyle);
                    //设置对话框大小
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                            fictionDrawer.closeDrawer(Gravity.RIGHT);
                            getMessageNone();
                        }
                    });

                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }
            });
        }
        //类型
        if (isChecked){
            for (int i = 0; i < checkBoxes.length; i++) {
                //不等于当前选中的就变成false

                if (checkBoxes[i].getText().toString().equals(buttonView.getText().toString())) {
                    checkBoxes[i].setChecked(true);
                    docType = buttonView.getText().toString();
                } else {
                    checkBoxes[i].setChecked(false);
                }
            }
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

}
