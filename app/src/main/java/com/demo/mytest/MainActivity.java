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
    ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        httpApi=new HttpApi();
        gson(httpApi.gsonGet("http://192.168.0.111/mytestfor.php",new My()));
        view= (ImageView) findViewById(R.id.xian);




    }

    public void gson(Observable observable){
        observable.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null && !o.equals("")) {
                    my = (My) o;
                    onDraw(my,my.getArr());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    public void onDraw(My my, ArrayList<Arr> arr){
        int x = 10;
        int y = 5;
        Bitmap newb = Bitmap.createBitmap( 400, 500, Bitmap.Config.ARGB_8888 );
        Canvas canvas = new Canvas(newb);
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.BOLD);
        p.setColor(Color.RED);
        p.setTypeface(font);
        p.setTextSize(20);
        for (int i = 0; i <arr.size() ; i++) {
            canvas.drawRect(y*i,10,x*i,50,p);
        }
        Drawable drawable = new BitmapDrawable(newb);
        view.setBackground(drawable);
    }

}






