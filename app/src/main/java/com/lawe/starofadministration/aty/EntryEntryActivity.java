package com.lawe.starofadministration.aty;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
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
import com.lawe.starofadministration.fgt.shouwen_chuanyue.EntryContextFragment;
import com.lawe.starofadministration.fgt.shouwen_chuanyue.EntryEclosureFragment;
import com.lawe.starofadministration.fgt.shouwen_chuanyue.EntrySetMessageFragment;
import com.lawe.starofadministration.fgt.shouwen_chuanyue.EntrySpeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/21 10:54
 * description : 收文传阅---收文录入
 **/
@Layout(R.layout.activity_entry_entry)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class EntryEntryActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleMore;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    private LinearLayout draftMore;
    private int flag = 1; //更多展开隐藏标识
    private int chatflag = 1; //常用语展开隐藏标识
    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;

    //空集合
    private List<String> list;
    private TemplateAdapter templateAdapter;
    private LinearLayout draftChat;
    private RecyclerView draftChatRecycle;
    private LinearLayout draftChatNew;
    private LinearLayout draftChatSet;
    private LinearLayout bottomPerson;
    private ImageView bottomPizhu;
    private EditText bottomWhrit;
    private Button bottomChat;
    private Button bottomButton;
    private TextView draftChatNewText;
    private TextView draftChatSetText;

    //空集合
    private List<ListChatBean> listchat;
    private DraftChatAdapter draftChatAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView draftChatNewImg;
    private ImageView draftChatSetImg;
    private RadioButton draftSpeedOne;
    private RadioButton rb;
    private LinearLayout bottomGongneng;
    private int flagSpeed;
    private ImageView bottomImg1;
    private TextView bottomChooseper1;
    private ImageView bottomImg2;
    private TextView bottomChooseper2;
    private LinearLayout bottomOne;
    int pageCounte = 0;

    @Override
    public void initViews() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleMore = findViewById(R.id.title_more);
        titleMore.setVisibility(View.VISIBLE);
        draftMore = findViewById(R.id.draft_more);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);

        bottomPerson = findViewById(R.id.bottom_person);
        bottomPizhu = findViewById(R.id.bottom_pizhu);
        bottomWhrit = findViewById(R.id.bottom_whrit);
        bottomChat = findViewById(R.id.bottom_chat);
        bottomButton = findViewById(R.id.bottom_button);
        bottomGongneng = findViewById(R.id.bottom_gongneng);
        bottomImg1 = findViewById(R.id.bottom_img1);
        bottomChooseper1 = findViewById(R.id.bottom_chooseper1);
        bottomImg2 = findViewById(R.id.bottom_img2);
        bottomChooseper2 = findViewById(R.id.bottom_chooseper2);
        bottomOne = findViewById(R.id.bottom_one);
        bottomOne.setVisibility(View.GONE);
        bottomChooseper2.setText("添加拟办人");

        draftChat = findViewById(R.id.draft_chat);
        draftChatRecycle = findViewById(R.id.draft_chat_recycle);
        draftChatNew = findViewById(R.id.draft_chat_new);
        draftChatSet = findViewById(R.id.draft_chat_set);
        draftChatNewText = findViewById(R.id.draft_chat_new_text);
        draftChatSetText = findViewById(R.id.draft_chat_set_text);


        //设置字体
        titleText.setText("收文录入");
        titleText.setTypeface(getTextBold);
        draftChatNewText.setTypeface(getTextMedium);
        draftChatSetText.setTypeface(getTextMedium);
        draftChatNewImg = findViewById(R.id.draft_chat_new_img);
        draftChatSetImg = findViewById(R.id.draft_chat_set_img);
        draftSpeedOne = findViewById(R.id.draft_speed_one);
        fragemnts = new ArrayList<>();
        //常用语列表
        listchat = new ArrayList<>();
        layoutManager = new LinearLayoutManager(me);
        draftChatAdapter = new DraftChatAdapter(R.layout.item_draft_chat);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts.add(EntryContextFragment.newInstance());
        fragemnts.add(EntryEclosureFragment.newInstance());
        fragemnts.add(EntrySetMessageFragment.newInstance());
        rb = (RadioButton) mainRgp.getChildAt(0);
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
}
