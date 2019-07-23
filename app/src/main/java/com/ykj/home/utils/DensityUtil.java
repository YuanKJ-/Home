package com.ykj.home.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.ykj.home.app.MainApplication;

public class DensityUtil {

    private static DisplayMetrics metric = new DisplayMetrics();

    public static int dip2px(float dpValue) {
        Context context = MainApplication.getInstance().getContext();
        float scale = 0f;
        if (context != null && context.getResources() != null && context.getResources().getDisplayMetrics() != null) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = MainApplication.getInstance().getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float dpValue) {
        final float scale = MainApplication.getInstance().getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = MainApplication.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}