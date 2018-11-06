package co.edu.unac.iotunac.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.unac.iotunac.R;
import co.edu.unac.iotunac.localdb.DBSQLiteHelper;
import co.edu.unac.iotunac.objects.User;
import co.edu.unac.iotunac.task.RegistryTask;


public class ImcActivity extends AppCompatActivity {
TextView titulo;
TextView info;
ImageView tabla;
ImageView descripcion;
ImageButton continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        titulo = findViewById(R.id.tituloimc);
        info = findViewById(R.id.infoimc);
        tabla = findViewById(R.id.tabla);
        descripcion = findViewById(R.id.captura);
        continuar = findViewById(R.id.continuar);
        setInfo();
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Navigationdrawer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }

    public void setInfo() {
        DBSQLiteHelper baseDatos = new DBSQLiteHelper(getApplicationContext());
        Double imc = baseDatos.getUserByImc();
        if (imc<18.5){
            info.setText("Su indice de masa corporal es: "+imc+" que indica que estás bajo de peso");
            descripcion.setImageResource(R.drawable.pesobajo);
        }
        if (imc>=18.5 && imc <24.9){
            info.setText("Su indice de masa corporal es: "+imc+" que indica que tienes un peso ideal");
            descripcion.setImageResource(R.drawable.pesonormal);
        }
        if (imc>=24.9 && imc <29.9){
            info.setText("Su indice de masa corporal es: "+imc+" que indica sobrepeso");
            descripcion.setImageResource(R.drawable.sobrepeso);
        }
        if (imc>=29.9 && imc <34.9){
            info.setText("Su indice de masa corporal es: "+imc+" que indica obesidad");
            descripcion.setImageResource(R.drawable.obesidad);
        }
        if (imc>=34.9 && imc <39.9){
            info.setText("Su indice de masa corporal es: "+imc+" que indica obesidad severa");
            descripcion.setImageResource(R.drawable.obesidadsevera);
        }
        if (imc>=39.9){
            info.setText("Su indice de masa corporal es: "+imc+" que indica obesidad morbida");
            descripcion.setImageResource(R.drawable.obesidadmorbida);
        }
    }

}
