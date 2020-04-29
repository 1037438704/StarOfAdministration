package com.lawe.starofadministration.fgt;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.CompanyAdapter;
import com.lawe.starofadministration.adp.PersonAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/10 11:40
 * description : 个人排名
 */
@Layout(R.layout.fgt_person)
public class PersonFragment extends BaseFgt {
    private RecyclerView recyclePerson;
    private LinearLayout linearLeibie;
    private RecyclerView recyclerLeibie;

    private int flag = 1; //类别默认标识

    //空集合
    private List<String> list;
    private PersonAdapter personAdapter;

    @Override
    public void initViews() {
        recyclePerson = (RecyclerView) findViewById(R.id.recycle_person);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);

        list = new ArrayList<>();
        //个人排名列表
        recyclePerson.setLayoutManager(new LinearLayoutManager(me));
        personAdapter = new PersonAdapter(R.layout.item_datas_person);
        recyclePerson.setAdapter(personAdapter);

    }

    @Override
    public void initDatas() {
        //个人排名列表
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        personAdapter.setNewData(list);
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {

    }

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

}
