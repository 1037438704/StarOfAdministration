package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.Preferences;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.EventFactionBean;
import com.lawe.starofadministration.bean.fontbean.FontStyle;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.handle.CustomHtml;
import com.lawe.starofadministration.handle.RichEditImageGetter;
import com.lawe.starofadministration.handle.Utils;
import com.lawe.starofadministration.utils.map.JSONUtils;
import com.lawe.starofadministration.view.FontStylePanel;
import com.lawe.starofadministration.view.RichEditText;

import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;
import java.util.Map;

import static androidx.core.content.ContextCompat.checkSelfPermission;

/**
 * author : fuke
 * date : 2020/4/30 16:36
 * description : 起草公文---公文编辑
 **/
@Layout(R.layout.fgt_document_edit)
public class DocumentEditFragment extends BaseFgt implements FontStylePanel.OnFontPanelListener
        , RichEditText.OnSelectChangeListener {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
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
    private FontStylePanel fontStylePanel;
    private RichEditText richEditText;
    private Button btnCenter;
    private int flagCenter = 1;
    private SharedPreferences.Editor edit;
    private Handler handler = new Handler();
    private String fictionId;
    private EventFactionBean eventFactionBean;

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
        fontStylePanel = (FontStylePanel) findViewById(R.id.fontStylePanel);
        richEditText = (RichEditText) findViewById(R.id.richEditText);
        btnCenter = (Button) findViewById(R.id.btn_center);
        fontStylePanel.setOnFontPanelListener(this);
        //richEditText.setOnSelectChangeListener(this);
        //设置字体
        documentTitle.setTypeface(getTextMedium);
        newWorkTitle.setText("公文标题：");
        documentSubTitle.setText("公文主体：");

        String flagSpeed = Preferences.getInstance().getString(getActivity(), "doc", "flagSpeed");
        if (flagSpeed.equals("2")){
            String title = Preferences.getInstance().getString(getActivity(),"doc","title");
            String num = Preferences.getInstance().getString(getActivity(),"doc","num");
            String content = Preferences.getInstance().getString(getActivity(),"doc","content");
            documentTitle.setText(title);
            documentNumber.setText(num);
            richEditText.setText(content);
        }
        SharedPreferences fictionIdSp = getContext().getSharedPreferences("fictionId", Context.MODE_PRIVATE);
        fictionId = fictionIdSp.getString("fictionId", "");
        eventFactionBean = new EventFactionBean();
        eventFactionBean.type = 0;
        //获取拟编号
        getQuasiNumber();
        //查询公文拟制公文字号数值
        getNumber();
        //模板
        showWord();

        initRichTextCon();

    }

    //获取公文字号数值
    private void getNumber() {
        /*HttpRequest.GET(me, Constants.GETNUMBER, new Parameter(), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                    qNumber = map.get("number");
                    eventFactionBean.setqNumber(qNumber);
                    EventBus.getDefault().postSticky(eventFactionBean);
                }else {
                    error.getMessage();
                }
            }
        });*/
    }

    //获取拟编号
    private void getQuasiNumber() {

        HttpRequest.build(me,Constants.GETQUASINUMBER + departmentId)
                .setResponseListener(new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if(error == null){
                            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                            quasiNumber = map.get("newQuasiNumber");
                            documentNumber.setText("拟编号："+ quasiNumber);
                            //保存拟编号
                            eventFactionBean.setQuasiNumber(quasiNumber);
                            EventBus.getDefault().postSticky(eventFactionBean);
                        }else{
                            error.getMessage();
                        }
                    }
                }).doGet();
    }

    @Override
    public void initDatas() {
        //时时获取标题内容
        documentTitle.addTextChangedListener(new TextWatcher() {
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
                String docTitle = s.toString();
                eventFactionBean.setDocTitle(docTitle);
                EventBus.getDefault().postSticky(eventFactionBean);
                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRun, 800);
            }
        });

        //时时获取公文主体内容
        richEditText.addTextChangedListener(new TextWatcher() {
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
                String doc_context = s.toString();
                eventFactionBean.setDocContext(doc_context);
                EventBus.getDefault().postSticky(eventFactionBean);
                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRun, 800);
            }
        });
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
                    return;
                }
                documentSubject.setTextSize(textsize);
            }
        });

        btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagCenter == 1){
                    richEditText.setGravity(Gravity.CENTER);
                    btnCenter.setTextColor(ContextCompat.getColor(fgtContext, R.color.colorPrimaryDark));
                    flagCenter = 2;
                }else if(flagCenter == 2){
                    richEditText.setGravity(Gravity.LEFT);
                    btnCenter.setTextColor(ContextCompat.getColor(fgtContext, R.color.result_view));
                    flagCenter = 1;
                }
            }
        });
    }

    //-----------------------------------------------调用第三方pageOffers-----------------------------------------
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

    //----------------------------------设置字体样式、大小、颜色-------------------------------------------------------
    @Override
    public void setBold(boolean isBold) {
        richEditText.setBold(isBold);
    }

    @Override
    public void setItalic(boolean isItalic) {
        richEditText.setItalic(isItalic);
    }

    @Override
    public void setUnderline(boolean isUnderline) {
        richEditText.setUnderline(isUnderline);
    }

    @Override
    public void setStreak(boolean isStreak) { richEditText.setStreak(isStreak); }

    @Override
    public void insertImg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(fgtContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(fgtContext, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(fgtContext, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                pickPicture();
            }
        } else {
            pickPicture();
        }
    }

    private void pickPicture(){
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, 0);
    }
    //字体大小
    @Override
    public void setFontSize(int size) {
        richEditText.setFontSize(size);
    }
    //颜色设置
    @Override
    public void setFontColor(int color) {
        richEditText.setFontColor(color);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            Uri originalUri = data.getData(); // 获得图片的uri
            String path = Utils.getRealPathFromUri(fgtContext,originalUri);
            richEditText.setImg(path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initRichTextCon(){
        String html_content = fgtContext.getIntent().getStringExtra("html_content");
        if(!TextUtils.isEmpty(html_content)){
            Log.d("richText","html转span:"+html_content);
            Spanned spanned = CustomHtml.fromHtml(html_content,CustomHtml.FROM_HTML_MODE_LEGACY,new RichEditImageGetter(fgtContext,richEditText),null);
            richEditText.setText(spanned);
        }
    }

    /*@OnClick(R.id.btn_right)
    protected void btn_right_onClick(){
        String content = CustomHtml.toHtml(richEditText.getEditableText(),CustomHtml.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE);
        Log.d("richText","span转html:"+content);
        Intent intent = new Intent(this,WebviewActivity.class);
        intent.putExtra("content",content);
        startActivity(intent);
    }*/
    /**
     * 样式改变
     * @param fontStyle
     */
    @Override
    public void onFontStyleChang(FontStyle fontStyle) {
        fontStylePanel.initFontStyle(fontStyle);
    }

    /**
     * 光标选中监听
     * @param start
     * @param end
     */
    @Override
    public void onSelect(int start, int end) {
    }

    private void checkPermission() {

    }

    public static DocumentEditFragment newInstance() {
        return new DocumentEditFragment();
    }

}
