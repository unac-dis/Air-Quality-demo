package co.edu.unac.iotunac.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import co.edu.unac.iotunac.objects.User;

import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_EDAD;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_EMAIL;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_ESTATURA;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_HORAS;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_IMC;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_PASOS;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_PESO;
import static co.edu.unac.iotunac.localdb.DBContract.User.TABLE_NAME;

/*** Created by Kevin Ortiz on 30/10/2018.*/

public class DBSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;


    public DBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBContract.User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser(User user) {
        db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_EMAIL, user.getCorreo());
            contentValues.put(COLUMN_ESTATURA, user.getEstatura());
            contentValues.put(COLUMN_PESO, user.getPeso());
            contentValues.put(COLUMN_IMC, user.getImc());
            contentValues.put(COLUMN_PASOS, user.getNumpasos());
            contentValues.put(COLUMN_HORAS, user.getHorassueño());
            contentValues.put(COLUMN_EDAD, user.getEdad());
            long id = db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public String getUserByEmail(String email) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT email FROM user where email = '" + email + "'", null);
        return c.moveToFirst() ? c.getString(0) : null;
    }
    public int getUserByPasos(int pasos) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT pasos FROM user where pasos = '" + pasos + "'", null);
        return c.moveToFirst() ? c.getInt(0) : null;
    }

}