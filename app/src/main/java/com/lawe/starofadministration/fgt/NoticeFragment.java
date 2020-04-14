package com.lawe.starofadministration.fgt;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.aty.ChatActivity;
import com.lawe.starofadministration.aty.LookAllActivity;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/8 10:23
 * description :待办----通知信息
 */
@Layout(R.layout.fgt_notice)
public class NoticeFragment extends BaseFgt {

    private TextView text_lookAll;
    private LinearLayout liaotian;

    @Override
    public void initViews() {
        text_lookAll = (TextView) findViewById(R.id.text_lookAll);
        liaotian = (LinearLayout) findViewById(R.id.liaotian);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //查看全部--跳转
        text_lookAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(LookAllActivity.class);
            }
        });

        //聊天列表
        liaotian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ChatActivity.class);
            }
        });
    }

    public static NoticeFragment newInstance() {
        return new NoticeFragment();
    }
}
