package co.edu.unac.iotunac.actividades;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;
import co.edu.unac.iotunac.objects.Logro;
import co.edu.unac.iotunac.view.Navigationdrawer;
import co.edu.unac.iotunac.view.Tablaprogresopasos;

public class Podometro extends AppCompatActivity implements SensorEventListener, StepListener {

    private static final String TEXT_NUM_STEPS = ": Pasos";
   static int numSteps;
    static double calorias;
     TextView TvSteps;
     TextView Tvcalorias;
     StepDetector simpleStepDetector;
     SensorManager sensorManager;
     android.hardware.Sensor accel;
     Button BtnStart;
     Button BtnReiniciar;
     Button Btnrojo;
     Button Btnamarillo;
     Button Btnverde;

    public static int getNumSteps() {
        return numSteps;
    }

    public static void setNumSteps(int numSteps) {
        Podometro.numSteps = numSteps;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podometro);

        AlertDialog.Builder avisopodometro = new AlertDialog.Builder(Podometro.this);
        avisopodometro.setTitle("¡Aviso!");
        avisopodometro.setMessage("Para una mejor precisión del sensor, por favor " +
                "mantenga el celular en el bolsillo, o si esta haciendo ejercicio, en " +
                "el estuche respectivo, que este en contacto con su cuerpo.");
        avisopodometro.setCancelable(false);
        avisopodometro.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        avisopodometro.show();

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = findViewById(R.id.tv_steps);
        Tvcalorias = findViewById(R.id.tv_calorias);
        BtnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        BtnReiniciar = findViewById(R.id.btn_reiniciar);
        Btnrojo = findViewById(R.id.btn_rojo);
        Btnamarillo = findViewById(R.id.btn_amarillo);
        Btnverde = findViewById(R.id.btn_verde);

        Double state = Navigationdrawer.getCo();

        if (state > 60) {
            Btnrojo.setBackgroundDrawable(getDrawable(R.drawable.semaforo_rojo));
            Btnrojo.setText("PELIGRO");
        } else if (state > 40 && state < 60) {
            Btnamarillo.setBackgroundDrawable(getDrawable(R.drawable.semaforo_amarillo));
            Btnamarillo.setText("ALERTA");
        } else {
            Btnverde.setBackgroundDrawable(getDrawable(R.drawable.semaforo_verde));
            Btnverde.setText("SEGURO");
        }

        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSteps = 0;
                sensorManager.registerListener(Podometro.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                Toast.makeText(getApplicationContext(), "Inicia el conteo de pasos", Toast.LENGTH_LONG).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager.unregisterListener(Podometro.this);
                insertLogro();
                Intent intent = new Intent(Podometro.this, Tablaprogresopasos.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Usted se ha detenido", Toast.LENGTH_LONG).show();
            }
        });

        BtnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSteps = 0;
                TvSteps.setText(numSteps + TEXT_NUM_STEPS);
                calorias = 0;
                Tvcalorias.setText(String.format("%.2f : Kcal", calorias));
            }
        });
        Btnrojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Podometro.this)
                        .setIcon(R.drawable.ic_informacion_icon)
                        .setTitle("Info")
                        .setMessage(R.string.rojo)
                        .setCancelable(false)
                        .setPositiveButton("Cerrar", null)
                        .show();
            }
        });
        Btnamarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Podometro.this)
                        .setIcon(R.drawable.ic_informacion_icon)
                        .setTitle("Info")
                        .setMessage(R.string.amarillo)
                        .setCancelable(false)
                        .setPositiveButton("Cerrar", null)
                        .show();
            }
        });
        Btnverde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Podometro.this)
                        .setIcon(R.drawable.ic_informacion_icon)
                        .setTitle("Info")
                        .setMessage(R.string.verde)
                        .setCancelable(false)
                        .setPositiveButton("cerrar", null)
                        .show();
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(numSteps + TEXT_NUM_STEPS);

        calorias = numSteps / 20;
        Tvcalorias.setText(String.format("%.2f : Kcal", calorias));
    }

    public void insertLogro() {
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(this);
        Logro logro = new Logro();
        logro.setFecha(Calendar.getInstance().getTime());
        logro.setPasoslogrados(numSteps);
        logro.setHoraslogradas(0.0);
        baseDatos.insertLogro(logro);
    }
}