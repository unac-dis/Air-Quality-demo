package co.edu.unac.iotunac.task;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unac.iotunac.localdb.DBSQLiteHelper;
import co.edu.unac.iotunac.objects.User;
import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.auth.SingInActivity;
import co.edu.unac.iotunac.view.Navigationdrawer;

public class CalcularIMC extends AppCompatActivity {

    EditText editAge, editHeight, editWeight, editpasos, editsleep;
    Button button3;
    Double fin, imc;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_imc);
        editAge = (EditText) findViewById(R.id.editAge);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editWeight = (EditText) findViewById(R.id.editWeight);
        editsleep = (EditText) findViewById(R.id.editSleep);
        editpasos = (EditText) findViewById(R.id.editPasos);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();

            }
        });
    }

    public boolean isNullOrEmpty(EditText editText) {
        return !(editText.getText().toString() != null && !editText.getText().toString().isEmpty());

    }

    public void calcular() {
        if (isNullOrEmpty(editAge) || isNullOrEmpty(editWeight) || isNullOrEmpty(editHeight)
                || isNullOrEmpty(editsleep) || isNullOrEmpty(editpasos)) {
            editHeight.setError("campo obligatorio");
            editAge.setError("campo obligatorio");
            editWeight.setError("campo obligatorio");
            editsleep.setError("campo obligatorio");
            editpasos.setError("campo obligatorio");

            Toast.makeText(this, "Por Favor Ingrese Todos Los Campos Requeridos", Toast.LENGTH_SHORT).show();
        } else {
            Double Height = (double) Integer.parseInt((editHeight.getText().toString()));
            Double peso = (double) Integer.parseInt(editWeight.getText().toString());
            Double metro = (Height / 100);
            imc = (peso / (metro * metro));
            fin = (double) Math.round(imc);
            registrar();
        }
    }

    public void dialog() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(CalcularIMC.this);
        dialogo1.setView(R.layout.imc);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Intent intent = new Intent(getApplicationContext(), Navigationdrawer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dialogo1.setMessage("Su IMC es: " + fin);
        dialogo1.show();
    }

    public void registrar() {

        email = SingInActivity.getTxtEmails();
        TaskRegistro task = new TaskRegistro();
        User userRegistry = new User();
        userRegistry.setCorreo(email);
        userRegistry.setEstatura(Double.valueOf(editHeight.getText().toString()));
        userRegistry.setPeso(Double.valueOf(editWeight.getText().toString()));
        userRegistry.setEdad(Integer.parseInt(editAge.getText().toString()));
        userRegistry.setHorassueño(Integer.parseInt(editsleep.getText().toString()));
        userRegistry.setNumpasos(Integer.parseInt(editpasos.getText().toString()));
        userRegistry.setImc(fin);

        try {
            String resul = task.execute(userRegistry).get();

            DBSQLiteHelper baseDatos = new DBSQLiteHelper(this);
            baseDatos.insertUser(userRegistry);
            dialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}