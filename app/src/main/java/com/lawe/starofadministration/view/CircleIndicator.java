package com.lawe.starofadministration.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.lawe.starofadministration.R;

public class CircleIndicator extends LinearLayout {

    private ViewPager mViewpager;
    private OnPageChangeListener onPageChangeListener;
    public CircleIndicator(Context context) {
        super(context);
        init();
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setGravity(Gravity.CENTER);
    }

    public void setViewPager(ViewPager viewPager) {
        mViewpager = viewPager;
        if (mViewpager != null && mViewpager.getAdapter() != null) {
            createIndicators();
            mViewpager.removeOnPageChangeListener(mInternalPageChangeListener);
            mViewpager.addOnPageChangeListener(mInternalPageChangeListener);
            mInternalPageChangeListener.onPageSelected(mViewpager.getCurrentItem());
        }
    }

    public void setViewPager(ViewPager viewPager, OnPageChangeListener onPageChangeListener) {
        mViewpager = viewPager;
        if (mViewpager != null && mViewpager.getAdapter() != null) {
            createIndicators();
            this.onPageChangeListener = onPageChangeListener;
            mViewpager.removeOnPageChangeListener(mInternalPageChangeListener);
            mViewpager.addOnPageChangeListener(mInternalPageChangeListener);
            mInternalPageChangeListener.onPageSelected(mViewpager.getCurrentItem());
        }
    }

    private final ViewPager.OnPageChangeListener mInternalPageChangeListener = new ViewPager.OnPageChangeListener() {

        boolean isScrolling = false;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position == getChildCount()-1 && positionOffset==0&&positionOffsetPixels==0&&isScrolling){
                isScrolling = false;
                if(onPageChangeListener!=null){
                    onPageChangeListener.onPageSelected(getChildCount());
                }
            }
        }

        @Override public void onPageSelected(int position) {

            if (mViewpager.getAdapter() == null || mViewpager.getAdapter().getCount() <= 0) {
                return;
            }

            for(int i=0;i<getChildCount();i++){
                TextView tv_Indicator = (TextView) getChildAt(i);
                tv_Indicator.setBackground(getResources().getDrawable(R.drawable.bg_circle_indicator_e3e5e7));
                if(i==position){
                    if(onPageChangeListener!=null){
                        onPageChangeListener.onPageSelected(position);
                    }
                    tv_Indicator.setBackground(getResources().getDrawable(R.drawable.bg_circle_indicator_db322b));
                }
            }
        }

        @Override public void onPageScrollStateChanged(int state) {
            if (state == 1) {
                isScrolling = true;
            } else {
                isScrolling = false;
            }
    }
    };

    /**
     * @deprecated User ViewPager addOnPageChangeListener
     */
    @Deprecated public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (mViewpager == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        mViewpager.removeOnPageChangeListener(onPageChangeListener);
        mViewpager.addOnPageChangeListener(onPageChangeListener);
    }

    private void createIndicators() {
        removeAllViews();
        int count = mViewpager.getAdapter().getCount();
        if (count <= 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            TextView tv_Indicator = new TextView(getContext());
            tv_Indicator.setGravity(Gravity.CENTER);
            tv_Indicator.setHeight(dip2px(6));
            tv_Indicator.setWidth(dip2px(6));
            if (i == 0) {
                tv_Indicator.setBackground(getResources().getDrawable(R.drawable.bg_circle_indicator_db322b));
            } else {
                tv_Indicator.setBackground(getResources().getDrawable(R.drawable.bg_circle_indicator_e3e5e7));
            }
            LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            layout.setMargins(dip2px(1),0,dip2px(1),0);
            tv_Indicator.setLayoutParams(layout);

            final int index = i;
            tv_Indicator.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewpager.setCurrentItem(index);
                }
            });

            addView(tv_Indicator);
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnPageChangeListener{
        void onPageSelected(int position);
    }
}
