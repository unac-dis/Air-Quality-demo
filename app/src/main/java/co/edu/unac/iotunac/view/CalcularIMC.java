package co.edu.unac.iotunac.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.unac.iotunac.R;

public class CalcularIMC extends AppCompatActivity {

    EditText ageText,heighText,weightText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_imc);

        ageText = (EditText) findViewById(R.id.ageText);
        heighText = (EditText) findViewById(R.id.heighText);
        weightText = (EditText) findViewById(R.id.weightText);
        resultText = (TextView) findViewById(R.id.resultText);
        Button calculateIMC = (Button) findViewById(R.id.calculateIMC);

        calculateIMC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                float n1 = Float.parseFloat(heighText.getText().toString());
                float n2 = Float.parseFloat(weightText.getText().toString());
                float imc = n2/(n1*n1);
                resultText.setText(String.valueOf(imc));
            }
        });

    }
}