package com.lawe.starofadministration.aty;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;
/**
 * author : fuke
 * date : 2020/4/14 15:22
 * description : 聊天
 */
@Layout(R.layout.activity_chat)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ChatActivity extends BaseAty {

    private ImageView chatBack;
    private EditText chatEdit;
    private Button chatButton;
    private TextView chatName;
    private ImageView chatMessage;
    private ImageView chatMore;
    private LinearLayout chatMoreList;
    private TextView centerNizhi;
    private int chatFlag = 0;

    @Override
    public void initViews() {
        super.initViews();
        chatBack = findViewById(R.id.chat_back);
        chatEdit = findViewById(R.id.chat_edit);
        chatButton = findViewById(R.id.chat_button);
        chatName = findViewById(R.id.chat_name);
        chatMessage = findViewById(R.id.chat_message);
        chatMore = findViewById(R.id.chat_more);
        chatMoreList = findViewById(R.id.chat_more_list);
        centerNizhi = findViewById(R.id.center_nizhi);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {
        chatEdit.addTextChangedListener(new TextWatcher() {
            private CharSequence wordNum;//记录输入的字数

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordNum= s;//实时记录输入的字数

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (wordNum.length() > 0){
                    chatButton.setText("发送");
                }else if(wordNum.length() == 0){
                    chatButton.setText("常用语");
                }
            }
        });

        //返回
        chatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chatMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chatFlag == 0){
                    chatMoreList.setVisibility(View.VISIBLE);
                    chatFlag  = 1;
                }else if(chatFlag == 1){
                    chatMoreList.setVisibility(View.GONE);
                    chatFlag = 0;
                }
            }
        });

        chatMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChatPersonMessActivity.class);
            }
        });
    }

}
