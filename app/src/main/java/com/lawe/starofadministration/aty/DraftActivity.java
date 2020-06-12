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
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.DraftChatAdapter;
import com.lawe.starofadministration.adp.TemplateAdapter;
import com.lawe.starofadministration.adp.ViewPagerAdp;
import com.lawe.starofadministration.base.BaseAty;
import com.lawe.starofadministration.bean.ListChatBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.fgt.gongwen_nizhi.DocumentEditFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.EnclosureCatalogFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.JoinSpeedFragment;
import com.lawe.starofadministration.fgt.gongwen_nizhi.SetMessageFragment;
import com.lawe.starofadministration.utils.map.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author : fuke
 * date : 2020/4/30 15:54
 * description : 公文起草
 **/
@Layout(R.layout.activity_draft)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class DraftActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private RadioGroup mainRgp;
    private ViewPager viewPagerData;
    private LinearLayout titleMore;
    private int flag = 1; //更多展开隐藏标识
    private int chatflag = 1; //常用语展开隐藏标识
    ViewPagerAdp viewPagerAdp;
    private List<BaseFragment> fragemnts;
    private LinearLayout draftMore;
    private LinearLayout draftAll;
    private LinearLayout draftMoreAI;
    private LinearLayout draftMoreDaoru;
    private LinearLayout draftMoreSave;
    private LinearLayout draftMoreTemplate;
    private LinearLayout draftMoreGlossary;

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
    private String jsons;
    private LinearLayout bottomGongneng;
    private String flagSpeed;

    @Override
    public void initViews() {
        super.initViews();
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        titleMore = findViewById(R.id.title_more);
        mainRgp = findViewById(R.id.main_rgp);
        viewPagerData = findViewById(R.id.viewPagerData);

        draftAll = findViewById(R.id.draft_all);
        draftMore = findViewById(R.id.draft_more);
        draftMoreAI = findViewById(R.id.draft_more_AI);
        draftMoreDaoru = findViewById(R.id.draft_more_daoru);
        draftMoreSave = findViewById(R.id.draft_more_save);
        draftMoreTemplate = findViewById(R.id.draft_more_template);
        draftMoreGlossary = findViewById(R.id.draft_more_glossary);

        bottomPerson = findViewById(R.id.bottom_person);
        bottomPizhu = findViewById(R.id.bottom_pizhu);
        bottomWhrit = findViewById(R.id.bottom_whrit);
        bottomChat = findViewById(R.id.bottom_chat);
        bottomButton = findViewById(R.id.bottom_button);
        bottomGongneng = findViewById(R.id.bottom_gongneng);
        LinearLayout bottomOne = findViewById(R.id.bottom_one);

        draftChat = findViewById(R.id.draft_chat);
        draftChatRecycle = findViewById(R.id.draft_chat_recycle);
        draftChatNew = findViewById(R.id.draft_chat_new);
        draftChatSet = findViewById(R.id.draft_chat_set);
        draftChatNewText = findViewById(R.id.draft_chat_new_text);
        draftChatSetText = findViewById(R.id.draft_chat_set_text);

        //获取上一个页面传递的标识、
        flagSpeed = getIntent().getExtras().getString("flagSpeed");
        if (flagSpeed.equals("1")){
            //设置字体
            titleText.setText("起草公文");
            bottomOne.setVisibility(View.GONE);

        }else if(flagSpeed.equals("2")){
            //设置字体
            titleText.setText("创建人查看");
            draftChat.setClickable(false);
            bottomPizhu.setClickable(false);
            bottomGongneng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toast("禁止修改");
                }
            });
        }

        titleText.setTypeface(getTextBold);
        titleMore.setVisibility(View.VISIBLE);
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
    int pageCounte = 0;
    @Override
    public void initDatas(JumpParameter parameter) {

        if (flagSpeed.equals("1")){
            pageCounte = 0;
            draftSpeedOne.setVisibility(View.GONE);
            fragemnts.add(DocumentEditFragment.newInstance());
            fragemnts.add(EnclosureCatalogFragment.newInstance());
            fragemnts.add(SetMessageFragment.newInstance());
            rb = (RadioButton) mainRgp.getChildAt(pageCounte);
        }else{
            pageCounte = 3;
            draftSpeedOne.setVisibility(View.VISIBLE);
            fragemnts.add(DocumentEditFragment.newInstance());
            fragemnts.add(EnclosureCatalogFragment.newInstance());
            fragemnts.add(SetMessageFragment.newInstance());
            fragemnts.add(JoinSpeedFragment.newInstance());
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


        //点击全屏任意地方弹框消失
        draftAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 2) {
                    draftMore.setVisibility(View.GONE);
                    flag = 1;
                }
                if (chatflag == 2) {
                    draftChat.setVisibility(View.GONE);
                    chatflag = 1;
                }
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

                wordMuban();
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

        //保存
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用保存
                fileSave();
            }
        });
    }

    private void fileSave() {
        JSONObject json = new JSONObject();
        try {
            json.put("depUserId",depUserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.DOCUMENT_QUERYPAGE, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {

            }
        });
    }

    //改变状态
    private  void listItemstate(){
        List<ListChatBean> list =  draftChatAdapter.getData();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDisplay(!list.get(i).getDisplay());
        }
        draftChatAdapter.notifyDataSetChanged();
    }

    // 调用模板接口
    private void wordMuban() {


    }
}
