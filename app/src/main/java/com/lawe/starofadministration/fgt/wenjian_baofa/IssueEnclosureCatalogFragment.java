package com.lawe.starofadministration.fgt.wenjian_baofa;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.EnclosureAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/25 16:38
 * description : 文件报发---附件目录
 **/

@Layout(R.layout.fgt_enclosure)
public class IssueEnclosureCatalogFragment extends BaseFgt {

    private RecyclerView enclosureRecycle;
    private LinearLayout enclosureShangchuan;
    private TextView enclosureText;

    //空集合
    private List<String> list;

    private EnclosureAdapter enclosureAdapter;

    @Override
    public void initViews() {
        enclosureRecycle = (RecyclerView) findViewById(R.id.enclosure_recycle);
        enclosureShangchuan = (LinearLayout) findViewById(R.id.enclosure_shangchuan);
        enclosureText = (TextView) findViewById(R.id.enclosure_text);
        //设置字体
        enclosureText.setTypeface(getTextMedium);
        //附件列表
        list = new ArrayList<>();
        //待办信息
        enclosureRecycle.setNestedScrollingEnabled(false);
        enclosureRecycle.setLayoutManager(new LinearLayoutManager(me));
        enclosureAdapter = new EnclosureAdapter(R.layout.item_enclosure);
        enclosureRecycle.setAdapter(enclosureAdapter);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    public static IssueEnclosureCatalogFragment newInstance() {
        return new IssueEnclosureCatalogFragment();
    }

}
