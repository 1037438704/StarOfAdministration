package com.lawe.starofadministration.fgt;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MyMedalsAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.MyMedalBean;
import com.lawe.starofadministration.config.Constants;

import java.util.List;

/**
 * author : fuke
 * date : 2020/4/14 15:22
 * description : 基础勋章
 */
@Layout(R.layout.fgt_all_madal)
public class BasicsFragment extends BaseFgt {

    private RecyclerView xunzhang;
    private MyMedalsAdapter myXunzhangAdapter;

    @Override
    public void initViews() {
        xunzhang = (RecyclerView) findViewById(R.id.xunzhang);
        xunzhang.setLayoutManager(new GridLayoutManager(getActivity(),3));
        myXunzhangAdapter = new MyMedalsAdapter(R.layout.item_xunzhang);
        xunzhang.setAdapter(myXunzhangAdapter);
    }

    @Override
    public void initDatas() {
        showPopDialog();
        HttpRequest.GET(getActivity(), Constants.MEDALLIST + depUserId, new Parameter(), new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    MyMedalBean myMedalBean = gson.fromJson(response, MyMedalBean.class);
                    List<MyMedalBean.MapBean.ListBean.MedalsBean> medals = myMedalBean.getMap().getList().get(0).getMedals();
                    myXunzhangAdapter.setNewData(medals);
                }else {
                    error.getMessage();
                }
            }
        });
    }

    @Override
    public void setEvents() {

    }


}
