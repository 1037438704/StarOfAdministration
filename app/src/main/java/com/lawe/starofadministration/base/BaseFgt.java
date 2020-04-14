package com.lawe.starofadministration.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kongzue.baseframework.BaseFragment;

abstract public class BaseFgt extends BaseFragment {

    public AppCompatActivity fgtContext;

    @Override
    public void initViews() {
        fgtContext = (AppCompatActivity) getActivity();
    }
}
