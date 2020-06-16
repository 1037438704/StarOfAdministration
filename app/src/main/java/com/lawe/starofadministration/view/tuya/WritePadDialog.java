package com.lawe.starofadministration.view.tuya;

/**
 * author : fuke
 * date : 2020/6/15 14:06
 * description : 手写涂鸦弹框
 */
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lawe.starofadministration.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritePadDialog extends Dialog {
    private Context mContext;
    private WriteDialogListener mWriteDialogListener;
    private PaintView mPaintView;
    private FrameLayout mFrameLayout;
    private Button mBtnOK, mBtnClear, mBtnCancel;
    private LinearLayout writeHuaban;
    private LinearLayout writeImg;
    private Bitmap mSignBitmap;
    private ImageView mIVSign;

    public WritePadDialog(Context context,
                          WriteDialogListener writeDialogListener) {
        super(context);
        this.mContext = context;
        this.mWriteDialogListener = writeDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //无标题
        setContentView(R.layout.write_pad);

        mFrameLayout = (FrameLayout) findViewById(R.id.tablet_view);
        writeHuaban = findViewById(R.id.write_huaban);
        writeImg = findViewById(R.id.write_img);

        // 获取屏幕尺寸
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int screenWidth = mDisplayMetrics.widthPixels;
        int screenHeight = mDisplayMetrics.heightPixels;
        mPaintView = new PaintView(mContext, screenWidth, screenHeight);
        mFrameLayout.addView(mPaintView);
        mPaintView.requestFocus();

        mBtnOK = findViewById(R.id.write_pad_ok);
        mIVSign = findViewById(R.id.iv_sign);
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPaintView.getPath().isEmpty()) {
                    Toast.makeText(mContext, "请写批注", Toast.LENGTH_SHORT).show();
                    return;
                }
                mWriteDialogListener.onPaintDone(mPaintView.getPaintBitmap());
                dismiss();
            }
        });

        mBtnClear = findViewById(R.id.write_pad_clear);
        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaintView.clear();
            }
        });

        mBtnCancel = findViewById(R.id.write_pad_cancel);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
}
