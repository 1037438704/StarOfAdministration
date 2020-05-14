package com.lawe.starofadministration.fgt;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.CompanyAdapter;
import com.lawe.starofadministration.adp.SpeedAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/10 11:40
 * description : 单位排名
 */
@Layout(R.layout.fgt_company)
public class CompanyFragment extends BaseFgt {
    private RecyclerView recycleCompany;
    private LinearLayout linearLeibie;
    private RecyclerView recyclerLeibie;

    private int flag = 1; //类别默认标识

    //空集合
    private List<String> list;
    private CompanyAdapter companyAdapter;

    @Override
    public void initViews() {
        recycleCompany = (RecyclerView) findViewById(R.id.recycle_company);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        //recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);

        list = new ArrayList<>();
        //单位排名列表
        recycleCompany.setLayoutManager(new LinearLayoutManager(me));
        companyAdapter = new CompanyAdapter(R.layout.item_datas_company);
        recycleCompany.setAdapter(companyAdapter);
    }

    @Override
    public void initDatas() {
        //单位排名列表
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        companyAdapter.setNewData(list);
        companyAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static CompanyFragment newInstance() {
        return new CompanyFragment();
    }

}
