package com.lawe.starofadministration.view;

import android.content.Context;
import com.lawe.starofadministration.bean.fontbean.FontStyle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


import com.kongzue.baseframework.interfaces.BindView;
import com.kongzue.baseframework.interfaces.OnClick;
import com.lawe.starofadministration.R;

import butterknife.ButterKnife;


/**
 * Created by awarmisland on 2018/9/10.
 */
public class FontStylePanel extends LinearLayout {
    private Context mContext;
    private OnFontPanelListener onFontPanelListener;

    @BindView(R.id.btn_img)
    Button btn_img;
    //字体 加粗 斜杠 删除线 下划线 设置
   /* @BindView(R.id.fontStyleSelectView)
    FontStyleSelectView cusView_fontStyle;*/
    //字体 大小
   /* @BindView(R.id.fontSizeSelectView)
    FontSizeSelectView cusView_fontSize;*/
    //字体颜色
    /*@BindView(R.id.fontColorSelectView)
    FontsColorSelectView cusView_fontColor*/;

    private FontStyle fontStyle;
    private FontStyleSelectView cusView_fontStyle;
    private FontSizeSelectView cusView_fontSize1;
    private FontsColorSelectView cusView_fontColor1;

    public FontStylePanel(Context context) {
        super(context);
        initView(context);
    }

    public FontStylePanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FontStylePanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_font_style_panel, this);
        cusView_fontStyle = view.findViewById(R.id.fontStyleSelectView);
        cusView_fontSize1 = view.findViewById(R.id.fontSizeSelectView);
        cusView_fontColor1 = view.findViewById(R.id.fontColorSelectView);
        ButterKnife.bind(this);
        fontStyle = new FontStyle();
        //字体样式
        cusView_fontStyle.setOnFontStyleSelectListener(onFontStyleSelectListener);
        cusView_fontStyle.setFontStyle(fontStyle);
        //字体大小
        cusView_fontSize1.setOnFontSizeChangeListener(onFontSizeChangeListener);
        cusView_fontSize1.setFontStyle(fontStyle);
        //字体颜色
        cusView_fontColor1.setOnColorSelectListener(onColorSelectListener);
        cusView_fontColor1.setFontStyle(fontStyle);
    }

    //拍照
    @OnClick(R.id.btn_img)
    protected void btn_img_onClick(){
        if(onFontPanelListener!=null) {
            onFontPanelListener.insertImg();
        }
    }

    /**
     * 字体样式 设置
     */
    private FontStyleSelectView.OnFontStyleSelectListener onFontStyleSelectListener = new FontStyleSelectView.OnFontStyleSelectListener() {
        @Override
        public void setBold(boolean isBold) {
            if(onFontPanelListener!=null) {
                onFontPanelListener.setBold(isBold);
            }
        }
        @Override
        public void setItalic(boolean isItalic) {
            if(onFontPanelListener!=null) {
                onFontPanelListener.setItalic(isItalic);
            }
        }
        @Override
        public void setUnderline(boolean isUnderline) {
            if(onFontPanelListener!=null) {
                onFontPanelListener.setUnderline(isUnderline);
            }
        }
        @Override
        public void setStreak(boolean isStreak) {
            if(onFontPanelListener!=null) {
                onFontPanelListener.setStreak(isStreak);
            }
        }
    };

    /**
     * 字体 大小
     */
    private FontSizeSelectView.OnFontSizeChangeListener onFontSizeChangeListener = new FontSizeSelectView.OnFontSizeChangeListener() {
        @Override
        public void onFontSizeSelect(int size) {
            if(onFontPanelListener!=null){
                onFontPanelListener.setFontSize(size);
            }
        }
    };
    /**
     * 字体 颜色
     */
    private FontsColorSelectView.OnColorSelectListener onColorSelectListener = new FontsColorSelectView.OnColorSelectListener() {
        @Override
        public void onColorSelect(int color) {
            if(onFontPanelListener!=null){
                onFontPanelListener.setFontColor(color);
            }
        }
    };

    public interface OnFontPanelListener{
        void setBold(boolean isBold);
        void setItalic(boolean isItalic);
        void setUnderline(boolean isUnderline);
        void setStreak(boolean isStreak);
        void insertImg();
        void setFontSize(int size);
        void setFontColor(int color);
    }


    public void initFontStyle(FontStyle fontStyle){
        this.fontStyle =fontStyle;
        cusView_fontStyle.initFontStyle(fontStyle);
        cusView_fontSize1.initFontStyle(fontStyle);
        cusView_fontColor1.initFontStyle(fontStyle);
    }

    public void setOnFontPanelListener(OnFontPanelListener onFontPanelListener) {
        this.onFontPanelListener = onFontPanelListener;
    }
}
