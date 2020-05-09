package com.lawe.starofadministration.adp;

import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<Fragment> list;
    public ViewPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public boolean isViewFromObject(View view,  Object o) {
        return view==o;
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        //container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
        //container.removeView(list.get(position));
    }
}
