package com.lawe.starofadministration.fgt.gongxiang_wenjian;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.config.Constants;

/**
 * author : fuke
 * date : 2020/5/25 16:36
 * description : 共享文件---公文编辑
 **/

@Layout(R.layout.fgt_document_edit)
public class ShareEditFragment extends BaseFgt {

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
    private WebView webview;

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
        webview = (WebView) findViewById(R.id.webview);
        //设置字体
        documentTitle.setTypeface(getTextMedium);

        //判断公文主体是否为空
        isEmpty = TextUtils.isEmpty(documentSubject.getText());

        showWord();
    }

    @Override
    public void initDatas() {

    }

    private void showWord() {
        HttpRequest.POST(me, Constants.SHOWWORD, new Parameter()
                        .add("temWordfile","临时文件")
                        .add("depUserId","1253514067132448770")
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Log.e("shuju",response);
                            WebSettings webSettings = webview.getSettings();
                            webSettings.setDomStorageEnabled(true);//设置适应Html5的一些方法
                            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                            webSettings.setJavaScriptEnabled(true);
                            webSettings.setUseWideViewPort(true);
                            webSettings.setLoadWithOverviewMode(true);
                            webview.loadData(response, "text/html; charset=UTF-8", null);
                        } else {
                            error.getMessage();
                        }
                    }
                });
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

    public static ShareEditFragment newInstance() {
        return new ShareEditFragment();
    }

}
