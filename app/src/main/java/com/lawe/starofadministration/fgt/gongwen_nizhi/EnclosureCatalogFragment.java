package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.EnclosureAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        enclosureAdapter.setNewData(list);
        enclosureAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        //上传附件
        enclosureShangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    /** 调用文件选择软件来选择文件 **/
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
                //tv.setText(path);\
                Toast.makeText(fgtContext, path +"11111",Toast.LENGTH_SHORT).show();
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                //4.4以后
                //path = getPath(this, uri);
                path = uri.getPath();
                Toast.makeText(fgtContext,path+"333",Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                //path = getRealPathFromURI(uri);
                Toast.makeText(fgtContext, path+"222222", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static EnclosureCatalogFragment newInstance() {
        return new EnclosureCatalogFragment();
    }

}
