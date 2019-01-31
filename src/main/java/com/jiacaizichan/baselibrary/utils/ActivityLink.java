package com.jiacaizichan.baselibrary.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * 创建日期：2018/7/24 11:30
 * @author lihao
 * decs： TODO
 */
public class ActivityLink {
    public static ArrayList<Activity> activities = new ArrayList<>();
    private static ActivityLink activityLink;

    public static ActivityLink getInstence() {
        if (activityLink == null) {
            synchronized (ActivityLink.class) {
                if (activityLink == null) {
                    activityLink = new ActivityLink();
                }
            }
        }
        return activityLink;
    }


    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void removeAll(){
        activities.clear();
    }

    public static void finishAll(){
//        removeAll();
        for(Activity activity:activities){
            activity.finish();
        }
    }
}
