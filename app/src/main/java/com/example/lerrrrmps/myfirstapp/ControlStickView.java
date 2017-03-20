package com.example.lerrrrmps.myfirstapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class ControlStickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;

    public ControlStickView(Context context){
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public ControlStickView(Context context, AttributeSet attributes, int style){
        super(context,attributes,style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public ControlStickView(Context context, AttributeSet attributes){
        super(context, attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        setupDimensions();
        drawJoystick(centerX,centerY);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int forsat, int width, int height){
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
    }

    private void setupDimensions(){
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    private void drawJoystick(float newX, float newY){
        int alpha = 255;
        int red = 255;
        int blue = 255;
        int green = 255;
        if(getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            colors.setARGB(alpha, red, green, blue);

            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
            colors.setARGB(255,0,155,0);
            myCanvas.drawCircle(newX, newY, hatRadius, colors);

            getHolder().unlockCanvasAndPost(myCanvas);

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent myEvent){
        if (view.equals(this)) {
            if (myEvent.getAction() != myEvent.ACTION_UP) {
                float displacement = (float) Math.sqrt(Math.pow(myEvent.getX() - centerX, 2)+ Math.pow(myEvent.getY() - centerY,2));
                if (displacement < baseRadius)
                    drawJoystick(centerX, myEvent.getY());
                else{
                    float ratio = baseRadius/displacement;
                    float constrainedX = centerX + (myEvent.getX()-centerX)*ratio;
                    float constrainedY = centerY + (myEvent.getY()-centerY)*ratio;
                    drawJoystick(centerX,constrainedY);
                }
            } else {
                drawJoystick(centerX, centerY);
            }
        }
        return true;
    }
}
