package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.EnclosureAdapter;
import com.lawe.starofadministration.base.BaseFgt;
import com.lawe.starofadministration.bean.ZhutiFindAllBean;
import com.lawe.starofadministration.config.Constants;
import com.lawe.starofadministration.utils.PickerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    //空集合
    private List<String> list;
    private String path;
    private RecyclerView enclosureRecycleAdd;
    private EnclosureAdapter enclosureAdapter;
    private int page = 1;
    private int limit = 10;
    private String relationId;
    private File fileurl;
    private String fileName;

    @Override
    public Bitmap fastblur(Context context, Bitmap sentBitmap, int radius) {
        return super.fastblur(context, sentBitmap, radius);
    }

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
        list = new ArrayList<>();
        enclosureRecycle.setNestedScrollingEnabled(false);
        enclosureRecycle.setLayoutManager(new LinearLayoutManager(me));
        enclosureAdapter = new EnclosureAdapter(R.layout.item_enclosure);
        enclosureRecycle.setAdapter(enclosureAdapter);
    }

    @Override
    public void initDatas() {
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        enclosureAdapter.setNewData(list);
        enclosureAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        //上传附件监听事件
        enclosureShangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                getTime();
            }
        });
    }

    //附件目录接口
    private void getUpload() {
        JSONObject json = new JSONObject();
        try {
            json.put("page",page);
            json.put("limit",limit);
            json.put("relationId", relationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonUpload = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.UPLOADQUERYPAGE, jsonUpload, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {

            }
        });
    }

    /** 调用文件选择软件来选择文件 **/
    private void showFileChooser() {
        //这是调用系统
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    /*上传附件回调，调用上传接口*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
                //tv.setText(path);\
                fileurl = new File(path);
                fileName = fileurl.getParentFile().getName();
                uploadFile();
                Toast.makeText(fgtContext, path +"11111",Toast.LENGTH_SHORT).show();
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                //4.4以后
                path = uri.getPath();
                fileurl = new File(path);
                fileName = fileurl.getParentFile().getName();
                uploadFile();
                Toast.makeText(fgtContext,path+"333",Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                //path = getRealPathFromURI(uri);
                path = uri.getPath();
                //tv.setText(path);\
                fileurl = new File(path);
                fileName = fileurl.getParentFile().getName();
                uploadFile();
                Toast.makeText(fgtContext, path+"222222", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //上传附件接口
    private void uploadFile() {
        JSONObject json = new JSONObject();
        try {
            json.put("file",fileurl);
            json.put("fileName",fileName);
            json.put("relationId", relationId);
            json.put("depUserId",depUserId);
            json.put("state",1);
            json.put("departmentId", departmentId);
            json.put("departmentName",departFullName);
            json.put("depUserName",name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //json转化为string类型
        String jsonUpload = String.valueOf(json);
        HttpRequest.JSONPOST(me, Constants.UPLOADFILE, jsonUpload, new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {

            }
        });
    }


    public static EnclosureCatalogFragment newInstance() {
        return new EnclosureCatalogFragment();
    }

}
