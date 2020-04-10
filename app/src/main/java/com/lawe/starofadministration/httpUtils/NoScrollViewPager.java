package com.lawe.starofadministration.httpUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by xxm on 2017/10/31.
 * 是否禁止滑动与滑动动画
 */

public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = false;
    private boolean noScrollAnim = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否能左右滑动
     * @param noScroll true 不能滑动
     */
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    /**
     * 设置没有滑动动画
     * @param noAnim false 无动画
     */
    public void setScrollAnim(boolean noAnim){
        this.noScrollAnim = noAnim;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return !noScroll && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return !noScroll && super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,noScrollAnim);
    }
}