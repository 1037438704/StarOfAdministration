package com.lawe.starofadministration.fgt;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : fuke
 * date : 2020/4/10 11:40
 * description : OCR文档识别
 */
@Layout(R.layout.fgt_ocr_shibie)
public class OCRwordFragment extends BaseFgt {
    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static OCRwordFragment newInstance() {
        return new OCRwordFragment();
    }
}
