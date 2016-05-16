package com.demo.mytest.View;

import android.content.Context;
import android.graphics.*;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.demo.mytest.Model.Arr;
import com.demo.mytest.Model.My;
import com.demo.mytest.Util.HttpApi;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Android on 2016/5/12.
 */
public class MyImageView extends View{

    int width;
    int height;
    Paint paint;

    public MyImageView(Context context) {
        super(context);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // delay some minutes you desire.
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
                handler.post(new Runnable() {
                    public void run() {
                        invalidate();
                    }
                });
            }
        }).start();
    }



    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setVisibility(GONE);
        HttpApi httpApi=new HttpApi();
        gson(httpApi.gsonGet("http://192.168.0.111/mytestfor.php", new My()));
        paint = new Paint();
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(3);
        setWillNotDraw(false);
    }

    My my;
    ArrayList<Arr> arr;

    public Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("whx","刷新");
            invalidate();
        }
    };

    public void gson(Observable observable) {
        observable.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null && !o.equals("")) {
                    my = (My) o;
                    arr=my.getArr();
                    myhandler.sendEmptyMessage(0);
                    setVisibility(VISIBLE);
                    invalidate();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    int a = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("whx",a+++":");
        if (my!=null) {
            Log.i("whx","进入");
            double max = 0;
            double min = height;
            int num = 30;//宽600 30根
            int mun = width / num;//高400 宽20
            double price = 1 / height;//百分比

            canvas.drawColor(Color.BLACK);


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
                    canvas.drawRect(mun * i, height - Float.parseFloat(arr.get(i).getOpen()), mun * i + mun - 1, height - Float.parseFloat(arr.get(i).getClose()), paint);
                    canvas.drawRect(mun * i + mun / 2 - 1, height - Float.parseFloat(arr.get(i).getHigh()), mun * i + mun / 2, height - Float.parseFloat(arr.get(i).getLow()), paint);
                }
                if (open < close) {
                    paint.setColor(Color.RED);
                    canvas.drawRect(mun * i, height - Float.parseFloat(arr.get(i).getOpen()), mun * i + mun - 1, height - Float.parseFloat(arr.get(i).getClose()), paint);
                    canvas.drawRect(mun * i + mun / 2 - 1, height - Float.parseFloat(arr.get(i).getHigh()), mun * i + mun / 2, height - Float.parseFloat(arr.get(i).getLow()), paint);
                }
                if (open == close) {
                    paint.setColor(Color.WHITE);
                    canvas.drawRect(mun * i, height - Float.parseFloat(arr.get(i).getOpen()), mun * i + mun - 1, height - Float.parseFloat(arr.get(i).getClose()), paint);
                    canvas.drawRect(mun * i + mun / 2 - 1, height - Float.parseFloat(arr.get(i).getHigh()), mun * i + mun / 2, height - Float.parseFloat(arr.get(i).getLow()), paint);
                }

            }
        }
    }
}
