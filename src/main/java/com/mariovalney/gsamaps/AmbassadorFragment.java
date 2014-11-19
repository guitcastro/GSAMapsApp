package com.mariovalney.gsamaps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by neo on 18/11/14.
 */
public class AmbassadorFragment extends Fragment {

    public AmbassadorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ambassador_fragment, container, false);
        return rootView;
    }
}
