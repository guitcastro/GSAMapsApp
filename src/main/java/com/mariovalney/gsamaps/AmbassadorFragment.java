package com.mariovalney.gsamaps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by neo on 18/11/14.
 */
public class AmbassadorFragment extends Fragment {

    private GoogleMap mMap; // Null se não tiver mapa setado.

    private static String mNOME;
    private static String mINSTITUICAO;
    private static float mLAT;
    private static float mLNG;

    public AmbassadorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Cria a View
        View rootView = inflater.inflate(R.layout.ambassador_fragment, container, false);

        // Lê o Intent e o Endereço
        readingIntent();
        getAddress(rootView);

        // Faz o mapa, se necessário
        setUpMapIfNeeded();
        return rootView;
    }

    private void readingIntent() {
        // Descobrindo as intenções
        mNOME = getActivity().getIntent()
                .getStringExtra(AmbassadorActivity.INTENT_NOME);
        mINSTITUICAO = getActivity().getIntent()
                .getStringExtra(AmbassadorActivity.INTENT_INSTITUICAO);
        mLAT = getActivity().getIntent()
                .getFloatExtra(AmbassadorActivity.INTENT_LAT, 0);
        mLNG = getActivity().getIntent()
                .getFloatExtra(AmbassadorActivity.INTENT_LNG, 0);
    }

    private void getAddress(View rooview) {

        ((TextView) rooview.findViewById(
                R.id.ambassador_textview_nome)).setText(mNOME);
        ((TextView) rooview.findViewById(
                R.id.ambassador_textview_instituicao)).setText(mINSTITUICAO);
        ((TextView) rooview.findViewById(
                R.id.ambassador_textview_instituicao_endereco_linha1)).setText(Float.toString(mLAT));
        ((TextView) rooview.findViewById(
                R.id.ambassador_textview_instituicao_endereco_linha2)).setText(Float.toString(mLNG));
    }

    private void setUpMapIfNeeded() {
        // Verifica se é "null", ou seja, se não temos mapa ainda.
        if (mMap == null) {

            // Tenta obter um mapa do SupportMapFragment.
            mMap = ((SupportMapFragment)
                    getActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.map_ambassador)).getMap();

            // Verifica se temos um mapa
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        // Configurações do Mapa
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();

        //mapSettings.setZoomGesturesEnabled(false);
        mapSettings.setZoomControlsEnabled(false);

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.addMarker(new MarkerOptions().position(new LatLng(mLAT, mLNG))
                .title(mNOME).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mLAT, mLNG)));
        float maxZoom = mMap.getMaxZoomLevel();
        maxZoom = maxZoom - 1;
        if (maxZoom > 18) { maxZoom = 18; }
        mMap.moveCamera(CameraUpdateFactory.zoomTo(maxZoom));
    }
}
