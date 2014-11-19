package com.mariovalney.gsamaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by neo on 18/11/14.
 */
public class AmbassadorActivity extends ActionBarActivity {

    private final String LOG_TAG = AmbassadorActivity.class.getSimpleName();
    private GoogleMap mMap; // Null se não tiver mapa setado.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambassador);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.just_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpMapIfNeeded() {
        // Verifica se é "null", ou seja, se não temos mapa ainda.
        if (mMap == null) {
            // Tenta obter um mapa do SupportMapFragment.
            mMap = ((SupportMapFragment)
                    getSupportFragmentManager().findFragmentById(R.id.map_ambassador)).getMap();

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

        mapSettings.setZoomGesturesEnabled(false);
        mapSettings.setZoomControlsEnabled(false);

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.addMarker(new MarkerOptions().position(new LatLng(-3.736571, -38.579658))
                .title("Minha Casa"));
    }
}
