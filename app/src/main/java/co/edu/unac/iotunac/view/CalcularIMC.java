package co.edu.unac.iotunac.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.auth.SingInActivity;

public class CalcularIMC extends AppCompatActivity {

    EditText editAge,editHeight,editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_imc);

        editAge = (EditText) findViewById(R.id.editAge);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editWeight = (EditText) findViewById(R.id.editWeight);
        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(editHeight.getText().toString());
                int n2 = Integer.parseInt(editWeight.getText().toString());
                double metro = (n1/100);
                double imc = (n2/(metro*metro));
                ImageView image = new ImageView(CalcularIMC.this);
                image.setImageResource(R.drawable.ic_logounac_icon);
                double fin = Math.round(imc);
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
                dialogo1.setMessage("Su IMC es: "+imc);
                dialogo1.show();
               /* AlertDialog.Builder builder =
                        new AlertDialog.Builder(CalcularIMC.this).
                                setMessage(imsc).
                                setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).
                                setView(image);
                setTitle("REGISTRADO");
                builder.create().show();*/

            }
        });

    }
}
