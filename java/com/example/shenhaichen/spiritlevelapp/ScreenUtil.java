package com.example.shenhaichen.spiritlevelapp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by shenhaichen on 09/05/2017.
 */

public class ScreenUtil {

    public ScreenUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     *  get the width of the screen, in order to draw the rectangle
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     *  get the height of screen, in order to draw the rectangle
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
