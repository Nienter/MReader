package com.yuanchuangli.mreader.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Blank
 * @description ActivityCollector 类的管理器
 * @time 2016/11/24 18:48
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities
                ) {
            if (!activity.isFinishing()) {
                activity.finish();
            }

        }
    }
}
