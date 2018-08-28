package co.edu.unac.iotunac.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import co.edu.unac.iotunac.auth.LoginActivity;
import co.edu.unac.iotunac.R;

public class Test extends AppCompatActivity {
    Button ok;
    ListView list;
    private String questions[] = new String[]
            {"1. ¿Alguna vez su médico le ha indicado que usted tiene un problema cardiovascular, y que\n" +
                    "solamente puede llevar a cabo ejercicios o actividad física si lo refiere un médico?",
                    "2. ¿Sufre de dolores frecuentes en el pecho cuando realiza algún tipo de actividad física?",
                    "3. ¿En el último mes, le ha dolido el pecho cuando no estaba haciendo actividad física?",
                    "4. ¿Con frecuencia pierde el equilibrio debido a mareos, o alguna vez ha perdido el conocimiento?",
                    "5. ¿Tiene problemas en los huesos o articulaciones (por ejemplo, en la espalda, rodillas o cadera)\n" +
                            "que pudiera agravarse al aumentar la actividad física?",
                    "6. ¿Al presente, le receta su medico medicamentos (por ejemplo, pastillas de agua) para la\n" +
                            "presión arterial o problemas con el corazón?",
                    "7. ¿Existe alguna otra razón por la cual no debería participar en un programa de actividad física ?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("¡Atención!");
        dialogo1.setMessage(R.string.large_text);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("De acuerdo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.dismiss();
            }
        });
        dialogo1.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();
        LenguajeListAdapter adapter = new LenguajeListAdapter(this, questions);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Test.this);
                dialogo1.setTitle("¡Atención!");
                dialogo1.setCancelable(false);
                dialogo1.setMessage(R.string.afirma);
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent intent = new Intent(Test.this, LoginActivity.class);
                        startActivity(intent);
                        Toast t = Toast.makeText(Test.this, "Ahora puedes iniciar sesión", Toast.LENGTH_SHORT);
                        t.show();
                    }
                });
                dialogo1.show();
            }
        });
    }

    public void cancelar() {
        finish();
    }
}