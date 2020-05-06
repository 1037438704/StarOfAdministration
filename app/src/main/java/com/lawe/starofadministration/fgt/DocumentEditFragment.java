package com.lawe.starofadministration.fgt;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : fuke
 * date : 2020/4/30 16:36
 * description : 起草公文---公文编辑
 **/

@Layout(R.layout.fgt_document_edit)
public class DocumentEditFragment extends BaseFgt {

    private EditText documentTitle;
    private TextView documentNumber;
    private EditText documentSubject;
    private LinearLayout cebianlan;
    private ImageView imgBig;
    private ImageView imgSmall;
    private ImageView imgTop;
    private ImageView imgRead;
    private ImageView imgBohui;
    private boolean isEmpty;
    private ScrollView documentScroll;
    private int textsize = 16;

    @Override
    public void initViews() {
        documentTitle = (EditText) findViewById(R.id.document_title);
        documentNumber = (TextView) findViewById(R.id.document_number);
        documentSubject = (EditText) findViewById(R.id.document_subject);
        //documentScroll = (ScrollView) findViewById(R.id.document_scroll);
        cebianlan = (LinearLayout) findViewById(R.id.cebianlan);
        imgBig = (ImageView) findViewById(R.id.img_big);
        imgSmall = (ImageView) findViewById(R.id.img_small);
        imgTop = (ImageView) findViewById(R.id.img_top);
        imgRead = (ImageView) findViewById(R.id.img_read);
        imgBohui = (ImageView) findViewById(R.id.img_bohui);

        //设置字体
        documentTitle.setTypeface(getTextMedium);

        //判断公文主体是否为空
        isEmpty = TextUtils.isEmpty(documentSubject.getText());
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //实时监听edittext内容变化
        documentSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                isEmpty = TextUtils.isEmpty(documentSubject.getText());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEmpty = TextUtils.isEmpty(documentSubject.getText());
                //如果为空
                if (isEmpty) {
                    cebianlan.setVisibility(View.GONE);
                } else {
                    cebianlan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //监听edittext滚动
        documentSubject.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        imgTop.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });
        //输入内容回滚顶部
        imgTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentSubject.setSelection(0);
            }
        });
        //放大字体
        imgBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textsize++;
                if(textsize == 25){
                    textsize = 25;
                    toast("不能再大了");
                    return;
                }
                documentSubject.setTextSize(textsize);
            }
        });

        //缩小字体
        imgSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textsize--;
                if(textsize == 12){
                    textsize = 12;
                    toast("不能再小了");
                    return;
                }
                documentSubject.setTextSize(textsize);
            }
        });
    }

    public static DocumentEditFragment newInstance() {
        return new DocumentEditFragment();
    }

}
