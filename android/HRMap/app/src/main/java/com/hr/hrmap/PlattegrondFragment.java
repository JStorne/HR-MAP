package com.hr.hrmap;

/**
 * Created by jinxi on 6/16/16.
 */
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.graphics.Color;

public class PlattegrondFragment extends Fragment {
    PlattegrondView  plattegrondView;

    public PlattegrondFragment() {
    }


    /**
     * Returns Instance of DrawFragment
     *
     * @return Instance of DrawFragment
     */
    public static PlattegrondFragment getInstance() {
        PlattegrondFragment fragment = new PlattegrondFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.hoofdpagina,
                container, false);
        //lets keep a reference of DrawView
        plattegrondView = (PlattegrondView ) _view.findViewById(R.id.plattegrond);
        Intent intent = getActivity().getIntent();
        Log.d("Jinxi", "creating fragment..."+intent.getAction());
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Log.d("Jinxi", "Suggestion: "+ uri);
        }
        return _view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getActivity().getIntent();
        Log.d("Jinxi", "Resuming fragment..."+intent.getAction());
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        Log.d("Jinxi", "starting fragment..."+intent.getAction());
    }

    public void setDestination()
    {
        plattegrondView.destination = plattegrondView.locaties.get(6);
        plattegrondView.invalidate();
        Log.d("Jinxi", "Setting destination..");
    }

}