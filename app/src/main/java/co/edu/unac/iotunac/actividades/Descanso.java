package co.edu.unac.iotunac.actividades;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Calendar;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;
import co.edu.unac.iotunac.objects.Logro;

public class Descanso extends AppCompatActivity {

    double sueño;
    double horasdormidas;
    Button despertar;
    String[] listItems;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descanso);
        despertar = findViewById(R.id.despertar);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Tiempo de Sueño: %s");
        sueño = 0;
        despertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometer();
                listItems = getResources().getStringArray(R.array.descanso_item);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Descanso.this);
                mBuilder.setTitle("¿Cómo califica su descanso?");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Descanso.this, "Su descanso fue " + listItems[i], Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }

    public void startChronometer(View view) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View view) {
        if (running) {
            stopChronometer();
        }
    }

    private void stopChronometer() {
        chronometer.stop();
        sueño = SystemClock.elapsedRealtime() - chronometer.getBase();
        System.out.println("Sueñoooo " + sueño);
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        running = false;
    }

    public void resetChronometer() {
        stopChronometer();
        horasdormidas = sueño / 1000 / 3600;
        Toast.makeText(this, "dormiste " + horasdormidas + " horas", Toast.LENGTH_SHORT).show();
        insertLogroDescanso();
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    public void insertLogroDescanso() {
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(this);
        Logro logro = new Logro();
        logro.setFecha(Calendar.getInstance().getTime());
        logro.setPasoslogrados(0);
        logro.setHoraslogradas(horasdormidas);
        baseDatos.insertLogro(logro);
    }
}