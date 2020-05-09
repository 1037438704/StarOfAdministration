package com.lawe.starofadministration.adp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.bean.ListChatBean;

import java.util.List;

/**
 * author :
 * date : 2020/4/13 15:15
 * description : 常用语列表
 */
public class DraftChatAdapter extends BaseQuickAdapter<ListChatBean, BaseViewHolder> {


    public DraftChatAdapter(int layoutResId, @Nullable List<ListChatBean> data) {
        super(layoutResId, data);
    }

    public DraftChatAdapter(int item_message_layout) {
        super(item_message_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListChatBean item) {

        ImageView itemDraftBianJi = helper.itemView.findViewById(R.id.item_draft_bianji);
        ImageView itemDraftDelete = helper.itemView.findViewById(R.id.item_draft_delete);
        if (item.getDisplay()){
            itemDraftBianJi.setVisibility(View.VISIBLE);
            itemDraftDelete.setVisibility(View.VISIBLE);
        }else{
            itemDraftBianJi.setVisibility(View.GONE);
            itemDraftDelete.setVisibility(View.GONE);
        }


        //messageTitle.setTypeface(getTextMedium);

      /*   = (TextView) item.findViewById();
        //设置字体
        ;*/
    }
}
