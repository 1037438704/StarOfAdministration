package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.LoginDefaltBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.map.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Map;

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
    private WebView webview;
    private String filePath = "0",depUserId = "1253514067132448770",temWordfile = "aa",documentationType = "0";
    private TextView webceshi;
    private TextView newWorkTitle;
    private TextView documentSubTitle;
    private String qNumber;  //拟编号的数字
    private String quasiNumber;  //公文拟编号

    @Override
    public void initViews() {
        super.initViews();
        documentTitle = (EditText) findViewById(R.id.document_title);
        documentNumber = (TextView) findViewById(R.id.document_number);
        documentSubject = (EditText) findViewById(R.id.document_subject);
        newWorkTitle = (TextView) findViewById(R.id.new_work_title);
        documentSubTitle = (TextView) findViewById(R.id.document_sub_title);
        //documentScroll = (ScrollView) findViewById(R.id.document_scroll);
        cebianlan = (LinearLayout) findViewById(R.id.cebianlan);
        imgBig = (ImageView) findViewById(R.id.img_big);
        imgSmall = (ImageView) findViewById(R.id.img_small);
        imgTop = (ImageView) findViewById(R.id.img_top);
        imgRead = (ImageView) findViewById(R.id.img_read);
        imgBohui = (ImageView) findViewById(R.id.img_bohui);
        webview = (WebView) findViewById(R.id.webview);
        webceshi = (TextView) findViewById(R.id.document_sub_title);
        //设置字体
        documentTitle.setTypeface(getTextMedium);
        newWorkTitle.setText("公文标题：");
        documentSubTitle.setText("公文主体：");

        //判断公文主体是否为空
       // isEmpty = TextUtils.isEmpty(documentSubject.getText());

        //获取拟编号
        getQuasiNumber();
        //查询公文拟制公文字号数值
        getNumber();

        showWord();
    }

    private void getNumber() {
        JSONObject json = new JSONObject();
        try {
            json.put("id","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonLogin = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.GETNUMBER, jsonLogin, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                qNumber = map.get("number");
            }
        });
    }

    private void getQuasiNumber() {

        HttpRequest.build(me,Constants.GETQUASINUMBER + departmentId)
                .setResponseListener(new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if(error == null){
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            quasiNumber = map.get("newQuasiNumber");
                            documentNumber.setText("拟编号："+ quasiNumber);
                        }else{
                            error.getMessage();
                        }
                    }
                }).doGet();
    }

    @Override
    public void initDatas() {

    }

    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    private void showWord() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setDomStorageEnabled(true);//设置适应Html5的一些方法
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        //webSettings.setLoadWithOverviewMode(true);
        webview.loadUrl("http://192.168.0.178:8081/szzw-web/sys/poffice/mobileShowWord");
        //在js中调用本地java方法
        webview.addJavascriptInterface(new JsInterface(me), "AndroidWebView");
        //添加客户端支持
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (shouldOverrideUrlLoadingByApp(view, url)) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webceshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInfoToJs();
            }
        });
    }

    /**
     * 根据url的scheme处理跳转第三方app的业务
     */
    private boolean shouldOverrideUrlLoadingByApp(WebView view, String url) {
        if (url.startsWith("http") || url.startsWith("https") || url.startsWith("ftp")) {
            //不处理http, https, ftp的请求
            return false;
        }
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            return false;
        }
        intent.setComponent(null);
        try {
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            return false;
        }
        return true;
    }

    private void sendInfoToJs() {
        //调用js中的函数：
        webview.loadUrl("javascript:aa('"+filePath +"','"+depUserId +"','"+temWordfile +"','"+documentationType +"')");
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void showInfoFromJs(String name) {
            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setEvents() {
       /* //实时监听edittext内容变化
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
        });*/
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
