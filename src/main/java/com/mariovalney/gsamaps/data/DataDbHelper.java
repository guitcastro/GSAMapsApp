package com.mariovalney.gsamaps.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mariovalney.gsamaps.data.DataContract.AmbassadorEntry;

/**
 * Created by neo on 20/11/14.
 */
public class DataDbHelper extends  SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ambassador.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AmbassadorEntry.TABLE_NAME + " ( " +
                    AmbassadorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AmbassadorEntry.COLUMN_NAME_NOME + " TEXT NOT NULL, " +
                    AmbassadorEntry.COLUMN_NAME_INSTITUICAO + " TEXT NOT NULL, " +
                    AmbassadorEntry.COLUMN_NAME_PAIS + " TEXT NOT NULL, " +
                    AmbassadorEntry.COLUMN_NAME_LATITUDE + " REAL NOT NULL, " +
                    AmbassadorEntry.COLUMN_NAME_LONGITUDE + " REAL NOT NULL" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AmbassadorEntry.TABLE_NAME;

    public DataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public int insertData(Context context, String nome, String instituicao, String pais,
                           double lat, double lng) {

        int erros = 0;

        DataDbHelper mDbHelper = new DataDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Criando o mapa de Valores
        ContentValues values = new ContentValues();
        values.put(AmbassadorEntry.COLUMN_NAME_NOME, nome);
        values.put(AmbassadorEntry.COLUMN_NAME_INSTITUICAO, instituicao);
        values.put(AmbassadorEntry.COLUMN_NAME_PAIS, pais);
        values.put(AmbassadorEntry.COLUMN_NAME_LATITUDE, lat);
        values.put(AmbassadorEntry.COLUMN_NAME_LONGITUDE, lng);

        long row;
        row = db.insert(AmbassadorEntry.TABLE_NAME, null, values);

        if (row == -1) {
            erros = 1;
        }

        return erros;
    }

    public Cursor readData(Context context, String[] colunas) {

        DataDbHelper mDbHelper = new DataDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sortOrder = AmbassadorEntry.COLUMN_NAME_NOME + " ASC";

        return db.query(
                AmbassadorEntry.TABLE_NAME,     // The table to query
                colunas,                        // The columns to return
                null,                           // The columns for the WHERE clause
                null,                           // The values for the WHERE clause
                null,                           // don't group the rows
                null,                           // don't filter by row groups
                sortOrder                       // The sort order
        );
    }

    public void deleteAllData(Context context) {
        DataDbHelper mDbHelper = new DataDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }
}