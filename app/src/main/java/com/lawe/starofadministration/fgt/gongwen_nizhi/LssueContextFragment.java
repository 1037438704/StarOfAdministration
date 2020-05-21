package com.lawe.starofadministration.fgt.gongwen_nizhi;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.Layout;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.base.BaseFgt;

/**
 * author : fuke
 * date : 2020/5/12 14:08
 * description : 公文签发----公文内容
 */
@Layout(R.layout.fgt_join_context)
public class LssueContextFragment extends BaseFgt {

    private TextView joinContextTitle;
    private TextView joinContextTongzhi;
    private TextView joinContextGongkai;
    private TextView joinContextQicaoren;
    private TextView joinContextHuiqian;
    private TextView joinContextNumber;
    private TextView joinContextSubject;
    private LinearLayout cebianlan;
    private ImageView imgBig;
    private ImageView imgSmall;
    private ImageView imgTop;
    private ImageView imgRead;
    private ImageView imgBohui;

    @Override
    public void initViews() {
        joinContextTitle = (TextView) findViewById(R.id.join_context_title);
        joinContextTongzhi = (TextView) findViewById(R.id.join_context_tongzhi);
        joinContextGongkai = (TextView) findViewById(R.id.join_context_gongkai);
        joinContextQicaoren = (TextView) findViewById(R.id.join_context_qicaoren);
        joinContextHuiqian = (TextView) findViewById(R.id.join_context_huiqian);
        joinContextNumber = (TextView) findViewById(R.id.join_context_number);
        joinContextSubject = (TextView) findViewById(R.id.join_context_subject);
        cebianlan = (LinearLayout) findViewById(R.id.cebianlan);
        imgBig = (ImageView) findViewById(R.id.img_big);
        imgSmall = (ImageView) findViewById(R.id.img_small);
        imgTop = (ImageView) findViewById(R.id.img_top);
        imgRead = (ImageView) findViewById(R.id.img_read);
        imgBohui = (ImageView) findViewById(R.id.img_bohui);

        cebianlan.setVisibility(View.VISIBLE);
        imgBohui.setVisibility(View.VISIBLE);
        imgRead.setVisibility(View.VISIBLE);

        //设置字体
        joinContextTitle.setTypeface(getTextMedium);
        joinContextSubject.setText("父父父父父父仿佛u父父父父父父父父语雀是一款优雅高效的在线文档编辑与协同工具， 让每个企业轻松拥有文档中心阿里巴巴集团内部使用多年，众多中小企业首选。 语雀是一款优雅高效的在线文档编辑与协同工具， 让每个企业轻松拥有文档中心阿里巴巴集团内部使用多年，众多中小企业首选。语雀是一款优雅高效的在线文档编辑与协同工具， 让每个企业轻松拥有文档中心阿里巴巴集团内部使用多年，众多中小企业首选。 语雀是一款优雅高效的在线文档编辑与协同工具， 让每个企业轻松拥有文档中心阿里巴巴集团内部使用多年 众多中小企业首选。语雀是一款优雅高效的在线文档编辑与协同工具， 让每个企业轻松拥有文档中心阿里巴巴集团内部使用多年，众多中小企业首选。");
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //立即驳回
        imgBohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用Dialog、设置style
                final Dialog dialog = new Dialog(me, R.style.DialogTheme);
                //2、设置布局
                View view = View.inflate(me, R.layout.pop_bohui, null);
                dialog.setContentView(view);

                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                //设置弹出动画
                window.setWindowAnimations(R.style.main_menu_animStyle);
                //设置对话框大小
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
    }

    public static LssueContextFragment newInstance() {
        return new LssueContextFragment();
    }
}