package com.example.shenhaichen.spiritlevelapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shenhaichen on 09/05/2017.
 */

public class SpiritView extends View {

    private float cycleX;
    private float cycleY;
    private int screenWidth, screenHeight;
    private Paint horizonalPaint;
    private Bitmap mBitmap;

    public SpiritView(Context context) {
        super(context);
    }

    public SpiritView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //        screenWidth = ScreenUtil.getScreenWidth(context);
//        screenHeight = ScreenUtil.getScreenHeight(context);
        init();
    }

    public SpiritView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void init() {
        horizonalPaint = new Paint();
        horizonalPaint.setAntiAlias(true);
        horizonalPaint.setColor(Color.BLUE);
        horizonalPaint.setDither(true);
        horizonalPaint.setStrokeWidth(5);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float x = getWidth()/2;
        float y = getHeight()/2;

        x += cycleX;

        if (x < 0){
            x = 0;
        }else if ( x > getWidth()){
            x = getWidth();
        }
        canvas.drawCircle(x,y,50,horizonalPaint);

    }

    public void setCycleX(float cycleX) {
        this.cycleX = cycleX;
        invalidate();
    }

    public void setCycleY(float cycleY) {
        this.cycleY = cycleY;
    }
}
