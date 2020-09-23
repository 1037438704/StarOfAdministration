package com.lawe.starofadministration.aty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.QuickPageAdapter;
import com.lawe.starofadministration.view.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Layout(R.layout.activity_guide)
public class GuideActivity extends BaseActivity {

    private ViewPager viewPager;
    private CircleIndicator indicator;

    @Override
    public void initViews() {
        viewPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.indicator);
        indicator.setVisibility(View.GONE);
        List<View> views = new ArrayList<>();
        int[] images = new int[]{R.mipmap.start_page1,R.mipmap.start_page2,R.mipmap.start_page3,R.mipmap.start_page4,R.mipmap.start_page5,R.mipmap.start_page6};
        for (int i = 0; i < 6; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_guide_page, null);
            ImageView imageView = view.findViewById(R.id.iv_guide);
            if(i == 5){
                LinearLayout ll_next = view.findViewById(R.id.ll_next);
                ll_next.setVisibility(View.VISIBLE);
                ll_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Preferences.getInstance().set(GuideActivity.this,"token","isGuide",true);
                        Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            Glide.with(this).load(images[i]).into(imageView);
            views.add(view);
        }
        viewPager.setAdapter(new QuickPageAdapter<>(views));
        indicator.setViewPager(viewPager, position -> {
        });
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {

    }
}
