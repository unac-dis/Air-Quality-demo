package co.edu.unac.iotunac.functions;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

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

    public void calcular() {
        if (editAge.getText().toString().isEmpty()) {
            editAge.setError("campo obligatorio");
        }
        if (editWeight.getText().toString().isEmpty()) {
            editWeight.setError("campo obligatorio");
        }
        if (editHeight.getText().toString().isEmpty()) {
            editHeight.setError("campo obligatorio");
        }
        if (editsleep.getText().toString().isEmpty()) {
            editsleep.setError("campo obligatorio");
        }
        if (editpasos.getText().toString().isEmpty()) {
            editpasos.setError("campo obligatorio");
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
        TaskRegistro usersRegistry = new TaskRegistro();
        User userRegistry = new User();
        userRegistry.setCorreo(email);
        userRegistry.setEstatura(Double.valueOf(editHeight.getText().toString()));
        userRegistry.setPeso(Double.valueOf(editWeight.getText().toString()));
        userRegistry.setEdad(Integer.parseInt(editAge.getText().toString()));
        userRegistry.setHorassueño(Integer.parseInt(editsleep.getText().toString()));
        userRegistry.setNumpasos(Integer.parseInt(editpasos.getText().toString()));
        userRegistry.setImc(fin);

        try {
            String resul = usersRegistry.execute(userRegistry).get();
            dialog();
    //        DBSQLiteHelper guardar = new DBSQLiteHelper(this);
      //      guardar.insertUser(userRegistry);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}