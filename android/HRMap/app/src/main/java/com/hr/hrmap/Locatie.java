package com.hr.hrmap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Typeface;

import static java.lang.Math.*;

/**
 * Created by jinxi on 6/17/16.
 */
public class Locatie {
    int id = 0;
    float x, y;
    String naam;
    Paint paint = new Paint();
    public int verdieping;
    public boolean visible;

    public Locatie(float x, float y, String naam, int verdieping) {
        this.x = x;
        this.y = y;
        this.naam = naam;
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.id = Integer.parseInt(naam.replaceAll("\\D+",""));
        this.verdieping = verdieping;
    }

    public Locatie(float x, float y, String naam, float fontsize, int verdieping) {
        this.x = x;
        this.y = y;
        this.naam = naam;
        this.verdieping = verdieping;
        paint.setColor(Color.RED);
        paint.setTextSize(fontsize);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        try{
            this.id = Integer.parseInt(naam.replaceAll("\\D+",""));
        }catch (RuntimeException e){

        }
        if(naam.startsWith("n") || naam.startsWith("m")){
            this.visible = false;
        }else{
            //this.visible = true;
        }
        //this.visible = true;

    }

    public void draw(Canvas canvas)
    {
        if(this.visible == false){
            return;
        }
        canvas.drawCircle(this.x, this.y, 10, this.paint);
       // canvas.drawText(this.naam, this.x - 50 ,this.y - 20, this.paint);
    }

    public void drawArrowToLocatie(Locatie locatie, Canvas canvas)
    {
        canvas.drawLine( min(this.x, locatie.x) + 5 ,  min(this.y, locatie.y) + 5, max(this.x, locatie.x) - 5, max(this.y, locatie.y) - 5, paint);
    }

    @Override
    public String toString()
    {
        return this.naam;
    }
}
