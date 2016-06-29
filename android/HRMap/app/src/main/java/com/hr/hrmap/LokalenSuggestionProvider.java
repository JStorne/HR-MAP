package com.hr.hrmap;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinxi on 6/20/16.
 */
public class LokalenSuggestionProvider extends ContentProvider {

    List<Locatie> locaties = new ArrayList<>();

    public LokalenSuggestionProvider(List<Locatie> lokalen) {
        this.locaties = lokalen;
    }

    public LokalenSuggestionProvider() {
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

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        BaseColumns._ID,
                        SearchManager.SUGGEST_COLUMN_TEXT_1,
                }
        );

        String query = uri.getLastPathSegment().toLowerCase();
        for(Locatie locatie: locaties)
        {
            if (locatie.naam.toLowerCase().contains(query)){
                cursor.addRow(new Object[]{locatie.x, locatie.naam});
            }

        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
