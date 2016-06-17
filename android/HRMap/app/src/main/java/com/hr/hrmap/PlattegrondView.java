package com.hr.hrmap;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class PlattegrondView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    List<Locatie> locaties = new ArrayList<>();

    public PlattegrondView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initLocaties();
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

    }

    private void initLocaties()
    {

        this.locaties.add(new Locatie(1325, 180, "lift")); //lift

        this.locaties.add(new Locatie(400, 400, "m1")); //m1
        this.locaties.add(new Locatie(600, 400, "m2")); //m2
        this.locaties.add(new Locatie(800, 400, "m3")); //m3
        this.locaties.add(new Locatie(1000, 400, "m4")); //m4

        this.locaties.add(new Locatie(600, 200, "H.1.110", 30)); //H.1.110
        this.locaties.add(new Locatie(750, 200, "H.1.112", 30)); //H.1.112
        this.locaties.add(new Locatie(900, 200, "H.1.114", 30)); //H.1.114

        this.locaties.add(new Locatie(300, 525, "H.1.403", 30)); //H.1.403
        this.locaties.add(new Locatie(400, 500, "H.1.319", 30)); //H.1.319
        this.locaties.add(new Locatie(500, 525, "H.1.318", 30)); //H.1.318
        this.locaties.add(new Locatie(700, 525, "H.1.315", 30)); //H.1.315
        this.locaties.add(new Locatie(900, 525, "H.1.312", 30)); //H.1.312
        this.locaties.add(new Locatie(1100, 525, "H.1.306", 30)); //H.1.306

        this.locaties.add(new Locatie(1400, 525, "H.1.206", 30)); //H.1.206
        this.locaties.add(new Locatie(1400, 400, "H.1.206", 30)); //H.1.204


    }

    public PlattegrondView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, 5, paint);

            // Log.d(TAG, "Painting: "+point);
        }

        for (Locatie locatie : locaties) {
            drawLocatie(canvas, locatie);
        }

        Log.d(TAG, "point: drawing lines...");
        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);
    }

    private void drawLocatie(Canvas canvas, Locatie locatie)
    {
        canvas.drawCircle(locatie.x, locatie.y, 10, locatie.paint);
        canvas.drawText(locatie.naam, locatie.x - 50 ,locatie.y - 20, locatie.paint);
    }

    public boolean onTouch(View view, MotionEvent event) {
        return true;
        // if(event.getAction() != MotionEvent.ACTION_DOWN)
        // return super.onTouchEvent(event);
//        Point point = new Point();
//        point.x = event.getX();
//        point.y = event.getY();
//        points.add(point);
//        invalidate();
//        Log.d(TAG, "point: " + point);
//
//        return true;
    }


    class Point {
        float x, y;

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }

}