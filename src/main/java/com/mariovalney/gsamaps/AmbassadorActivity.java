package com.mariovalney.gsamaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by neo on 18/11/14.
 */
public class AmbassadorActivity extends ActionBarActivity {

    private final String LOG_TAG = AmbassadorActivity.class.getSimpleName();
    public static final String INTENT_NOME = "ambassador_name";
    public static final String INTENT_INSTITUICAO = "ambassador_instituicao";
    public static final String INTENT_LAT = "ambassador_lat";
    public static final String INTENT_LNG = "ambassador_lng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambassador);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}
