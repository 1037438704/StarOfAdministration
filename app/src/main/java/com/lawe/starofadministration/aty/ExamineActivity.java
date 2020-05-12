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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.fgt.ExamContextFragment;
import com.lawe.starofadministration.fgt.ExamEclosureFragment;
import com.lawe.starofadministration.fgt.ExamSpeedFragment;
import com.lawe.starofadministration.fgt.JoinContextFragment;
import com.lawe.starofadministration.fgt.JoinEclosureFragment;
import com.lawe.starofadministration.fgt.JoinSpeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 16:02
 * description : 公文审核
 **/
@Layout(R.layout.activity_examine)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ExamineActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    private ImageView bottomPerson;
    private ImageView bottomPizhu;
    private EditText bottomWhrit;
    private ImageView bottomChat;
    private Button bottomButton;
    private LinearLayout draftChat;
    private RecyclerView draftChatRecycle;
    private LinearLayout draftChatNew;
    private ImageView draftChatNewImg;
    private TextView draftChatNewText;
    private LinearLayout draftChatSet;
    private ImageView draftChatSetImg;
    private TextView draftChatSetText;

    private List<BaseFragment> fragemnts;
    private ViewPagerAdp viewPagerAdp;
    private int chatflag = 1; //常用语展开隐藏标识

    @Override
    public void initViews() {
        initView();

        fragemnts = new ArrayList<>();
        RadioButton rb = (RadioButton) mainRgp.getChildAt(0);
        rb.setChecked(true);
        rb.setTypeface(getTextMedium);

    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fragemnts.add(ExamContextFragment.newInstance());
        fragemnts.add(ExamEclosureFragment.newInstance());
        fragemnts.add(ExamSpeedFragment.newInstance());

        viewPagerAdp = new ViewPagerAdp(me.getSupportFragmentManager(), fragemnts);
        viewPagerData.setOffscreenPageLimit(fragemnts.size());
        viewPagerData.setAdapter(viewPagerAdp);
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
                    chatflag = 2;
                } else if (chatflag == 2) {
                    draftChat.setVisibility(View.GONE);
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

        //暂定跳往审阅页面
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ReviewActivity.class);
            }
        });
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);
        //下输入键盘常用语的部分
        bottomPerson = findViewById(R.id.bottom_person);
        bottomPizhu = findViewById(R.id.bottom_pizhu);
        bottomWhrit = findViewById(R.id.bottom_whrit);
        bottomChat = findViewById(R.id.bottom_chat);
        bottomButton = findViewById(R.id.bottom_button);
        draftChat = findViewById(R.id.draft_chat);
        draftChatRecycle = findViewById(R.id.draft_chat_recycle);
        draftChatNew = findViewById(R.id.draft_chat_new);
        draftChatNewImg = findViewById(R.id.draft_chat_new_img);
        draftChatNewText = findViewById(R.id.draft_chat_new_text);
        draftChatSet = findViewById(R.id.draft_chat_set);
        draftChatSetImg = findViewById(R.id.draft_chat_set_img);
        draftChatSetText = findViewById(R.id.draft_chat_set_text);

        titleText.setText("公文审核");
        titleText.setTypeface(getTextBold);
    }
}
