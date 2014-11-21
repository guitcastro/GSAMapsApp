package com.mariovalney.gsamaps.data;

import android.provider.BaseColumns;

/**
 * Created by neo on 20/11/14.
 */

public final class DataContract {

    public DataContract() {}

    public static abstract class AmbassadorEntry implements BaseColumns {
        public static final String TABLE_NAME = "tb_ambassadors";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_INSTITUICAO = "instituicao";
        public static final String COLUMN_NAME_PAIS = "pais";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
    }
}
