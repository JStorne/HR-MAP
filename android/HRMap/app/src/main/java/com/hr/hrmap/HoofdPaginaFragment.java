package com.hr.hrmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jinxi on 6/7/16.
 */
public class HoofdPaginaFragment extends Fragment {

    PlattegrondView plattegrond;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hoofdpagina,
                container, false);
        //lets keep a reference of DrawView
        plattegrond = (PlattegrondView) view.findViewById(R.id.plattegrond);


        return plattegrond;
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getActivity().getIntent();
        Log.d("Jinxi", "Resuming fragment...");
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Log.d("Jinxi", "Suggestion: "+ uri);
        }
    }


}
