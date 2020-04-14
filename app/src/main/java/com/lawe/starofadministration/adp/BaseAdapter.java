package com.lawe.starofadministration.adp;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:AbnerMing
 * date:2019/6/8
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    private Context context;
    private List<T> list = new ArrayList<>();

    public BaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = View.inflate(context, getLayoutId(), null);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder( BaseAdapter.BaseViewHolder baseViewHolder, int i) {
        createPosition(i);
        bindViewData(baseViewHolder, list.get(i));
        bindViewDataPosition(baseViewHolder, list.get(i), i);
    }

    protected void bindViewDataPosition(BaseViewHolder baseViewHolder, T t, int i) {

    }

    protected void createPosition(int i) {

    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        private SparseArray<View> sparseArray = new SparseArray<>();

        //用于获取控件的方法
        public View get(int id) {
            View view = sparseArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                sparseArray.put(id, view);
            }
            return view;
        }

        //给TextView 赋值
        public void setText(int id, String content) {
            TextView textView = (TextView) get(id);
            textView.setText(content);
        }

        public void setPic(int id, String url) {
            //这里写加载图片的逻辑
            //Glide  Fresco  pissca
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //传递数据
    public void setList(List<T> list){
        this.list=list;
        notifyDataSetChanged();
    }

    //获取数据
    public List<T> getList() {
        return list;
    }

    //子类向父类传递的layout
    public abstract int getLayoutId();

    //子类初始化数据
    protected abstract void bindViewData(BaseViewHolder baseViewHolder, T t);

}
