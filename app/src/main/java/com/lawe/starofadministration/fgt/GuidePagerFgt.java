package com.lawe.starofadministration.fgt;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.FragmentLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.AppManager;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.BootPageAty;
import com.lawe.starofadministration.aty.LoginAty;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * A simple {@link Fragment} subclass.
 */

@Layout(R.layout.fgt_guide_pager)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255,r = 255,g = 255,b = 255)      //设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class GuidePagerFgt extends BaseFgt {
    private FrameLayout frameLayout;
    private int[] bgRes = {R.mipmap.icon_start, R.mipmap.icon_start, R.mipmap.icon_start};
    ImageView imageButton;
    private int index;


    @Override
    public void initViews() {
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        imageButton = (ImageView) findViewById(R.id.imageButton);
    }

    @Override
    public void initDatas() {
        index = this.getArguments().getInt("count");
        frameLayout.setBackgroundResource(bgRes[index]);
        if (index == 2) {
            imageButton.setVisibility(View.VISIBLE);
        } else {
            imageButton.setVisibility(View.GONE);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(LoginAty.class);
                AppManager.getInstance().killActivity(BootPageAty.class);
            }
        });
    }

    @Override
    public void setEvents() {

    }



    public static GuidePagerFgt newInstance(int count) {
        GuidePagerFgt dHomeFragment = new GuidePagerFgt();
        Bundle bundle = new Bundle();
        bundle.putInt("count", count);
        dHomeFragment.setArguments(bundle);
        return dHomeFragment;
    }
}
