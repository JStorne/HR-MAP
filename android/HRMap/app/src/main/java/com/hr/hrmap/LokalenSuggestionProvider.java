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

        this.locaties.add(new Locatie(1325, 180, "lift", 30, 1)); //lift

//        this.locaties.add(new Locatie(400, 400, "m1", 30, 1)); //m1
//        this.locaties.add(new Locatie(600, 400, "m2", 30, 1)); //m2
//        this.locaties.add(new Locatie(800, 400, "m3", 30, 1)); //m3
//        this.locaties.add(new Locatie(1000, 400, "m4", 30, 1)); //m4

        this.locaties.add(new Locatie(600, 200, "H.1.101", 30, 1)); //H.1.110
        this.locaties.add(new Locatie(750, 200, "H.1.102", 30, 1)); //H.1.112
        this.locaties.add(new Locatie(900, 200, "H.1.104", 30, 1)); //H.1.114
        this.locaties.add(new Locatie(900, 200, "H.1.105", 30, 1)); //H.1.114

        this.locaties.add(new Locatie(300, 525, "H.1.403", 30, 1)); //H.1.403
        this.locaties.add(new Locatie(400, 500, "H.1.319", 30, 1)); //H.1.319
        this.locaties.add(new Locatie(500, 525, "H.1.318", 30, 1)); //H.1.318
        this.locaties.add(new Locatie(700, 525, "H.1.315", 30, 1)); //H.1.315
        this.locaties.add(new Locatie(900, 525, "H.1.312", 30, 1)); //H.1.312
        this.locaties.add(new Locatie(1100, 525, "H.1.306", 30, 1)); //H.1.306

        this.locaties.add(new Locatie(1400, 525, "H.1.206", 30,1)); //H.1.206
        this.locaties.add(new Locatie(1400, 400, "H.1.204", 30, 1)); //H.1.204

        /**
         * tweede verdieping
         */
        this.locaties.add(new Locatie(600, 200, "H.2.110", 30, 2));

        this.locaties.add(new Locatie(750, 200, "H.2.112", 30, 2));
        this.locaties.add(new Locatie(900, 200, "H.2.114", 30, 2));
        Locatie h2_403 = new Locatie(300, 525, "H.2.403", 30, 2);
        this.locaties.add(h2_403); //H.1.403
        Locatie h2_319 = new Locatie(400, 500, "H.2.319", 30, 2);
        this.locaties.add(h2_319); //H.1.319
        Locatie h2_318 = new Locatie(500, 525, "H.2.318", 30, 2);
        this.locaties.add(h2_318); //H.1.318

        Locatie h2_315 = new Locatie(700, 525, "H.2.315", 30, 2);
        this.locaties.add(h2_315); //H.1.315
        Locatie h2_312 = new Locatie(900, 525, "H.2.312", 30, 2);
        this.locaties.add(h2_312); //H.1.312
        Locatie h2_306 = new Locatie(1100, 525, "H.2.306", 30, 2);
        this.locaties.add(h2_306); //H.1.306
        Locatie h2_206 = new Locatie(1400, 525, "H.2.206", 30, 2);
        this.locaties.add(h2_206); //H.1.206
        Locatie h2_204 = new Locatie(1400, 400, "H.2.206", 30, 2);
        this.locaties.add(h2_204); //H.1.204

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
                        SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
                }
        );

        String query = uri.getLastPathSegment().toLowerCase();
        for(Locatie locatie: locaties)
        {
            if (locatie.naam.toLowerCase().contains(query)){
                cursor.addRow(new Object[]{locatie.x, locatie.naam, locatie.id});
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
