package com.demo.mytest.View;

import android.content.Context;
import android.graphics.*;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Android on 2016/5/10.
 */
public class Canvas_1 extends View {

    Paint paint;
    public Canvas_1(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path=new Path();


        path.moveTo(10,10);
        path.lineTo(10,150);
        path.lineTo(250,150);
        path.lineTo(250,10);

        path.close();
        canvas.drawPath(path,paint);
        //canvas.drawRect(25,200,250,300,paint);
    }
}
