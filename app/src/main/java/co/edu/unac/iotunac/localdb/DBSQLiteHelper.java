package co.edu.unac.iotunac.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.edu.unac.iotunac.objects.Logro;
import co.edu.unac.iotunac.objects.User;

import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_EDAD;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_EMAIL;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_ESTATURA;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_HORAS;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_IMAGE;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_IMC;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_NAME;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_PASOS;
import static co.edu.unac.iotunac.localdb.DBContract.User.COLUMN_PESO;
import static co.edu.unac.iotunac.localdb.DBContract.User.TABLE_NAME;
import static co.edu.unac.iotunac.localdb.DBContract.Logro.COLUMN_DATE;
import static co.edu.unac.iotunac.localdb.DBContract.Logro.COLUMN_HORASL;
import static co.edu.unac.iotunac.localdb.DBContract.Logro.COLUMN_PASOSL;

/*** Created by Kevin Ortiz on 30/10/2018.*/

public class DBSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBContract.User.CREATE_TABLE);
        sqLiteDatabase.execSQL(DBContract.Logro.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.User.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBContract.Logro.TABLE_NAME);
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
            contentValues.put(COLUMN_NAME, user.getName());
            contentValues.put(COLUMN_IMAGE, String.valueOf(user.getImage()));
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

    public boolean insertLogro(Logro logro) {
        db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_DATE, sdf.format(logro.getFecha()));
            contentValues.put(COLUMN_PASOSL, logro.getPasoslogrados());
            contentValues.put(COLUMN_HORASL, logro.getHoraslogradas());
            long id = db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public User findUser() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT email, edad, peso, estatura, horas, pasos, imc FROM user", null);
        return c.moveToFirst() ? buildUser(c) : new User();
    }

    private User buildUser(Cursor c) {
        User user = new User();
       // int i = 0;
        user.setCorreo(c.getString(0));
        user.setEdad(c.getInt(1));
        user.setPeso(c.getDouble(2));
        user.setEstatura(c.getDouble(3));
        user.setHorassueño(c.getInt(4));
        user.setNumpasos(c.getInt(5));
        user.setNumpasos(c.getInt(6));
        return user;
    }

    public Logro getLogroByDate(Date date) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT fecha, sum(pasoslogrados), sum(horaslogradas) FROM logro where fecha = '" + sdf.format(date) + "' GROUP BY fecha", null);
        return c.moveToFirst() ? buildLogro(c) : new Logro();
    }

    private Logro buildLogro(Cursor c) {
        return new Logro(getDate(c.getString(0)), c.getInt(1), c.getInt(2));
    }

    private Date getDate(String date) {
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return Calendar.getInstance().getTime();
        }
    }
    public Double getUserByImc() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT imc FROM user", null);
        return c.moveToFirst() ? c.getDouble(0) : null;
    }
    public String getUserByImage() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT image FROM user", null);
        return c.moveToFirst() ? c.getString(0) : null;
    }
    public String getUserByName() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM user", null);
        return c.moveToFirst() ? c.getString(0) : null;
    }
    public String getUserByEmail() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT email FROM user", null);
        return c.moveToFirst() ? c.getString(0) : null;
    }
    public int getUserbylogros() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT sum(pasoslogrados) FROM logro", null);
        return c.moveToFirst() ? c.getInt(0) : null;
    }
    public int getUserBypasos() {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT pasos FROM user", null);
        return c.moveToFirst() ? c.getInt(0) : null;
    }
}