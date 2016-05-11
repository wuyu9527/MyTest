package com.demo.mytest.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.mytest.MainActivity;
import com.demo.mytest.Model.Arr;
import com.demo.mytest.Model.My;

import java.util.ArrayList;

/**
 * Created by Android on 2016/5/10.
 */
public class Canvas extends View {

    Paint paint;
    public Canvas(Context context,AttributeSet set) {
        super(context,set);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(3);
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        paint.setColor(Color.RED);

//            int x = 10;
//            int y = 5 ;
//            for (int i = 0; i < 10; i++) {
//                canvas.drawRect(10,30,10,80,paint);//左上右下
//            }

        double x = 10.56;
        double y =9.56;


        canvas.drawColor(Color.BLACK);
        //canvas.drawRect(19 + 1 + 9, 0, 19 + 1 + 9 + 1, 200, paint);



        int mun= 20;//宽
        int num = 30;//根
        /**
         *
         * 10% = height*0.01
         * 高 400 宽 600 2:3
         * 
         *
          */
        if (x>y) {
            paint.setColor(Color.CYAN);
            for (int i = 0; i < num; i++) {
                canvas.drawRect(mun*i,200,mun*i+mun-1,260,paint);
                //canvas.drawRect(mun*i+mun/2-1,190,mun*i+mun/2,290,paint);
            }
        }


        if (x<y) {
            paint.setColor(Color.RED);
            canvas.drawRect(19 + 20 + 1, 80, 19 + 20 + 1 + 19, 130, paint);
        }
    }
}
