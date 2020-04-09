package com.lawe.starofadministration.fgt;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/8 10:23
 * description :待办——待办信息
 */
@Layout(R.layout.fgt_message)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class MessageFragment extends BaseFgt {
    private TextView textCategory;
    private ImageView daibanDown1;
    private TextView textTimer;
    private ImageView daibanDown2;
    private TextView textUrgent;
    private ImageView daibanDown3;
    private LinearLayout textChoose;
    private RecyclerView recycleMessage;
    private LinearLayout linearLeibie;
    private RecyclerView recyclerLeibie;
    private int flag = 1; //类别默认标识
    private PopupWindow popupWindow;

    @Override
    public void initViews() {
        textCategory = (TextView) findViewById(R.id.text_category);
        daibanDown1 = (ImageView) findViewById(R.id.daiban_down1);
        textTimer = (TextView) findViewById(R.id.text_timer);
        daibanDown2 = (ImageView) findViewById(R.id.daiban_down2);
        textUrgent = (TextView) findViewById(R.id.text_urgent);
        daibanDown3 = (ImageView) findViewById(R.id.daiban_down3);
        textChoose = (LinearLayout) findViewById(R.id.text_choose);
        recycleMessage = (RecyclerView) findViewById(R.id.recycle_message);

        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //类别选择
        daibanDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1){
                    linearLeibie.setVisibility(View.VISIBLE);
                    daibanDown1.setImageResource(R.mipmap.shaixuan_3);
                    flag = 2;
                }else{
                    linearLeibie.setVisibility(View.GONE);
                    daibanDown1.setImageResource(R.mipmap.daiban_down);
                    flag = 1;
                }
            }
        });

        //筛选弹出pop
        textChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupWindow_view = getLayoutInflater().inflate(R.layout.pop_shaixuan, null,
                        false);
                popupWindow = new PopupWindow(popupWindow_view, 360, 720, true);

                // 设置动画效果
                popupWindow.setAnimationStyle(R.style.AnimationFade);

                //点击其他地方消失
                popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }
                });
            }
        });
    }

}
