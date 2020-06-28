package com.lawe.starofadministration.adp;

import android.app.Dialog;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.MyApplication;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.JoinSpeedHistoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 15:02
 * description :  进度
 */
public class JoinSpeedAdapter extends BaseQuickAdapter<JoinSpeedHistoryBean.HistoryTaskMapBean.QicaoBean, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;
    private String personType;

    public JoinSpeedAdapter(int layoutResId, @Nullable List<JoinSpeedHistoryBean.HistoryTaskMapBean.QicaoBean> data) {
        super(layoutResId, data);
    }

    public JoinSpeedAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinSpeedHistoryBean.HistoryTaskMapBean.QicaoBean qicaoBean) {

        TextView liuchengTitle = helper.itemView.findViewById(R.id.liucheng_title);
        liuchengTitle.setTypeface(getTextMedium);
        ImageView liuchengHead = helper.itemView.findViewById(R.id.liucheng_head);
        TextView liuchengName = helper.itemView.findViewById(R.id.liucheng_name);
        TextView liuchengTime = helper.itemView.findViewById(R.id.liucheng_time);
        TextView liuchengContext = helper.itemView.findViewById(R.id.liucheng_context);
        TextView liucheng_yijiancuiban = helper.itemView.findViewById(R.id.liucheng_yijiancuiban);
        TextView liucheng_cuiban = helper.itemView.findViewById(R.id.liucheng_cuiban);

        //liuchengHead.setImageResource();
        liuchengTitle.setText(qicaoBean.getType());
        liuchengName.setText(qicaoBean.getUserId());
        liuchengTime.setText(qicaoBean.getTime());
        liuchengContext.setText(qicaoBean.getMessage());
        if (personType.equals("1")){  //创建人
            liucheng_yijiancuiban.setVisibility(View.VISIBLE);
        }else if(personType.equals("2")){  //执行者
            liucheng_yijiancuiban.setVisibility(View.GONE);
        }

       /* //督办
        liuchengDuban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), R.style.DialogTheme);
                View view = View.inflate(getContext(), R.layout.pop_duban, null);
                dialog.setContentView(view);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.main_menu_animStyle);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                ImageView loginDown = view.findViewById(R.id.login_down);
                loginDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

            }
        });

        //退办
        liuchengTuihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), R.style.DialogTheme);
                View view = View.inflate(getContext(), R.layout.pop_tuiban, null);
                dialog.setContentView(view);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.main_menu_animStyle);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                ImageView loginDown = view.findViewById(R.id.login_down);
                loginDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });*/
    }


}
