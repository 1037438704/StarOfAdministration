package com.lawe.starofadministration.adp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/6/11 15:02
 * description :  目录
 */
public class WorkSpeedAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    private List<String> list;
    private RecyclerView item_work_recycle;
    private int maxLine = 2;
    private SpannableString elipseString;//收起的文字
    private SpannableString notElipseString;//展开的文字

    public WorkSpeedAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public WorkSpeedAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        LinearLayout item_work_linear = helper.itemView.findViewById(R.id.item_work_linear);
        ImageView liuchengHead = helper.itemView.findViewById(R.id.liucheng_head);
        TextView liuchengName = helper.itemView.findViewById(R.id.liucheng_name);
        TextView liuchengTime = helper.itemView.findViewById(R.id.liucheng_time);
        TextView liuchengContext = helper.itemView.findViewById(R.id.liucheng_context);
        String count = "的合唱比賽第三次不死U盾艹死u十点半才个isU币嘎嘎对于沙河v歘uv对错案大V从来说不出的斯贝茨都改的说不出来是卡都不u好的VB爱的很不错啊";
        CheckBox itemChooseMyAll = helper.itemView.findViewById(R.id.item_choose_myAll);
        getLastIndexForLimit(liuchengContext,maxLine,count);
        item_work_recycle = helper.itemView.findViewById(R.id.item_work_recycle);
        liuchengContext.setTypeface(getTextMedium);
        item_work_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"ccc",Toast.LENGTH_SHORT).show();
                item_work_recycle.setVisibility(View.VISIBLE);
                getDatas();
            }
        });
    }

    private void getDatas() {
        //空集合---二级列表
        list = new ArrayList<>();
        item_work_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        WorkSpeedAdapter workSpeedAdapter = new WorkSpeedAdapter(R.layout.item_work_two_list);
        item_work_recycle.setAdapter(workSpeedAdapter);

        //进度列表
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        workSpeedAdapter.setNewData(list);
    }

    //展开全部----收起标题
    private void getLastIndexForLimit(TextView tv, int maxLine, String content) {
        //获取TextView的画笔对象
        TextPaint paint = tv.getPaint();
        //每行文本的布局宽度
        int width =getContext().getResources().getDisplayMetrics().widthPixels - dip2px(getContext(),80);
        //实例化StaticLayout 传入相应参数
        StaticLayout staticLayout = new StaticLayout(content,paint,width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        //判断content是行数是否超过最大限制行数2行
        if (staticLayout.getLineCount()>maxLine) {
            //定义展开后的文本内容
            String string1 = content + "    收起";
            notElipseString = new SpannableString(string1);
            //给收起两个字设成灰色
            notElipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#CACACC")), string1.length() - 2, string1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //获取到第er行最后一个文字的下标
            int index = staticLayout.getLineStart(maxLine) - 1;
            //定义收起后的文本内容
            String substring = content.substring(0, index - 4) + "..." + "查看全部";
            elipseString = new SpannableString(substring);
            //给查看全部设成灰色
            elipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#CACACC")), substring.length() - 4, substring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置收起后的文本内容
            tv.setText(elipseString);
            //tv.setOnClickListener(getContext());
            //将textview设成选中状态 true用来表示文本未展示完全的状态,false表示完全展示状态，用于点击时的判断
            tv.setSelected(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"111",Toast.LENGTH_SHORT).show();
                    if (v.isSelected()) {
                        //如果是收起的状态
                        tv.setText(notElipseString);
                        tv.setSelected(false);
                    } else {
                        //如果是展开的状态
                        tv.setText(elipseString);
                        tv.setSelected(true);
                    }
                }
            });

        } else {
            //没有超过 直接设置文本
            tv.setText(content);
            tv.setOnClickListener(null);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
