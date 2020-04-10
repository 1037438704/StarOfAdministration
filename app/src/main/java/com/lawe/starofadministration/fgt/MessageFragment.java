package com.lawe.starofadministration.fgt;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : dell
 * date : 2020/4/8 10:23
 * description :待办——待办信息
 */
@Layout(R.layout.fgt_message)
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
    private LinearLayout choose_leibie;
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
        choose_leibie = (LinearLayout) findViewById(R.id.choose_leibie);

        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);
        recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //类别选择
        choose_leibie.setOnClickListener(new View.OnClickListener() {
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
                toast(".....");
                View view = LayoutInflater.from(fgtContext).inflate(R.layout.pop_shaixuan, null, false);
               popupWindow = new PopupWindow(view, CoordinatorLayout.LayoutParams.MATCH_PARENT, 400, true);

                // 设置动画效果
                popupWindow.setAnimationStyle(R.style.AnimationFade);

                //点击其他地方消失
                view.setOnTouchListener(new View.OnTouchListener() {
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

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }
}
