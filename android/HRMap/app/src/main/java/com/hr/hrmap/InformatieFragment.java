package com.hr.hrmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jinxi on 6/7/16.
 */
public class InformatieFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.informatie_main,
                container, false);
        return view;
    }

    public void setText(String url) {
//        TextView view = (TextView) getView().findViewById(R.id.detailsText);
//        view.setText(url);
    }
}
