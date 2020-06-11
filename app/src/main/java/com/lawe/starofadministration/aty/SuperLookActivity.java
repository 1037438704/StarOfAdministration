package com.lawe.starofadministration.aty;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.DraftChatAdapter;
import com.lawe.starofadministration.adp.TemplateAdapter;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.ListChatBean;
import com.lawe.starofadministration.fgt.ducha_duban.SuperContextFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperEclosureFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperSettingFragment;
import com.lawe.starofadministration.fgt.ducha_duban.SuperSpeedFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeContextFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeEclosureFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeSettingFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeSpeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/26 14:14
 * description : 督察督办查看、编辑
 **/
@Layout(R.layout.activity_super_look)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class SuperLookActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    ViewPagerAdp viewPagerAdp;

    private int flag = 1; //更多展开隐藏标识
    private int chatflag = 1; //常用语展开隐藏标识

    private List<BaseFragment> fragemnts;
    private RadioButton rb;
    private  int pageCounte = 0;
    private String superFlag;
    private RadioButton draftSpeedOne;
    private Button bottomChat;
    private LinearLayout draftChat;
    private RecyclerView draftChatRecycle;
    private LinearLayout draftChatNew;
    private ImageView draftChatNewImg;
    private TextView draftChatNewText;
    private LinearLayout draftChatSet;
    private ImageView draftChatSetImg;
    private TextView draftChatSetText;
    private LinearLayout bottomGongneng;

    //空集合
    private List<ListChatBean> listchat;
    private DraftChatAdapter draftChatAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayout draftMore;
    private LinearLayout titleMore;
    private String typePerson;
    private RadioButton radioSetting;
    private EditText bottomWhrit;
    private LinearLayout bottomPerson;
    private LinearLayout bottomOne;
    private ImageView bottomImg2;
    private TextView bottomChooseper2;

    @Override
    public void initViews() {
        super.initViews();
        initView();
        if (superFlag.equals("1")){
            if (typePerson.equals("true")){
                titleText.setText("督察督办-查看任务");
                bottomGongneng.setVisibility(View.GONE);
                bottomWhrit.setHint("请输入文件摘要");
            }else{
                titleText.setText("督查事项任务办理");
                bottomOne.setVisibility(View.GONE);
                bottomChooseper2.setText("添加转发人");
                bottomWhrit.setHint("请输入验收备注");
            }
        }else{
            titleText.setText("创建督查任务");
            bottomGongneng.setVisibility(View.GONE);
            bottomWhrit.setHint("请输入文件摘要");

        }
        //常用语列表
        listchat = new ArrayList<>();
        layoutManager = new LinearLayoutManager(me);
        draftChatAdapter = new DraftChatAdapter(R.layout.item_draft_chat);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();
        if (superFlag.equals("2")){
            pageCounte = 0;
            draftSpeedOne.setVisibility(View.GONE);
            fragemnts.add(SuperContextFragment.newInstance());
            fragemnts.add(SuperEclosureFragment.newInstance());
            fragemnts.add(SuperSettingFragment.newInstance());
            rb = (RadioButton) mainRgp.getChildAt(pageCounte);
        }else{
            if (typePerson.equals("true")){
                pageCounte = 3;
                draftSpeedOne.setVisibility(View.VISIBLE);
                fragemnts.add(SuperContextFragment.newInstance());
                fragemnts.add(SuperEclosureFragment.newInstance());
                fragemnts.add(SuperSettingFragment.newInstance());
                fragemnts.add(SuperSpeedFragment.newInstance());
                rb = (RadioButton) mainRgp.getChildAt(pageCounte);
            }else{
                pageCounte = 2;
                draftSpeedOne.setVisibility(View.VISIBLE);
                mainRgp.removeView(radioSetting);
                fragemnts.add(SuperContextFragment.newInstance());
                fragemnts.add(SuperEclosureFragment.newInstance());
                fragemnts.add(SuperSpeedFragment.newInstance());
                rb = (RadioButton) mainRgp.getChildAt(pageCounte);
            }
        }
        rb.setChecked(true);
        //字体
        rb.setTypeface(getTextMedium);
        viewPagerAdp = new ViewPagerAdp(me.getSupportFragmentManager(), fragemnts);
        viewPagerData.setOffscreenPageLimit(fragemnts.size());
        viewPagerData.setAdapter(viewPagerAdp);
        viewPagerData.setCurrentItem(pageCounte);

        draftChatRecycle.setLayoutManager(layoutManager);
        draftChatRecycle.setAdapter(draftChatAdapter);
        for (int i = 0; i < 10; i++) {
            listchat.add(new ListChatBean(false, "第" + i + "条"));
        }
        //常用语列表
        draftChatAdapter.setNewData(listchat);
        draftChatAdapter.notifyDataSetChanged();

    }

    @Override
    public void setEvents() {
        //viewPager的滑动监听
        viewPagerData.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //获取当前位置的RadioButton
                RadioButton rb = (RadioButton) mainRgp.getChildAt(position);
                //设置为true
                rb.setChecked(true);
                rb.setTypeface(getTextMedium);
            }
        });
        //RadioGroup的事件监听
        mainRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < mainRgp.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) mainRgp.getChildAt(i);
                    if (rb.isChecked()) {
                        viewPagerData.setCurrentItem(i, false);
                        rb.setTypeface(getTextMedium);
                    } else {
                        rb.setTypeface(getTextRegular);
                    }
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

        //查看更多
        titleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    draftMore.setVisibility(View.VISIBLE);
                    flag = 2;
                } else if (flag == 2) {
                    draftMore.setVisibility(View.GONE);
                    flag = 1;
                }
            }
        });

        //新增常用语
        draftChatNew.setOnClickListener(new View.OnClickListener() {
            private int maxnum = 200;   //设置最大字数

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(me);
                View view = View.inflate(me, R.layout.pop_add_yongyu, null);
                builder.setView(view);
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                TextView popAddTitle = view.findViewById(R.id.pop_add_title);
                EditText popAddEdit = view.findViewById(R.id.pop_add_edit);
                TextView popAddNum = view.findViewById(R.id.pop_add_num);
                Button popAddCancle = view.findViewById(R.id.pop_add_cancle);
                Button popAddSure = view.findViewById(R.id.pop_add_sure);

                //设置字体
                popAddTitle.setTypeface(getTextMedium);
                popAddSure.setTypeface(getTextMedium);
                popAddNum.setTypeface(getTextNum);

                //控制字数
                //控制字数
                popAddEdit.addTextChangedListener(new TextWatcher() {

                    private CharSequence wordNum;//记录输入的字数
                    private int selectionStart;  //开始
                    private int selectionEnd;  //结束

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        wordNum = charSequence;//实时记录输入的字数

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        int number = maxnum - editable.length();
                        //TextView显示剩余字数
                        popAddNum.setText(200 - number + "/" + 200);  //输入字数
                        selectionStart = popAddEdit.getSelectionStart();
                        selectionEnd = popAddEdit.getSelectionEnd();
                        if (wordNum.length() > maxnum) {
                            //删除多余输入的字（不会显示出来）
                            editable.delete(selectionStart - 1, selectionEnd);
                            int tempSelection = selectionEnd;
                            popAddEdit.setText(editable);
                            popAddEdit.setSelection(tempSelection);//设置光标在最后
                        }
                    }
                });

                //取消和保存的点击
                popAddCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        //管理常用语
        draftChatSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = draftChatSetText.getText().toString();
                if (text.equals("管理")) {
                    draftChatNewText.setText("取消");
                    draftChatSetText.setText("保存");
                    draftChatSetText.setTextColor(ContextCompat.getColor(me, R.color.colorPrimaryDark));
                    draftChatNewImg.setVisibility(View.GONE);
                    draftChatSetImg.setVisibility(View.GONE);
                } else {
                    draftChatNewText.setText("新建");
                    draftChatSetText.setText("管理");
                    draftChatSetText.setTextColor(ContextCompat.getColor(me, R.color.textMedium));
                    draftChatNewImg.setVisibility(View.VISIBLE);
                    draftChatSetImg.setVisibility(View.VISIBLE);
                }

            }
        });

        //常用语
        bottomChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chatflag == 1) {
                    draftChat.setVisibility(View.VISIBLE);
                    bottomGongneng.setVisibility(View.GONE);
                    chatflag = 2;
                } else if (chatflag == 2) {
                    draftChat.setVisibility(View.GONE);
                    bottomGongneng.setVisibility(View.VISIBLE);
                    chatflag = 1;
                }
            }
        });

        //人员选择
        bottomPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChoosePersonActivity.class);
            }
        });

    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
        draftSpeedOne = findViewById(R.id.draft_speed_one);
        radioSetting = findViewById(R.id.radio_setting);

        //设置字体
        titleText.setTypeface(getTextBold);

        superFlag = getIntent().getExtras().getString("superFlag");
        typePerson = getIntent().getExtras().getString("typePerson");

        bottomChat = findViewById(R.id.bottom_chat);
        draftChat = findViewById(R.id.draft_chat);
        draftChatRecycle = findViewById(R.id.draft_chat_recycle);
        draftChatNew = findViewById(R.id.draft_chat_new);
        draftChatNewImg = findViewById(R.id.draft_chat_new_img);
        draftChatNewText = findViewById(R.id.draft_chat_new_text);
        draftChatSet = findViewById(R.id.draft_chat_set);
        draftChatSetImg = findViewById(R.id.draft_chat_set_img);
        draftChatSetText = findViewById(R.id.draft_chat_set_text);
        bottomGongneng = findViewById(R.id.bottom_gongneng);
        draftMore = findViewById(R.id.draft_more);
        titleMore = findViewById(R.id.title_more);
        bottomWhrit = findViewById(R.id.bottom_whrit);
        bottomPerson = findViewById(R.id.bottom_person);
        bottomOne = findViewById(R.id.bottom_one);
        bottomImg2 = findViewById(R.id.bottom_img2);
        bottomChooseper2 = findViewById(R.id.bottom_chooseper2);
    }
}
