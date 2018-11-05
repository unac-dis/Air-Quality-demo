package co.edu.unac.iotunac.actividades;

import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;

public class Descanso extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    static long sueño;
    static long horasdormidas;

    public static long getHorasdormidas() {
        return horasdormidas;
    }

    public static void setHorasdormidas(long horasdormidas) {
        Descanso.horasdormidas = horasdormidas;
    }

    public static long getSueño() {
        return sueño;
    }

    public static void setSueño(long sueño) {
        Descanso.sueño = sueño;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descanso);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Tiempo de Sueño: %s");
       sueño =0;
    }

    public void startChronometer(View view){
        if (!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View view){
        if (running){
            chronometer.stop();
            sueño = SystemClock.elapsedRealtime() - chronometer.getBase();
            horasdormidas = (sueño/1000)/3600;
            Toast.makeText(this,"dormiste "+horasdormidas+" horas",Toast.LENGTH_SHORT).show();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}