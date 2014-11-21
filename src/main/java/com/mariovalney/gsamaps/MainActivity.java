package com.mariovalney.gsamaps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mariovalney.gsamaps.data.DataContract.AmbassadorEntry;
import com.mariovalney.gsamaps.data.DataManager;

public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private GoogleMap mMap; // Null se não tiver mapa setado.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (id == R.id.action_ambassadors_list) {
            Intent intent = new Intent(this, ListActivity.class);
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
                    getSupportFragmentManager().findFragmentById(R.id.map_main)).getMap();

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
        //mapSettings.setZoomControlsEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Ler os dados armazenados
        DataManager dataManager = new DataManager();
        Cursor cursor = dataManager.readAllLocations(getApplication());

        // Varrendo o Cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String nome =
                cursor.getString(cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_NOME));
            float lat =
                cursor.getFloat(cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_LATITUDE));
            float lng =
                cursor.getFloat(cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_LONGITUDE));

            // Criando o Marker
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                    .title(nome).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

            cursor.moveToNext();
        }
        cursor.close();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.373552, -76.97600300), 2));
    }
}
