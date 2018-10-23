package co.edu.unac.iotunac.LocalRepository.functions;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import co.edu.unac.iotunac.functions.Users;

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

public class UserRepository {
    DBHelper dbHelper;

    public UserRepository(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public boolean insertUser(Users user)
    {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(USERS_COLUMN_NAME, user.getName());
            contentValues.put(USERS_COLUMN_CORREO, user.getCorreo());
            contentValues.put(USERS_COLUMN_ID, user.getId());
            contentValues.put(USERS_COLUMN_NUMPASOS, user.getNumpasos());
            contentValues.put(USERS_COLUMN_HORASSUEÑO, user.getHorassueño());
            contentValues.put(USERS_COLUMN_IMC, user.getImc());
            contentValues.put(USERS_COLUMN_PESO, user.getPeso());
            contentValues.put(USERS_COLUMN_EDAD, user.getEdad());
            long a = dbHelper.db.insert(USERS_TABLE_NAME, null, contentValues);

            return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    public Users getUserByUserName(String userName){
        Users user = null;
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + USERS_TABLE_NAME
                            + " WHERE " + USERS_COLUMN_ID + " = '" + userName + "'"
                    , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                user = mapUser(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return user;
    }



    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + USERS_TABLE_NAME
                            + " ORDER BY " + USERS_COLUMN_NAME
                            , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                users = mapUsers(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return users;
    }

    private Users mapUser(Cursor query){
        Users user = new Users();
        try {
            user.setId(query.getInt(query.getColumnIndex(USERS_COLUMN_ID)));
            user.setName(query.getString(query.getColumnIndex(USERS_COLUMN_NAME)));
            user.setCorreo(query.getString(query.getColumnIndex(USERS_COLUMN_CORREO)));
            user.setPeso(Double.valueOf(query.getString(query.getColumnIndex(USERS_COLUMN_PESO))));
            user.setEstatura(Double.valueOf(query.getString(query.getColumnIndex(USERS_COLUMN_ESTATURA))));
            user.setEdad(query.getInt(query.getColumnIndex(USERS_COLUMN_EDAD)));
            user.setHorassueño(query.getInt(query.getColumnIndex(USERS_COLUMN_HORASSUEÑO)));
            user.setNumpasos(query.getInt(query.getColumnIndex(USERS_COLUMN_NUMPASOS)));
            user.setImc(query.getInt(query.getColumnIndex(USERS_COLUMN_IMC)));
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return user;
    }

    private List<Users> mapUsers(Cursor query) {
        List<Users> users = new ArrayList<Users>();
        for(query.moveToFirst(); !query.isAfterLast(); query.moveToNext()){
            users.add(mapUser(query));
        }
        return users;
    }
}
