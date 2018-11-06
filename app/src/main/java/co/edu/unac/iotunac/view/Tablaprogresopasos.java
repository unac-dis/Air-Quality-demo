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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;

public class Tablaprogresopasos extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> dates;
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
        barChart.invalidate();

        YAxis yAxis = barChart.getAxisRight();
        yAxis.setEnabled(false);

        createRandomBarGraph("2018/11/04", "2018/11/07");
    }

    public void createRandomBarGraph(String Date1, String Date2) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(this);

        try {
            Date date1 = simpleDateFormat.parse(Date1);
            Date date2 = simpleDateFormat.parse(Date2);

            Calendar mDate1 = Calendar.getInstance();
            Calendar mDate2 = Calendar.getInstance();
            mDate1.clear();
            mDate2.clear();

            mDate1.setTime(date1);
            mDate2.setTime(date2);

            dates = new ArrayList<>();
            dates = getList(mDate1, mDate2);

            barEntries = new ArrayList<>();

            float max = 0f;
            float value = 0f;
            int valuey=0;
            pasos = baseDatos.getLogroByDate(Calendar.getInstance().getTime()).getPasoslogrados();
            max = baseDatos.findUser().getNumpasos();
            for (int j = 0; j < dates.size(); j++) {
                value = ((int) pasos);
                valuey = (int) max;
                barEntries.add(new BarEntry(value, 1,valuey));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Fechas");
        barDataSet.setColor(Color.GREEN);
        BarData barData = new BarData(dates, barDataSet);
        barData.setValueTextSize(15);
        barChart.setData(barData);
        barChart.setDescription("Gráfico de Pasos");
        barChart.setDescriptionPosition(1030, 1045);
        barChart.setDescriptionTextSize(15);

    }

    public ArrayList<String> getList(Calendar startDate, Calendar endDate) {
        ArrayList<String> list = new ArrayList<String>();
        while (startDate.compareTo(endDate) <= 0) {
            list.add(getDate(startDate));
            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    public String getDate(Calendar cld) {
        String curDate = cld.get(Calendar.YEAR) + "/" + (cld.get(Calendar.MONTH) + 1) + "/"
                + cld.get(Calendar.DAY_OF_MONTH);
        try {
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(curDate);
            curDate = new SimpleDateFormat("yyy/MM/dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDate;
    }
}
