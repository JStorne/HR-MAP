package com.hr.hrmap;

import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Created by jinxi on 6/17/16.
 */
public class Locatie {
    float x, y;
    String naam;
    Paint paint = new Paint();

    public Locatie(float x, float y, String naam) {
        this.x = x;
        this.y = y;
        this.naam = naam;
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }

    public Locatie(float x, float y, String naam, float fontsize) {
        this.x = x;
        this.y = y;
        this.naam = naam;
        paint.setColor(Color.RED);
        paint.setTextSize(fontsize);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }
}
