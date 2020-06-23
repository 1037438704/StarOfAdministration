package com.lawe.starofadministration.aty;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import android.widget.Toast;

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
import com.lawe.starofadministration.MainActivity;
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
import com.lawe.starofadministration.utils.AppManager;
import com.lawe.starofadministration.utils.map.JSONUtils;
import com.lawe.starofadministration.view.tuya.WriteDialogListener;
import com.lawe.starofadministration.view.tuya.WritePadDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private Bitmap mSignBitmap;
    private ImageView mIVSign;
    private String remark;
    private String quasiNumber;
    private String  qNumber;
    private String docTitle;
    private String docType;
    private String publicProperty;
    private String docTheme;
    private String relationId;
    private String assigneeList = "1245548306401255426";
    private LinearLayout draftMoreChangeType;
    private int yourChoice;
    private String changeType;
    private String docContext;
    private Handler handler = new Handler();

    @Override
    public void initViews() {
        super.initViews();
        Log.e("assigneeList",assigneeList+"");
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
        draftMoreChangeType = findViewById(R.id.draft_more_changeType);

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

        //时时获取备注内容
        bottomWhrit.addTextChangedListener(new TextWatcher() {
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
                remark = s.toString();
                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRun, 800);
            }
        });
        //获取relationId
        SharedPreferences nizhi_uuid = getSharedPreferences("nizhi_uuid", Context.MODE_PRIVATE);
        relationId = nizhi_uuid.getString("ni_relationId","");
        getZhi();  //方法：获取全部值

        //常用语列表
        draftChatRecycle.setLayoutManager(layoutManager);
        draftChatRecycle.setAdapter(draftChatAdapter);
        for (int i = 0; i < 10; i++) {
            listchat.add(new ListChatBean(false, "第" + i + "条"));
        }
        draftChatAdapter.setNewData(listchat);
        draftChatAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getZhi();
    }

    /**
     * * 延迟线程，看是否还有下一个字符输入
     */

    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
        }
    };

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
                SharedPreferences newFile = getSharedPreferences("newFile", Context.MODE_PRIVATE);
                newFile.edit().clear().commit();
                finish();
                newFile.edit().clear().commit();
            }
        });

        //点击全屏任意地方弹框消失
        draftAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取存进去的值
                getZhi();
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
                getTime();
                if (assigneeList.equals("")){
                    toast("请选择审核人");
                }else{
                    fileSave();
                    draftMore.setVisibility(View.GONE);
                    flag = 1;
                    jump(FictionActivity.class);
                    AppManager.getInstance().killActivity(DraftActivity.class);
                }
            }
        });

        //保存草稿箱
        draftMoreSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDrafts();
                draftMore.setVisibility(View.GONE);
                flag = 1;
                jump(FictionActivity.class);
                finish();
            }
        });

        //手写批注
        bottomPizhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDocument();
            }
        });

        //变更文档类型
        draftMoreChangeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draftMore.setVisibility(View.GONE);
                flag = 1;
                showSingleChoiceDialog();
            }
        });
    }

    //获取值
    private void getZhi() {
        //获取所有需要的值
        SharedPreferences newFile = getSharedPreferences("newFile", Context.MODE_PRIVATE);
        quasiNumber = newFile.getString("quasiNumber", "");
        qNumber = newFile.getString("qNumber", "");
        docTitle = newFile.getString("docTitle", "");
        docType = newFile.getString("docType", "");
        publicProperty = newFile.getString("publicProperty", "");
        docTheme = newFile.getString("docTheme", "");
        //公文主体
        docContext = newFile.getString("docContext", "");
        if (docTitle.equals("") || docContext.equals("") || docTheme.equals("") || docType.equals("") || publicProperty.equals("")){
            bottomButton.setBackgroundResource(R.drawable.shape_red42_22);
        }else{
            bottomButton.setBackgroundResource(R.drawable.shape_red22);
        }
    }

    //选择文件类型
    private void showSingleChoiceDialog(){
        final String[] items = { "pdf","word","图片" };
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(DraftActivity.this);
        singleChoiceDialog.setTitle("请选择文档类型");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            if (items[yourChoice].equals("pdf")){
                                changeType = "1";
                            }else if(items[yourChoice].equals("word")){
                                changeType = "2";
                            }else if(items[yourChoice].equals("图片")){
                                changeType = "3";
                            }
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    //保存草稿箱
    private void saveDrafts() {
        JSONObject json = new JSONObject();
        try {
            json.put("depUserId",depUserId);  //用户ID
            json.put("departmentId",departmentId);  //部门id
            json.put("quasiNumber",quasiNumber);  //公文拟编号
            json.put("qNumber",qNumber);  //拟编号的数字，例：001
            json.put("docTitle",docTitle);  //公文标题
            json.put("changeType","2");  //1.pdf 2. Word 3.图片
            json.put("docType",docType);   //公文类型 调查询字典表接口参数：doc_type
            json.put("publicProperty",publicProperty);  //公开属性：0 不公开 1主动公开 2依申请公开
            json.put("docTheme",docTheme);  //公文主题 查询公文主题接口（查询所有父id为空公文主题）
            json.put("relationId",relationId);  //关联附件表relation_id
            json.put("timingSendTime","2020-6-24 10:10:35");  //定时发送时间
            json.put("signUnit",null);  //会签单位
            json.put("docNumber","");  //公文文号（核发传值）
            json.put("number","");  //公文文号的数字号（核发传值）
            json.put("modelId","");  //模板ID
            json.put("remark",remark);  //备注
            json.put("sendTime",time);  //发送时间
            json.put("filePath","");  //文件存放地址
            json.put("registerId","");  //报发人id（核发传值）
            json.put("archivePeopleId","");  //归档人id（核发传值）
            json.put("archivePeopleCoId","");  //归档人单位id（核发传值）
            json.put("forwardingFile","");  //立即转发文件（核发传值）
            json.put("fileName",null);  //文件名称（加后缀的）（核发传值）
            json.put("docContent",docContext);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.NIZHI_SAVE, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                String msg = map.get("msg");
                if (msg.equals("success")){
                    toast("保存成功");
                }else{
                    toast(msg);
                }
            }
        });
    }

    //手写批注
    private void writeDocument() {

        WritePadDialog mWritePadDialog = new WritePadDialog(
                DraftActivity.this, new WriteDialogListener() {
            @Override
            public void onPaintDone(Object object) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.write_pad2, null);
                dialog.setContentView(view);
                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mIVSign = view.findViewById(R.id.iv_sign);
                mSignBitmap = (Bitmap) object;
                createSignFile();
                mIVSign.setImageBitmap(mSignBitmap);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
        mWritePadDialog.show();
    }

    //文件保存并立即发送
    private void fileSave() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        /*JSONObject jsonObject = new JSONObject();
           jsonObject.put("assigneeList",assigneeList);  //审核人列表*/
        jsonArray.put(assigneeList);
        try {
            json.put("depUserId",depUserId);  //用户ID
            json.put("departmentId",departmentId);  //部门id
            json.put("assigneeList",jsonArray);
            json.put("quasiNumber",quasiNumber);  //公文拟编号
            json.put("qNumber",qNumber);  //拟编号的数字，例：001
            json.put("docTitle",docTitle);  //公文标题
            json.put("changeType",changeType);  //1.pdf 2. Word 3.图片
            json.put("docType",docType);   //公文类型 调查询字典表接口参数：doc_type
            json.put("publicProperty",publicProperty);  //公开属性：0 不公开 1主动公开 2依申请公开
            json.put("docTheme",docTheme);  //公文主题 查询公文主题接口（查询所有父id为空公文主题）
            json.put("relationId",relationId);  //关联附件表relation_id
            json.put("timingSendTime","2020-6-24 10:10:35");  //定时发送时间
            json.put("signUnit",null);  //会签单位
            json.put("docNumber","");  //公文文号（核发传值）
            json.put("number","");  //公文文号的数字号（核发传值）
            json.put("modelId","");  //模板ID
            json.put("remark",remark);  //备注
            json.put("sendTime",time);  //发送时间
            json.put("filePath","");  //文件存放地址
            json.put("registerId","");  //报发人id（核发传值）
            json.put("archivePeopleId","");  //归档人id（核发传值）
            json.put("archivePeopleCoId","");  //归档人单位id（核发传值）
            json.put("forwardingFile","");  //立即转发文件（核发传值）
            json.put("fileName",null);  //文件名称（加后缀的）（核发传值）
            json.put("docContent",docContext);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonMess = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.NIZHI_SAVESUBMIT, jsonMess, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                String msg = map.get("msg");
                if (msg.equals("success")){
                    toast("保存发送成功");
                }else{
                    toast(msg);
                }
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

    //创建签名文件
    private void createSignFile() {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        String path = null;
        File file = null;
        try {
            path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".jpg";
            file = new File(path);
            fos = new FileOutputStream(file);
            baos = new ByteArrayOutputStream();
            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
            mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            if (b != null) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
