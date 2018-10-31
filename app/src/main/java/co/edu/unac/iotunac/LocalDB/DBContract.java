package co.edu.unac.iotunac.LocalDB;

import android.provider.BaseColumns;

/*** Created by Kevin Ortiz on 30/10/2018.*/

public class DBContract {

      private DBContract() { }

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_ESTATURA = "estatura";
        public static final String COLUMN_PESO = "peso";
        public static final String COLUMN_IMC = "imc";
        public static final String COLUMN_HORAS = "horas";
        public static final String COLUMN_PASOS = "pasos";
        public static final String COLUMN_EDAD = "edad";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL+ " TEXT, " +
                COLUMN_IMC+ " DOUBLE, " +
                COLUMN_ESTATURA+ " DOUBLE, " +
                COLUMN_PESO+ " DOUBLE, " +
                COLUMN_HORAS+ " INTEGER, " +
                COLUMN_PASOS + " INTEGER, " +
                COLUMN_EDAD + " INTEGER" + ")";
    }
}