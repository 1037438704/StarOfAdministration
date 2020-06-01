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
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeContextFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeEclosureFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeSettingFragment;
import com.lawe.starofadministration.fgt.gonggao_tongzhi.NoticeSpeedFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.DocumentEditFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.EnclosureCatalogFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.JoinSpeedFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.SetMessageFragment;
import com.lawe.starofadministration.fgt.gongxiang_wenjian.ShareEditFragment;
import com.lawe.starofadministration.fgt.gongxiang_wenjian.ShareEnclosureCatalogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/25 14:14
 * description : 公告通知查看、编辑
 **/
@Layout(R.layout.activity_notice_look)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class NoticeLookActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;

    private int flag = 1; //更多展开隐藏标识
    private int chatflag = 1; //常用语展开隐藏标识

    private RadioButton rb;
    private  int pageCounte = 0;
    private String noticeFlag;
    private RadioButton draftSpeedOne;
    private Button bottomChat;
    private LinearLayout draftChat;
    private RecyclerView draftChatRecycle;
    private LinearLayout draftChatNew;
    private ImageView draftChatNewImg;
    private TextView draftChatNewText;
    private android.widget.LinearLayout draftChatSet;
    private ImageView draftChatSetImg;
    private TextView draftChatSetText;
    private LinearLayout bottomGongneng;
    private LinearLayout bottomOne;
    private LinearLayout bottomPerson;
    private ImageView bottomImg2;
    private TextView bottomChooseper2;

    //空集合
    private List<ListChatBean> listchat;
    private DraftChatAdapter draftChatAdapter;
    private List<String> list;
    private TemplateAdapter templateAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayout draftMore;
    private LinearLayout draftMoreAI;
    private LinearLayout draftMoreTemplate;
    private LinearLayout titleMore;

    @Override
    public void initViews() {
        initView();

        if (noticeFlag.equals("1")){
            titleText.setText("创建人查看");
        }else{
            titleText.setText("发布公告");
        }
        //常用语列表
        listchat = new ArrayList<>();
        layoutManager = new LinearLayoutManager(me);
        draftChatAdapter = new DraftChatAdapter(R.layout.item_draft_chat);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts = new ArrayList<>();
        if (noticeFlag.equals("2")){
            pageCounte = 0;
            draftSpeedOne.setVisibility(View.GONE);
            fragemnts.add(NoticeContextFragment.newInstance());
            fragemnts.add(NoticeEclosureFragment.newInstance());
            fragemnts.add(NoticeSettingFragment.newInstance());
            rb = (RadioButton) mainRgp.getChildAt(pageCounte);
        }else{
            pageCounte = 3;
            draftSpeedOne.setVisibility(View.VISIBLE);
            fragemnts.add(NoticeContextFragment.newInstance());
            fragemnts.add(NoticeEclosureFragment.newInstance());
            fragemnts.add(NoticeSettingFragment.newInstance());
            fragemnts.add(NoticeSpeedFragment.newInstance());
            rb = (RadioButton) mainRgp.getChildAt(pageCounte);
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
        //模板列表
        draftMoreTemplate.setOnClickListener(new View.OnClickListener() {

            private RecyclerView popDraftRecycle;
            private ImageView loginDown;

            @Override
            public void onClick(View v) {
                draftMore.setVisibility(View.GONE);
                flag = 1;
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.pop_draft_muban, null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                loginDown = view.findViewById(R.id.login_down);
                popDraftRecycle = view.findViewById(R.id.pop_draft_recycle);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(me, 2);
                popDraftRecycle.setLayoutManager(gridLayoutManager);

                //列表
                list = new ArrayList<>();
                //待办信息
                templateAdapter = new TemplateAdapter(R.layout.item_pop_draft);
                popDraftRecycle.setAdapter(templateAdapter);

                for (int i = 0; i < 10; i++) {
                    list.add("" + i);
                }
                templateAdapter.setNewData(list);
                templateAdapter.notifyDataSetChanged();


                loginDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
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
                jump(ChoosePersonActivity.class, new JumpParameter()
                        .put("flagType", 1)
                );
            }
        });

    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
        draftSpeedOne = findViewById(R.id.draft_speed_one);

        //设置字体
        titleText.setTypeface(getTextBold);

        noticeFlag = getIntent().getExtras().getString("noticeFlag");
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
        bottomOne = findViewById(R.id.bottom_one);
        bottomOne.setVisibility(View.GONE);
        bottomPerson = findViewById(R.id.bottom_person);
        bottomImg2 = findViewById(R.id.bottom_img2);
        bottomChooseper2 = findViewById(R.id.bottom_chooseper2);
        draftMore = findViewById(R.id.draft_more);
        draftMoreAI = findViewById(R.id.draft_more_AI);
        draftMoreTemplate = findViewById(R.id.draft_more_template);
        titleMore = findViewById(R.id.title_more);
    }
}
