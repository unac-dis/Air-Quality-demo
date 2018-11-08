package co.edu.unac.iotunac.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.actividades.Podometro;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;

public class Tablaprogresopasos extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> steps;
    int pasos;
    ArrayList<BarEntry> barEntries;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablaprogresopasos);

        barChart = findViewById(R.id.barChart);
        textView = findViewById(R.id.textinformacion);
        barChart.setBackgroundColor(Color.LTGRAY);
        barChart.setGridBackgroundColor(Color.LTGRAY);
        barChart.animateY(2500);


        YAxis yAxis = barChart.getAxisRight();
        yAxis.setEnabled(false);

        createRandomBarGraph();
    }

    public void createRandomBarGraph() {

        DBSQLiteHelper baseDatos = new DBSQLiteHelper(this);

        try {
            int Meta= baseDatos.findUser().getNumpasos();
            pasos = Podometro.getNumSteps();


            steps = new ArrayList<>();
            steps.add("Meta");
            steps.add("Logro");

            barEntries = new ArrayList<>();
            barEntries.add(new BarEntry(pasos, 1));
            barEntries.add(new BarEntry(Meta, 0));



    } catch (Exception e) {
        e.printStackTrace();
    }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Meta");
        barDataSet.setColor(Color.BLUE);
        BarData barData = new BarData(steps, barDataSet);
        barData.setValueTextSize(15);
        barChart.setData(barData);
        barChart.setDescriptionPosition(1030, 1000);
    }


}
