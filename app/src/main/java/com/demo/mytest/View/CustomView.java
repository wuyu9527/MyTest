package com.demo.mytest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Android on 2016/5/9.
 */
public class CustomView extends View {
    Paint paint;



    public CustomView(Context context, AttributeSet set) {

        super(context,set);

        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔

        paint.setColor(Color.GREEN);//画笔颜色

        paint.setStrokeJoin(Paint.Join.ROUND);

        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 把整张画布绘制成白色
        canvas.drawColor(Color.BLACK);
        // 去锯齿
        paint.setAntiAlias(true);
        canvas.drawCircle(400, 400, 150, paint);//画圆，坐标x，坐标y，半径，画笔
        // 绘制圆形
        canvas.drawCircle(40, 40, 30, paint);
        // 绘制正方形
        canvas.drawRect(10, 80, 70, 140, paint);//左上右下
        // 绘制矩形
        canvas.drawRect(10, 150, 70, 190, paint);

        // 绘制圆角矩形
        RectF re1 = new RectF(20, 210, 80, 240);//四面坐标
        canvas.drawRoundRect(re1, 20, 20, paint);

        // 绘制椭圆
        RectF re11 = new RectF(10, 240, 70, 270);
        canvas.drawOval(re11, paint);


        // 定义一个Path对象，封闭成一个三角形。
        Path path1 = new Path();
        path1.moveTo(10, 340);
        path1.lineTo(70, 340);
        path1.lineTo(40, 290);
        path1.close();
        // 根据Path进行绘制，绘制三角形
        canvas.drawPath(path1, paint);//根据路径绘制

        // 定义一个Path对象，封闭成一个五角形。
        Path path2 = new Path();
        path2.moveTo(26, 360);
        path2.lineTo(54, 360);
        path2.lineTo(70, 392);
        path2.lineTo(40, 420);
        path2.lineTo(10, 392);
        path2.close();
        // 根据Path进行绘制，绘制五角形
        canvas.drawPath(path2, paint);

        // ----------设置填充风格后绘制----------
        paint.setStyle(Paint.Style.FILL);//填充
        paint.setColor(Color.RED);//颜色
        canvas.drawCircle(120, 40, 30, paint);
        // 绘制正方形
        canvas.drawRect(90, 80, 150, 140, paint);//左上右下
        // 绘制矩形
        canvas.drawRect(90, 150, 150, 190, paint);//左上右下
        RectF re2 = new RectF(90, 200, 150, 230);
        // 绘制圆角矩形
        canvas.drawRoundRect(re2, 15, 15, paint);
        RectF re21 = new RectF(90, 240, 150, 270);
        // 绘制椭圆
        canvas.drawOval(re21, paint);
        Path path3 = new Path();
        path3.moveTo(90, 340);
        path3.lineTo(150, 340);
        path3.lineTo(120, 290);
        path3.close();
        // 绘制三角形
        canvas.drawPath(path3, paint);
        Path path4 = new Path();
        path4.moveTo(106, 360);
        path4.lineTo(134, 360);
        path4.lineTo(150, 392);
        path4.lineTo(120, 420);
        path4.lineTo(90, 392);
        path4.close();
        // 绘制五角形
        canvas.drawPath(path4, paint);

        // 为Paint设置渐变器
        Shader mShader = new LinearGradient(0, 0, 40, 60, new int[] {
                Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }, null,
                Shader.TileMode.REPEAT);
        paint.setShader(mShader);
        // 设置阴影
        paint.setShadowLayer(45, 10, 10, Color.GRAY);
        // 绘制圆形
        canvas.drawCircle(200, 40, 30, paint);


    }
}
