package co.edu.unac.iotunac.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.Calendar;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;

public class Graficomensual extends AppCompatActivity {
    PieChart pieChart;
    PieChart horas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficomensual);
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(this);


        horas = findViewById(R.id.pieChartsueño);
        horas.setHoleRadius(40f);
        horas.setRotationEnabled(true);
        horas.animateXY(1500,1500);
        horas.setDescriptionTextSize(20f);
        horas.setDescription("Gŕafica del horas");

        pieChart = findViewById(R.id.pieChartpasos);
        pieChart.setHoleRadius(40f);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(1500,1500);
        pieChart.setDescription("Gráfica de los pasos");
        pieChart.setDescriptionTextSize(20f);

        //creamos una lista para los valores Y
        int logro = baseDatos.getLogroByDate(Calendar.getInstance().getTime()).getPasoslogrados();
        int meta = baseDatos.findUser().getNumpasos();
        double logros = baseDatos.getLogroByDate(Calendar.getInstance().getTime()).getHoraslogradas();
        int metas = baseDatos.findUser().getHorassueño();

        ArrayList<Entry> valsY = new ArrayList<>();
        valsY.add(new Entry(meta,0));
        valsY.add(new Entry(logro,1));

        ArrayList<Entry> valsYhoras = new ArrayList<>();
        valsYhoras.add(new Entry(metas,0));
        valsYhoras.add(new Entry((float) logros,1));

        //creamos una lista para los valores X
        ArrayList<String> valsX = new ArrayList<>();
        valsX.add("Pasos propuestos");
        valsX.add("Pasos logrados");

        ArrayList<String> valsXhoras = new ArrayList<>();
        valsXhoras.add("Horas propuestas");
        valsXhoras.add("Horas logradas");

        //creamos una lista de colores
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);

        //seteamos los valores de Y y los colores
        PieDataSet set1 = new PieDataSet(valsY, null);
        set1.setSliceSpace(3f);
        set1.setColors(colors);

        PieDataSet set = new PieDataSet(valsYhoras, null);
        set.setSliceSpace(3f);
        set.setColors(colors);

        //seteamos los valores de X
        PieData data = new PieData(valsX, set1);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();

        PieData datas = new PieData(valsXhoras, set);
        horas.setData(datas);
        horas.highlightValues(null);
        horas.invalidate();
    }
}