package com.demo.mytest;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.demo.mytest.Model.Arr;
import com.demo.mytest.Model.My;
import com.demo.mytest.Util.HttpApi;


import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends Activity {

    private My my;
    HttpApi httpApi;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        view =  findViewById(R.id.xian);
        httpApi = new HttpApi();
        gson(httpApi.gsonGet("http://192.168.0.111/mytestfor.php", new My()));



    }

    public void gson(Observable observable) {
        observable.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null && !o.equals("")) {
                    my = (My) o;
                    //onDraw(my.getArr());
                    view.invalidate();
                    view.postInvalidate();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    public void onDraw(ArrayList<Arr> arr) {

        double max = 0;
        double min = view.getHeight();
        int num = 30;//宽600 30根
        int mun = view.getWidth() / num;//高400 宽20
        double price = 1 / view.getHeight();//百分比


        Bitmap newb = Bitmap.createBitmap(view.getHeight(), view.getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newb);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();

        for (int i = 0; i < arr.size(); i++) {

            double ax = Double.parseDouble(arr.get(i).getHigh());
            if (ax > max) {
                max = ax;
            }

            double in = Double.parseDouble(arr.get(i).getLow());
            if (in < min) {
                min = in;
            }
        }
        for (int i = 0; i < num; i++) {
            double open = Double.parseDouble(arr.get(i).getOpen());
            double close = Double.parseDouble(arr.get(i).getClose());
            if (open > close) {
                paint.setColor(Color.CYAN);
                canvas.drawRect(mun * i, view.getHeight()-Float.parseFloat(arr.get(i).getOpen()), mun * i + mun - 1, view.getHeight()-Float.parseFloat(arr.get(i).getClose()), paint);
                canvas.drawRect(mun * i + mun / 2 - 1, view.getHeight()-Float.parseFloat(arr.get(i).getHigh()), mun * i + mun / 2, view.getHeight()-Float.parseFloat(arr.get(i).getLow()), paint);
            }
            if (open < close) {
                paint.setColor(Color.RED);
                canvas.drawRect(mun * i, view.getHeight()-Float.parseFloat(arr.get(i).getOpen()), mun * i + mun - 1, view.getHeight()-Float.parseFloat(arr.get(i).getClose()), paint);
                canvas.drawRect(mun * i + mun / 2 - 1, view.getHeight()-Float.parseFloat(arr.get(i).getHigh()), mun * i + mun / 2, view.getHeight()-Float.parseFloat(arr.get(i).getLow()), paint);
            }
            if (open == close) {
                paint.setColor(Color.WHITE);
                canvas.drawRect(mun * i, view.getHeight()-Float.parseFloat(arr.get(i).getOpen()), mun * i + mun - 1, view.getHeight()-Float.parseFloat(arr.get(i).getClose()), paint);
                canvas.drawRect(mun * i + mun / 2 - 1, view.getHeight()-Float.parseFloat(arr.get(i).getHigh()), mun * i + mun / 2, view.getHeight()-Float.parseFloat(arr.get(i).getLow()), paint);
            }

        }

        view.invalidate();
    }

}






