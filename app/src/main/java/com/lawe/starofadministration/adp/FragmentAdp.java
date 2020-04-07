package com.lawe.starofadministration.adp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawe.starofadministration.R;

import java.util.List;

/**
 * author : dell
 * date : 2020/4/7 9:35
 * description :
 */
public class FragmentAdp extends BaseAdapter {

    Context context;
    List list;
    public FragmentAdp(Context context, List list) {
// TODO Auto-generated constructor stub
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView==null){
            //convertView=View.inflate(context, R.layout.list_layout, null);
            holder=new ViewHolder();
            //holder.l_text=(TextView) convertView.findViewById(R.id.list_text);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        //holder.l_text.setText(list.get(position).getList_text());
        return convertView;
    }

    private class ViewHolder{
        TextView l_text;
    }
}
