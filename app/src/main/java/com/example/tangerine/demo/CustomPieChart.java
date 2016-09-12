package com.example.tangerine.demo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;




/**
 * Created by Tangerine on 16/8/11.
 */
public class CustomPieChart extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private static String TAG = "123";
    private SurfaceHolder mHolder;
    private Canvas mCanvas       ;


    private        Paint paintCircle;
    private         Paint paintCircleAlpha;
    private         Paint paintPercentNum;
    private         Paint paintPercent;

    private     int paintCircleColor;
    private        int Type;
    private        int  viewWidth       ;
    private        Boolean isDrawn;
    public        static final int CPU = 0;
    public         static final int MEMORRY = 1;

    public         static final int NETSPEED_B = 2;
    public         static final int NETSPEED_K = 3;
    public         static final int NETSPEED_M = 4;


    private        float percent_value;
    private        float  value           ;
    private        float angelValue;
    private        float percentValue;
    public CustomPieChart(Context context) {
        super(context);
        init();
    }

    public CustomPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }





    /**
     *
     *
     */


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isDrawn = true;
        draw();



    }
    @Override
    public void surfaceChanged(final SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isDrawn = false;
    }


    public void valueChange(float toTranslationValue, int duration,int type) {
            percent_value = toTranslationValue;
            Type = type;

            if (Type == CPU) {
                percentValue = percent_value;
            } else if (Type == MEMORRY) {
                percentValue = Math.round(percent_value);

            } else if (Type == NETSPEED_B || Type == NETSPEED_K || Type == NETSPEED_M) {
            }

            ValueAnimator animator = ValueAnimator.ofFloat(value, toTranslationValue).setDuration(duration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    value = (float) animation.getAnimatedValue();
                    angelValue = (value / 100.0f) * 360.0f;
                    if (isDrawn)
                    draw();
                }
            });

            animator.start();
        }


    @Override
    public  void run() {
    }
    private void draw(){
        try {
            mCanvas = mHolder.lockCanvas();
            viewWidth = getWidth();
            viewWidth = getHeight();
            paintCircleAlpha.setStrokeWidth(viewWidth/50);
            paintCircle.setStrokeWidth(viewWidth/50);
            paintPercent.setTextSize(viewWidth /7);
            paintPercentNum.setTextSize(viewWidth *10/32);

            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);


            mCanvas.drawText("%", viewWidth /30*22, viewWidth /30*19,paintPercent);
            RectF rectF = new RectF(viewWidth/45, viewWidth /45,viewWidth-10, viewWidth -10);
            mCanvas.drawArc(rectF,0,360,false, paintCircleAlpha);
            mCanvas.drawArc(rectF,-90.0f, angelValue,false, paintCircle);
         /*个位数*/   if (NumberDigit(percentValue) == 1 || NumberDigit(percentValue) == 0 ) {
                if (Type  == MEMORRY)
                    mCanvas.drawText(Integer.toString((int) percentValue), viewWidth /10*4, viewWidth / 10*6, paintPercentNum);
                else if (Type == CPU)
                mCanvas.drawText(Float.toString(percentValue), viewWidth /10*3, viewWidth / 10*6, paintPercentNum);
            }
         /*十位数*/   else if (NumberDigit(percentValue)==2) {
                if (Type  == MEMORRY)
                    mCanvas.drawText(Integer.toString((int) percentValue), viewWidth /10*3, viewWidth /10*6, paintPercentNum);
                else if (Type == CPU)
                    mCanvas.drawText(Float.toString(percentValue), viewWidth /10*1, viewWidth /10*6, paintPercentNum);


            }
         /*百位数*/    else if (percentValue == 100) {
                if (Type  == MEMORRY)
                    mCanvas.drawText(Integer.toString((int) percentValue),  viewWidth /10 *2, viewWidth /10*6, paintPercentNum);
                else if (Type == CPU)
                    mCanvas.drawText(Integer.toString((int)percentValue), viewWidth /10 *2, viewWidth /10*6, paintPercentNum);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (mHolder!=null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }



    private void init(){


        mHolder = this.getHolder();
        mHolder.addCallback(this);


        mHolder.setSizeFromLayout();
        paintPercent = new Paint();
        paintCircleAlpha = new Paint();
        paintPercentNum = new Paint();
        paintCircle = new Paint();


        paintCircleAlpha.setColor(Color.WHITE);
        paintPercentNum.setColor(Color.WHITE);

        paintCircle.setColor(paintCircleColor);
        paintPercent.setColor(Color.WHITE);
        paintCircleAlpha.setAntiAlias(true);
        paintCircle.setAntiAlias(true);
        paintPercent.setAntiAlias(true);
        paintPercentNum.setAntiAlias(true);

        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircleAlpha.setStyle(Paint.Style.STROKE);

        paintCircleAlpha.setAlpha(20);

        value = 0 ;

        angelValue =( 1.0f/100.0f)*360.0f;


        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);

    }



    private static int NumberDigit(float n){
        int i  = (int)n;
        int count = 0;
        while (i!=0){
            i/=10;
            count++;
        }
        return  count;
    }

    public void setPaintCircleColor(int paintCircleColor) {
        this.paintCircleColor = paintCircleColor;
        paintCircle.setColor(paintCircleColor);
    }






}
