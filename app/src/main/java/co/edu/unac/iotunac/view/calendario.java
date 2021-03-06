package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;

public class calendario extends Fragment {

    CalendarView calendarView;
    ImageView imageView;
    TextView progreso;

    public calendario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        imageView = view.findViewById(R.id.imageView);
        progreso = view.findViewById(R.id.progreso);
        calendarView.setFirstDayOfWeek(1);
        calendarView.setShowWeekNumber(true);
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(getContext());
        int pasos = baseDatos.getLogroByDate(Calendar.getInstance().getTime()).getPasoslogrados();
        Double horas = baseDatos.getLogroByDate(Calendar.getInstance().getTime()).getHoraslogradas();
        progreso.setText("Has dormido "+horas+" horas.\n Has caminado "+pasos+" pasos");
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
                    startActivity(new Intent(getActivity(), Graficodiario.class));
                    Toast.makeText(getActivity(), "Rendimiento del:" + date, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
}