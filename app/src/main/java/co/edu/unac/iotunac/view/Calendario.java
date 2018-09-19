package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.unac.iotunac.R;

public class Calendario extends AppCompatActivity {

    CalendarView calendarView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendarView = findViewById(R.id.calendarView);
        imageView = findViewById(R.id.imageView);

        calendarView.setFirstDayOfWeek(1);
        calendarView.setShowWeekNumber(true);

        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.imguno), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgdos), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgtres), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgcuatro), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgcinco), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgseis), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgsiete), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgocho), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgnueve), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgdiez), 4500);
        animation.addFrame(getResources().getDrawable(R.drawable.imgonce), 4500);

        animation.setOneShot(false);

        imageView.setBackgroundDrawable(animation);
        animation.start();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                if(dayOfMonth == 31 || dayOfMonth == 30) {
                    Intent intent = new Intent(Calendario.this, Graficomensual.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(), "Rendimiento del:" + date, Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    Intent intent = new Intent(Calendario.this, Graficosemanal.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(), "Rendimiento del:" + date, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
