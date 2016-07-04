package com.hr.hrmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
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

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
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
    public int currentVerdieping = 1;



    public PlattegrondView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);
        //this.setBackgroundResource(R.drawable.h2);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        /* Load the correct list of locaties */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PlattegrondView, 0, 0);
        String verdieping = a.getString(R.styleable.PlattegrondView_verdieping);
        MainActivity activity = (MainActivity) getContext();
        this.locaties = activity.plattegrond.getLocatiesVanVerdieping(Integer.parseInt(verdieping));
    }


    private void init(Context context)
    {


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
        Log.d("jinxi", "Redrawing plattegrond");
        this.canvas = canvas;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        for (Locatie locatie : locaties) {
            locatie.draw(canvas);
        }


        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);

        //drawAllEdges();
//
//        Log.d("Jinxi", vertices.toString());
//        Locatie prev = null;
//        for(Locatie locatie: vertices)
//        {
//            if(prev == null)
//            {
//                prev = locatie;
//                continue;
//            }
//
//            drawPath(prev, locatie);
//            prev = locatie;
//        }

    }

    public void drawPath(Locatie l1, Locatie l2)
    {
        Log.d("Jinxi", "drawing path...." + l1.toString() + " -> " + l2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        Path path = new Path();
        path.moveTo(l1.x, l1.y);
        path.lineTo(l2.x, l2.y);
        path.close();
        canvas.drawPath(path, paint);
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