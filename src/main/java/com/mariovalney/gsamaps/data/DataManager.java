package com.mariovalney.gsamaps.data;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mariovalney.gsamaps.data.DataContract.AmbassadorEntry;

/**
 * Created by neo on 20/11/14.
 */
public class DataManager extends Activity {

    private DataDbHelper mDbHelper = new DataDbHelper(getApplication());

    public DataManager() {
    }

    public Cursor readAllData(Context context) {

        String[] colunas = {
                AmbassadorEntry.COLUMN_NAME_NOME,
                AmbassadorEntry.COLUMN_NAME_INSTITUICAO,
                AmbassadorEntry.COLUMN_NAME_PAIS,
                AmbassadorEntry.COLUMN_NAME_LATITUDE,
                AmbassadorEntry.COLUMN_NAME_LONGITUDE
        };

        DataDbHelper dbHelper = new DataDbHelper(context);
        return dbHelper.readData(context, colunas);
    }

    public Cursor readAllLocations(Context context) {

        String[] colunas = {
                AmbassadorEntry.COLUMN_NAME_NOME,
                AmbassadorEntry.COLUMN_NAME_LATITUDE,
                AmbassadorEntry.COLUMN_NAME_LONGITUDE
        };

        DataDbHelper dbHelper = new DataDbHelper(context);
        return dbHelper.readData(context, colunas);
    }

    public void saveDataFromWebService(Context context) {
        DataDbHelper dbHelper = new DataDbHelper(context);

        dbHelper.deleteAllData(context);

        dbHelper.insertData(context, "Mário Valney", "UABC", "BR", -3.728934, -38.535374);
        dbHelper.insertData(context, "Gabriela Lopez", "UABC", "MX", 32.632633, -115.445882);
        int result = dbHelper.insertData(context, "Nagore Vidan",
                "Tecnologico de Monterrey Camous Cuernavaca", "MX", 18.805700, -99.221594);

        if (result == 0) {
            Toast.makeText(context,
                    "Dados atualizados.", Toast.LENGTH_SHORT).show();
        }
    }
}
