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

import co.edu.unac.iotunac.R;

public class Descanso extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    static long sueño;
    static long horasdormidas;
    Button despertar;
    String[] listItems;
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
        despertar =findViewById(R.id.despertar);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Tiempo de Sueño: %s");
       sueño =0;
       despertar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listItems = getResources().getStringArray(R.array.descanso_item);
               AlertDialog.Builder mBuilder = new AlertDialog.Builder(Descanso.this);
               mBuilder.setTitle("¿Cómo califica su descanso?");
               mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(Descanso.this,"Su descanso fue "+listItems[i],Toast.LENGTH_LONG).show();
                       dialogInterface.dismiss();
                   }
               });

               AlertDialog mDialog = mBuilder.create();
               mDialog.show();
           }
       });
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