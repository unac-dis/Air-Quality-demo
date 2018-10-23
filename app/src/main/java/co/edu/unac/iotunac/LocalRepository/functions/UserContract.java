package co.edu.unac.iotunac.LocalRepository.functions;

import android.provider.BaseColumns;

/**
 * Created by JohanPerez on 20/09/2016.
 */
public class UserContract {
    public static abstract class UserEntry implements BaseColumns {
        public static final String USERS_TABLE_NAME = "users";

        public static final String USERS_COLUMN_ID = "id";
        public static final String USERS_COLUMN_NAME = "name";
        public static final String USERS_COLUMN_CORREO = "correo";
        public static final String USERS_COLUMN_PESO = "peso";
        public static final String USERS_COLUMN_ESTATURA = "estatura";
        public static final String USERS_COLUMN_EDAD = "edad";
        public static final String USERS_COLUMN_NUMPASOS = "numpasos";
        public static final String USERS_COLUMN_HORASSUEÑO = "horassueño";
        public static final String USERS_COLUMN_IMC = "imc";
    }
}
