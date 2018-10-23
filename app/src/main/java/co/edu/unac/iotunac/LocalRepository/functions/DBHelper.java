package co.edu.unac.iotunac.LocalRepository.functions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_CORREO;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_EDAD;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_ESTATURA;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_HORASSUEÑO;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_ID;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_IMC;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_NAME;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_NUMPASOS;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_COLUMN_PESO;
import static co.edu.unac.iotunac.LocalRepository.functions.UserContract.UserEntry.USERS_TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "IOT.db";
    public SQLiteDatabase db;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "
                     + USERS_TABLE_NAME
                     +  "("
                    + USERS_COLUMN_ID + " integer primary key AUTOINCREMENT, "
                    + USERS_COLUMN_NAME + " text, "
                    + USERS_COLUMN_CORREO + " text, "
                    + USERS_COLUMN_PESO + " text, "
                    + USERS_COLUMN_ESTATURA + " text, "
                    + USERS_COLUMN_HORASSUEÑO + " text, "
                    + USERS_COLUMN_NUMPASOS + " text, "
                    + USERS_COLUMN_EDAD + " text, "
                    + USERS_COLUMN_IMC + " DOUBLE)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
         onCreate(db);
    }

}
