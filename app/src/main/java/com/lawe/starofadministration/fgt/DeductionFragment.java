package com.lawe.starofadministration.fgt;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.DeductionAdapter;
import com.lawe.starofadministration.adp.PersonAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/10 11:40
 * description :  扣分事项
 */
@Layout(R.layout.fgt_deduction)
public class DeductionFragment extends BaseFgt {
    private LinearLayout deduLeibie;
    private TextView deduLeibieText;
    private ImageView deduLeibieImg;
    private TextView deduYear;
    private RecyclerView recycleDeduction;
    private LinearLayout linearLeibie;
    private RecyclerView recyclerLeibie;

    private int flag = 1; //类别默认标识

    //空集合
    private List<String> list;
    private DeductionAdapter deductionAdapter;
    @Override
    public void initViews() {
        super.initViews();
        deduLeibie = (LinearLayout) findViewById(R.id.dedu_leibie);
        deduLeibieText = (TextView) findViewById(R.id.dedu_leibie_text);
        deduLeibieImg = (ImageView) findViewById(R.id.dedu_leibie_img);
        deduYear = (TextView) findViewById(R.id.dedu_year);
        recycleDeduction = (RecyclerView) findViewById(R.id.recycle_deduction);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        //recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);

        list = new ArrayList<>();
        //扣分事项列表
        recycleDeduction.setLayoutManager(new LinearLayoutManager(me));
        deductionAdapter = new DeductionAdapter(R.layout.item_datas_deduction);
        recycleDeduction.setAdapter(deductionAdapter);
    }

    @Override
    public void initDatas() {
        //个人排名列表
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        deductionAdapter.setNewData(list);
        deductionAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static DeductionFragment newInstance() {
        return new DeductionFragment();
    }

}
