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

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/5/11 15:02
 * description :  进度
 */
public class JoinSpeedAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Typeface getTextMedium = MyApplication.getTextMedium;

    public JoinSpeedAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public JoinSpeedAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView liuchengTitle = helper.itemView.findViewById(R.id.liucheng_title);
        liuchengTitle.setTypeface(getTextMedium);
        ImageView liuchengHead = helper.itemView.findViewById(R.id.liucheng_head);
        TextView liuchengName = helper.itemView.findViewById(R.id.liucheng_name);
        TextView liuchengTime = helper.itemView.findViewById(R.id.liucheng_time);
        TextView liuchengContext = helper.itemView.findViewById(R.id.liucheng_context);
        LinearLayout liuchengCreat = helper.itemView.findViewById(R.id.liucheng_creat);
        TextView liuchengCreatCuiban = helper.itemView.findViewById(R.id.liucheng_creat_cuiban);
        TextView liuchengCreatChehui = helper.itemView.findViewById(R.id.liucheng_creat_chehui);
        LinearLayout liuchengQianshou = helper.itemView.findViewById(R.id.liucheng_qianshou);
        TextView liuchengDuban = helper.itemView.findViewById(R.id.liucheng_duban);
        TextView liuchengYuetan = helper.itemView.findViewById(R.id.liucheng_yuetan);
        TextView liuchengCuiban = helper.itemView.findViewById(R.id.liucheng_cuiban);
        LinearLayout liuchengBanli = helper.itemView.findViewById(R.id.liucheng_banli);
        TextView liuchengChehui = helper.itemView.findViewById(R.id.liucheng_chehui);
        LinearLayout liuchengBanjie = helper.itemView.findViewById(R.id.liucheng_banjie);
        TextView liuchengTuihui = helper.itemView.findViewById(R.id.liucheng_tuihui);

        //督办
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
        });
    }
}
