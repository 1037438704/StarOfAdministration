package com.lawe.starofadministration.fgt;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.heynchy.baiduocr.BaiduOCRUtil;
import com.heynchy.baiduocr.Utils.OCRFileUtil;
import com.heynchy.baiduocr.event.ResultEvent;
import com.heynchy.baiduocr.resultInterface.ResultListener;
import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.EventFactionBean;
import com.lawe.starofadministration.bean.EventOcrBean;
import com.lawe.starofadministration.ocr.RecognizeGeneralActivity;
import com.lawe.starofadministration.utils.BasicUtils;
import com.lawe.starofadministration.utils.Uri2PathUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.Map;

import static com.heynchy.baiduocr.Utils.Constant.REC_GENERAL_BASIC;

/**
 * author : fuke
 * date : 2020/4/10 11:40
 * description : OCR文档识别
 */
@Layout(R.layout.fgt_ocr_shibie)
public class OCRwordFragment extends BaseFgt{

    private ImageView ocrShibie;
    private String content = "";
    private TextView text;

    @Override
    public void initViews() {
        super.initViews();
        EventBus.getDefault().register(this);
        ocrShibie = (ImageView) findViewById(R.id.ocrShibie);
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    public void initDatas() {

        if (!TextUtils.isEmpty(content)){
            show();
        }
    }

    @Override
    public void setEvents() {
        ocrShibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通用文字识别
                RecognizeGeneralActivity.start(getActivity());
                //BaiduOCRUtil.openOCRWithOcrUi(getActivity(), REC_GENERAL_BASIC);
            }
        });
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getData(EventOcrBean eventOcrBean) {
        content = eventOcrBean.getContent();
        text.setText(content);
        Log.e("didid",content);
        show();
    }

    private void show() {
        Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        View view = View.inflate(getActivity(), R.layout.pop_alredy_shibie, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout news = view.findViewById(R.id.news);
        LinearLayout copy = view.findViewById(R.id.copy);
        TextView mResultTv = view.findViewById(R.id.tv_show_result);
        mResultTv.setText(content);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecognizeGeneralActivity.start(getActivity());
                dialog.dismiss();
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("", mResultTv.getText().toString());
//                (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE).setPrimaryClip(clipData);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    public static OCRwordFragment newInstance() {
        return new OCRwordFragment();
    }

    /**
     * 在onActivityResult的方法中获取图片的返回并进行文字识别处理
     *
     * @param requestCode 此处的返回参数应该等于通用文字识别的类型
     * @param resultCode
     * @param data
     */
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("zdl", "=============================="+data.getData());
        // 识别成功回调，通用文字识别
        if (requestCode == REC_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            if(selectedImage != null){
                String urlpath = Uri2PathUtil.getRealPathFromUri(getActivity(),selectedImage);
                Toast.makeText(getActivity(), urlpath, Toast.LENGTH_SHORT).show();
            }


            BaiduOCRUtil.recognizeOCR(REC_GENERAL_BASIC,
                    OCRFileUtil.getSaveFile(getActivity().getApplicationContext()).getAbsolutePath(),
                    new ResultListener() {
                        @Override
                        public void onResult(ResultEvent result) {
                            // 通用位置识别无位置信息
                            *//**
                             *  文字解析结果 result.getResultWorld()
                             *  文字位置信息  result.getLocationJson()
                             *//*
                            Log.d("zdl", "==========result==============="+result);
                            Log.d("zdl", "============getLocationJson============="+result.getLocationJson());
                            Log.d("zdl", "=============getResultWorld============"+result.getResultWorld());
                            mResultTv.setText(result.getResultWorld());
                        }

                        @Override
                        public void onError(String error) {
                            mResultTv.setText(error);
                        }
                    });
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        BaiduOCRUtil.release();
    }
}
