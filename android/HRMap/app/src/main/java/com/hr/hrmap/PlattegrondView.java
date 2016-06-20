package com.hr.hrmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import static java.lang.Math.*;

public class PlattegrondView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    List<Locatie> locaties = new ArrayList<>();
    UndirectedGraph<Locatie, DefaultEdge> g =  new SimpleGraph<Locatie, DefaultEdge>(DefaultEdge.class);
    Canvas canvas;

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
        Locatie lift = new Locatie(1325, 180, "lift");
        this.locaties.add(lift); //lift
        this.g.addVertex(lift);

        Locatie m1 = new Locatie(1000, 400, "m1");
        this.locaties.add(m1); //m1
        this.g.addVertex(m1);
        this.g.addEdge(lift, m1);

        Locatie m2 = new Locatie(800, 400, "m2");
        this.locaties.add(m2); //m2
        this.g.addVertex(m2);
        this.g.addEdge(m1, m2);

        Locatie m3 = new Locatie(600, 400, "m3");
        this.locaties.add(m3); //m3
        this.g.addVertex(m3);
        this.g.addEdge(m2, m3);


        Locatie m4 = new Locatie(400, 400, "m4");
        this.locaties.add(m4); //m1
        this.g.addVertex(m4);
        this.g.addEdge(m3, m4);





        Locatie h1_110 = new Locatie(600, 200, "H.1.110", 30);
        this.locaties.add(h1_110); //H.1.110
        this.g.addVertex(h1_110);
        this.g.addEdge(m3, h1_110);

        Locatie h1_112 = new Locatie(750, 200, "H.1.112", 30);
        this.locaties.add(h1_112); //H.1.112
        this.g.addVertex(h1_112);
        this.g.addEdge(m2, h1_112);

        Locatie h1_114 = new Locatie(900, 200, "H.1.114", 30);
        this.locaties.add(h1_114); //H.1.114
        this.g.addVertex(h1_114);
        this.g.addEdge(m1, h1_114);

        Locatie h1_403 = new Locatie(300, 525, "H.1.403", 30);
        this.locaties.add(h1_403); //H.1.403
        this.g.addVertex(h1_403);
        this.g.addEdge(m4, h1_403);

        Locatie h1_319 = new Locatie(400, 500, "H.1.319", 30);
        this.locaties.add(h1_319); //H.1.319
        this.g.addVertex(h1_319);
        this.g.addEdge(m4, h1_319);

        Locatie h1_318 = new Locatie(500, 525, "H.1.318", 30);
        this.locaties.add(h1_318); //H.1.318
        this.g.addVertex(h1_318);
        this.g.addEdge(m3, h1_318);

        Locatie h1_315 = new Locatie(700, 525, "H.1.315", 30);
        this.locaties.add(h1_315); //H.1.315
        this.g.addVertex(h1_315);
        this.g.addEdge(m2, h1_315);

        Locatie h1_312 = new Locatie(900, 525, "H.1.312", 30);
        this.locaties.add(h1_312); //H.1.312
        this.g.addVertex(h1_312);
        this.g.addEdge(m1, h1_312);

        Locatie h1_306 = new Locatie(1100, 525, "H.1.306", 30);
        this.locaties.add(h1_306); //H.1.306
        this.g.addVertex(h1_306);
        this.g.addEdge(m1, h1_306);

        Locatie h1_206 = new Locatie(1400, 525, "H.1.206", 30);
        this.locaties.add(h1_206); //H.1.206
        this.g.addVertex(h1_206);
        this.g.addEdge(lift, h1_206);

        Locatie h1_204 = new Locatie(1400, 400, "H.1.206", 30);
        this.locaties.add(h1_204); //H.1.204
        this.g.addVertex(h1_204);
        this.g.addEdge(lift, h1_204);


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
        this.canvas = canvas;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, 5, paint);
        }

        for (Locatie locatie : locaties) {
            locatie.draw(canvas);
        }

        Log.d(TAG, "point: drawing lines...");
        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);
        Locatie l1 = locaties.get(0);
        Locatie l2 = locaties.get(1);
        drawAllEdges();

//        Path path = new Path();
//        path.moveTo(700, 525);
//        path.lineTo(5, 0);
//        path.lineTo(-5, 0);
//        path.close();
//        path.offset(10, 40);
//        canvas.drawPath(path, paint);
//        path.offset(50, 100);
//        canvas.drawPath(path, paint);
//// offset is cumlative
//// next draw displaces 50,100 from previous
//        path.offset(50, 100);
//        canvas.drawPath(path, paint);
    }


    public void drawAllEdges()
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);

        for(Locatie locatie: locaties)
        {
            for(Locatie locatie1: locaties)
            {
                if(locatie == locatie1){
                    continue;
                }

                DefaultEdge edge = g.getEdge(locatie, locatie1);
                if(edge != null){
                    Log.d("Jinxi", "drawing  my line");
                    Path path = new Path();
                    path.moveTo(locatie.x, locatie.y);
                    path.lineTo(locatie1.x, locatie1.y);
                    path.close();
                    canvas.drawPath(path, paint);
                }
            }
        }

        Log.d("Jinxi1", g.toString());
    }

    private void drawLocatie(Canvas canvas, Locatie locatie)
    {
        canvas.drawCircle(locatie.x, locatie.y, 10, locatie.paint);
        canvas.drawText(locatie.naam, locatie.x - 50 ,locatie.y - 20, locatie.paint);
    }

    private static Path makeArrow(float length, float height) {
        Path p = new Path();
        p.moveTo(-2.0f, 0.0f);
        p.lineTo(length, height / 2.0f);
        p.lineTo(-2.0f, height);
        p.lineTo(-2.0f, 0.0f);
        p.close();
        return p;
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