package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.EnclosureAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.EnclosureListBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.Uri2PathUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import static com.lawe.starofadministration.fgt.QRcodeFragment.RESULT_OK;

/**
 * author : fuke
 * date : 2020/4/30 16:38
 * description : 起草公文---附件目录
 **/

@Layout(R.layout.fgt_enclosure)
public class EnclosureCatalogFragment extends BaseFgt {

    private RecyclerView enclosureRecycle;
    private LinearLayout enclosureShangchuan;
    private TextView enclosureText;

    private String path;
    private RecyclerView enclosureRecycleAdd;
    private EnclosureAdapter enclosureAdapter;
    private int page = 1;
    private int limit = 10;
    private String relationId;
    private File fileurl;
    private String fileName;

    @Override
    public void initViews() {
        super.initViews();
        enclosureRecycle = (RecyclerView) findViewById(R.id.enclosure_recycle);
        enclosureRecycleAdd = (RecyclerView) findViewById(R.id.enclosure_recycle_add);
        enclosureShangchuan = (LinearLayout) findViewById(R.id.enclosure_shangchuan);
        enclosureText = (TextView) findViewById(R.id.enclosure_text);
        //设置字体
        enclosureText.setTypeface(getTextMedium);
        //获取relationId
        SharedPreferences nizhi_uuid = fgtContext.getSharedPreferences("nizhi_uuid", Context.MODE_PRIVATE);
        relationId = nizhi_uuid.getString("ni_relationId","");
        //附件目录
        getUpload();

        //附件列表
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
        //上传附件监听事件
        enclosureShangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                //获取创建时间
                getTime();
            }
        });
    }

    //附件目录
    private void getUpload() {
        showPopDialog();
        JSONObject json = new JSONObject();
        try {
            json.put("page",page);
            json.put("limit",limit);
            json.put("relationId", relationId);
            json.put("state",null);
            json.put("uploadifyTitle",null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonUpload = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.UPLOADQUERYPAGE, jsonUpload, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    EnclosureListBean enclosureListBean = gson.fromJson(response, EnclosureListBean.class);
                    List<EnclosureListBean.PageBean.ListBean> list = enclosureListBean.getPage().getList();
                    enclosureAdapter.setNewData(list);
                    enclosureAdapter.setSize(list.size());
                }else{
                    error.getMessage();
                }

            }
        });
    }

    /** 调用文件选择软件来选择文件 **/
    private void showFileChooser() {
        //这是调用系统
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,200);
    }

    @SuppressLint("MissingSuperCall")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            if (data != null){
                Uri selectedImage = data.getData();
                if(selectedImage != null){
                    String urlpath = Uri2PathUtil.getRealPathFromUri(getActivity(),selectedImage);
                    File  file = new File(urlpath);
                    uploadFile(file);
                }
            }
        }
    }

    //上传附件接口
    private void  uploadFile(File file) {
        showPopDialog();
        HttpRequest.POST(me, Constants.UPLOADFILE, new Parameter()
                        .add("file",file)
                        .add("relationId", relationId)
                        .add("depUserId",depUserId)
                        .add("state",1)
                        .add("departmentId", departmentId)
                        .add("departmentName",departFullName)
                        .add("depUserName",name)
                , new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                endLoading();
                if (error == null){
                    toast("上传成功");
                    //附件目录
                    getUpload();
                }else{
                    error.getMessage();
                }
            }
        });
    }

    public static EnclosureCatalogFragment newInstance() {
        return new EnclosureCatalogFragment();
    }

}
