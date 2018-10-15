package co.edu.unac.iotunac.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import co.edu.unac.iotunac.R;

public class Graficodiario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficodiario);

        PieChart pieChart;

        pieChart = findViewById(R.id.pieChartDiario);

        //definimos algunos atributos
        pieChart.setHoleRadius(40f);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(1500,1500);
        pieChart.setDescription("Éste es su progreso " +
                "Cada día.");
        pieChart.setDescriptionTextSize(20f);

        //creamos una lista para los valores Y
        ArrayList<Entry> valsY = new ArrayList<Entry>();
        valsY.add(new Entry(20*100/25,0));
        valsY.add(new Entry(50*10/25,1));

        //creamos una lista para los valores X
        ArrayList<String> valsX = new ArrayList<String>();
        valsX.add("Tiempo de Actividad");
        valsX.add("Tiempo de Descanso");

        //creamos una lista de colores
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        //seteamos los valores de Y y los colores
        PieDataSet set1 = new PieDataSet(valsY, null);
        set1.setSliceSpace(3f);
        set1.setColors(colors);

        //seteamos los valores de X
        PieData data = new PieData(valsX, set1);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
}
