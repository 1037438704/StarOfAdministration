package com.lawe.starofadministration.ocr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.heynchy.baiduocr.BaiduOCRUtil;
import com.heynchy.baiduocr.Utils.OCRFileUtil;
import com.heynchy.baiduocr.event.ResultEvent;
import com.heynchy.baiduocr.resultInterface.ResultListener;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.EventOcrBean;
import com.lawe.starofadministration.utils.BasicUtils;
import com.lawe.starofadministration.utils.Uri2PathUtil;

import org.greenrobot.eventbus.EventBus;

import static com.heynchy.baiduocr.Utils.Constant.REC_GENERAL_BASIC;


/**
 * 通用文字识别服务的Activity-------通用文字识别
 *
 * @author CHY
 * Create at 2017/12/6 15:57.
 */
public class RecognizeGeneralActivity extends AppCompatActivity {

    private TextView mBeginTv;            // 开始按钮
    private TextView mIntroduceTv;        // 基本的介绍
    private TextView mResultTv;           // 显示分析的结果
    private TextView mTitleTv;            // 标题
    private TextView lalla;            // 标题

    /**
     * 静态跳转至该界面
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, RecognizeGeneralActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_general);
        initView();
    }

    /**
     * 初始化控件和UI
     */
    private void initView() {
        mBeginTv = (TextView) findViewById(R.id.tv_begin_recognize);
        lalla =  findViewById(R.id.lalla);
        mIntroduceTv = (TextView) findViewById(R.id.tv_general_introduce);
        mResultTv = (TextView) findViewById(R.id.tv_show_result);
        mTitleTv = findViewById(R.id.tv_title);
        mTitleTv.setText(BasicUtils.getTitle(REC_GENERAL_BASIC));
        BaiduOCRUtil.openOCRWithOcrUi(RecognizeGeneralActivity.this, REC_GENERAL_BASIC);

        mIntroduceTv.setText("免费版-----识别图片中的文字信息, 非高精度，没有位置信息；免费使用，每天最高500次识别");
        mBeginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecognizeGeneralActivity.this,"点击了",Toast.LENGTH_LONG);
                BaiduOCRUtil.openOCRWithOcrUi(RecognizeGeneralActivity.this, REC_GENERAL_BASIC);
            }
        });
        lalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiduOCRUtil.openOCRWithOcrUi(RecognizeGeneralActivity.this, REC_GENERAL_BASIC);
            }
        });
    }

    /**
     * 在onActivityResult的方法中获取图片的返回并进行文字识别处理
     *
     * @param requestCode 此处的返回参数应该等于通用文字识别的类型
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("zdl", "=============================="+data.getData());
        // 识别成功回调，通用文字识别
        if (requestCode == REC_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            if(selectedImage != null){
                String urlpath = Uri2PathUtil.getRealPathFromUri(this,selectedImage);
                Toast.makeText(this, urlpath, Toast.LENGTH_SHORT).show();
            }


            BaiduOCRUtil.recognizeOCR(REC_GENERAL_BASIC,
                    OCRFileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new ResultListener() {
                        @Override
                        public void onResult(ResultEvent result) {
                            // 通用位置识别无位置信息
                            /**
                             *  文字解析结果 result.getResultWorld()
                             *  文字位置信息  result.getLocationJson()
                             */
                            Log.d("zdl", "==========result==============="+result);
                            Log.d("zdl", "============getLocationJson============="+result.getLocationJson());
                            Log.d("zdl", "=============getResultWorld============"+result.getResultWorld());
                            mResultTv.setText(result.getResultWorld());
                            EventBus.getDefault().postSticky(new EventOcrBean(result.getResultWorld()));
                            finish();
                        }

                        @Override
                        public void onError(String error) {
                            mResultTv.setText(error);
                        }
                    });
        }
    }
}