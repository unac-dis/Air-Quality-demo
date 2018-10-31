package co.edu.unac.iotunac.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import co.edu.unac.iotunac.LocalDB.DBSQLiteHelper;
import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.auth.SingInActivity;
import co.edu.unac.iotunac.functions.TaskRegistro;
import co.edu.unac.iotunac.functions.User;

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
        editsleep = (EditText)  findViewById(R.id.editSleep);
        editpasos = (EditText) findViewById(R.id.editPasos);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
                dialog();
            }
        });
    }
    public void calcular(){
        if (editHeight.getText().toString().isEmpty()) {
            editHeight.setError("campo obligatorio");
        }
        if (editWeight.getText().toString().isEmpty()) {
            editWeight.setError("campo obligatorio");
        } else {
            Double n1 = Double.valueOf(Integer.parseInt((editHeight.getText().toString())));
            Double n2 = Double.valueOf(Integer.parseInt(editWeight.getText().toString()));
            Double metro = (n1 / 100);
            imc = (n2 / (metro * metro));
            fin = Double.valueOf(Math.round(imc));
        }
    }
    public void dialog(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(CalcularIMC.this);
        dialogo1.setView(R.layout.imc);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                registrar();
                Intent intent = new Intent(getApplicationContext(), Navigationdrawer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dialogo1.setMessage("Su IMC es: " + fin);
        dialogo1.show();
    }
    public void registrar (){

        DBSQLiteHelper mHelper = new DBSQLiteHelper(getApplicationContext());
        SQLiteDatabase midb = mHelper.getReadableDatabase();
        midb = mHelper.getWritableDatabase();

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}