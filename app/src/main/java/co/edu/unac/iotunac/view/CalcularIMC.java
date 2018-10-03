package co.edu.unac.iotunac.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.unac.iotunac.R;

public class CalcularIMC extends AppCompatActivity {

    EditText editAge,editHeight,editWeight;
    private TextView Indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_imc);

        editAge = (EditText) findViewById(R.id.editAge);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editWeight = (EditText) findViewById(R.id.editWeight);
        Indice = (TextView) findViewById(R.id.Indice);
        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                float n1 = Float.parseFloat(editHeight.getText().toString());
                float n2 = Float.parseFloat(editWeight.getText().toString());
                float imc = n2/(n1*n1);
                Indice.setText(String.valueOf(imc));
            }
        });

    }
}
