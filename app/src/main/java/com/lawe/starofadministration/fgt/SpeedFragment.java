package com.lawe.starofadministration.fgt;

import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.SpeedAdapter;
import com.lawe.starofadministration.base.BaseFgt;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/4/7 16:36
 * description :  进度追踪
 */
@Layout(R.layout.fgt_speed)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class SpeedFragment extends BaseFgt {

    protected static final float FLIP_DISTANCE = 50;
    GestureDetector mDetector;
    private LinearLayout textChoose;
    private DrawerLayout drawerLayout;
    private Button drawer_quxiao;
    private ImageView speedHead;
    private LinearLayout chooseLeibie;
    private RecyclerView recycleSpeed;
    private RecyclerView recyclerLeibie;
    private ImageView speedImgLeibie;
    private ImageView speedImgJinji;
    private LinearLayout linearLeibie;
    LinearLayout speedLinerarLayout;
    private int flag = 1; //类别默认标识

    //空集合
    private List<String> list;
    private SpeedAdapter speedAdapter;

    @Override
    public void initViews() {
        super.initViews();
        ((MainActivity) this.getActivity()).registerFragmentTouchListener(fragmentTouchListener);

        textChoose = (LinearLayout) findViewById(R.id.text_choose);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_shaixuan);
        drawer_quxiao = getActivity().findViewById(R.id.drawer_quxiao);
        speedHead = (ImageView) findViewById(R.id.speed_head);

        chooseLeibie = (LinearLayout) findViewById(R.id.choose_leibie);
        recycleSpeed = (RecyclerView) findViewById(R.id.recycle_speed);
        //recyclerLeibie = (RecyclerView) findViewById(R.id.recycler_leibie);

        speedImgLeibie = (ImageView) findViewById(R.id.speed_img_leibie);
        speedImgJinji = (ImageView) findViewById(R.id.speed_img_jinji);
        linearLeibie = (LinearLayout) findViewById(R.id.linear_leibie);

        speedLinerarLayout = (LinearLayout) findViewById(R.id.speed_linerar_layout);
        speedLinerarLayout.setPadding(0,me.getStatusBarHeight(),0,0);

        list = new ArrayList<>();
        //进度信息
        recycleSpeed.setLayoutManager(new LinearLayoutManager(me));
        speedAdapter = new SpeedAdapter(R.layout.item_message_layout);
        recycleSpeed.setAdapter(speedAdapter);

    }

    @Override
    public void initDatas() {
        //进度列表
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        speedAdapter.setNewData(list);
        speedAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        gestrueListener();

        //筛选弹出pop
        textChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        //类别选择
        chooseLeibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    linearLeibie.setVisibility(View.VISIBLE);
                    speedImgLeibie.setImageResource(R.mipmap.shaixuan_3);
                    flag = 2;
                } else {
                    linearLeibie.setVisibility(View.GONE);
                    speedImgLeibie.setImageResource(R.mipmap.daiban_down);
                    flag = 1;
                }
            }
        });

        //侧滑栏取消按钮
        drawer_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        //点击头像滑出左侧菜单栏
        speedHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    /*
     * 获取手势
     * */
    private void gestrueListener() {
        mDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向左滑...");
                    return true;
                }
                if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向右滑...");
                    return true;
                }
                if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向上滑...");
                    toast("向上向上");
                    //fastblur(fgtContext,R.mipmap.maoboli_bai,0);
                    return true;
                }
                if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向下滑...");
                    return true;
                }

                Log.d("TAG", e2.getX() + " " + e2.getY());

                return false;
            }
        });
    }

    MainActivity.FragmentTouchListener fragmentTouchListener = new MainActivity.FragmentTouchListener() {
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return mDetector.onTouchEvent(event);
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) this.getActivity()).unRegisterFragmentTouchListener(fragmentTouchListener);
    }

    public static SpeedFragment newInstance() {
        return new SpeedFragment();
    }

    private void initView() {

    }
}
