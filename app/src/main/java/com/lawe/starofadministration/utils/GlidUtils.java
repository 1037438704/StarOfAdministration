package com.lawe.starofadministration.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lawe.starofadministration.R;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class GlidUtils {
    public static void circular(Context me, String url, ImageView imageView) {
        Glide
                .with(me)
                .load(url)
                .apply(new RequestOptions().circleCrop().error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    public static void circularGaoSi(Context me, String url, ImageView imageView) {
        Glide
                .with(me)
                .load(url)
                .apply(new RequestOptions().circleCrop().error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    public static void defaultGlid(Context me, String url, ImageView imageView) {
        Glide
                .with(me)
                .load(url)
                .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    public static void defaultGlid2(Context me, String url, ImageView imageView) {
        RequestOptions options = bitmapTransform(new CenterCropRoundCornerTransform(20));
        Glide.with(me)
                .load(url)
                .apply(options)
                .into(imageView);
    }

}
