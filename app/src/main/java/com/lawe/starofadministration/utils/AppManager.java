package com.lawe.starofadministration.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/10
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: ActivityManager
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * @return 获取activity管理实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        Activity temp = null;
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                temp = activity;
            }
        }
        if (temp != null) {
            temp.finish();
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        if (activityStack != null) {
            while (!activityStack.empty()) {
                Activity activity = currentActivity();
                killActivity(activity);
            }
            activityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    @SuppressLint("MissingPermission")
    public void AppExit(Context context) {
        try {
            killAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public void deleteActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }
}
