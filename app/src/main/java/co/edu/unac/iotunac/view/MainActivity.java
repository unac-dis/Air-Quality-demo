package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import co.edu.unac.iotunac.R;

public class MainActivity extends AppCompatActivity {
    Button btndia, btnmes, btnsemana, calcular;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndia = (Button)findViewById(R.id.Gradia);
        btnmes = (Button)findViewById(R.id.Grames);
        btnsemana = (Button)findViewById(R.id.Grasemana);
        calcular = findViewById(R.id.imc);
        btndia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasar = (new Intent(MainActivity.this, Graficodiario.class));
                startActivity(pasar);
            }
        });
        btnsemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasar = (new Intent(MainActivity.this, Graficosemanal.class));
                startActivity(pasar);
            }
        });
        btnmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasar = (new Intent(MainActivity.this, Graficomensual.class));
                startActivity(pasar);
            }
        });
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasar = (new Intent(MainActivity.this, CalcularIMC.class));
                startActivity(pasar);

            }
        });
    }
}
