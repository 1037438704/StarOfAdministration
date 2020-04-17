package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseAty;

/**
 * author : dell
 * date : 2020/4/17 13:18
 * description :  问题反馈
 */

@Layout(R.layout.activity_feed_back)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class FeedBackActivity extends BaseAty {

    private ImageView setFeedBack;
    private TextView setFeedTitle;
    private TextView setFeedFeed;

    @Override
    public void initViews() {
        setFeedBack = findViewById(R.id.set_feed_back);
        setFeedTitle = findViewById(R.id.set_feed_title);
        setFeedFeed = findViewById(R.id.set_feed_feed);

        setFeedTitle.setTypeface(getTextMedium);
        setFeedFeed.setTypeface(getTextMedium);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {
        //返回
        setFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

}
