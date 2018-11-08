package co.edu.unac.iotunac.auth;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import co.edu.unac.iotunac.R;

/*** Created by Kevin Ortiz on 20/08/2018.*/

public class LenguajeListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private int MILISEGUNDOS_ESPERA = 500;

    public LenguajeListAdapter(Activity context, String[] itemname) {
        super(context, R.layout.list_item, itemname);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
    }

    public View getView(int posicion,View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item,null,true);
        final TextView txtTitle =  rowView.findViewById(R.id.pregunta);;
        TextView si = rowView.findViewById(R.id.si);
        Switch no =  rowView.findViewById(R.id.no);
        txtTitle.setText(itemname[posicion]);
        no.setChecked(false);
        no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                txtTitle.setText("No apto para continuar");
                esperarYCerrar(MILISEGUNDOS_ESPERA);
            }
        });
        return rowView;
    }
     public void terminar(){
        AlertDialog.Builder aviso = new AlertDialog.Builder(getContext());
        aviso.setTitle("¡Lo sentimos!");
        aviso.setCancelable(false);
        aviso.setMessage(R.string.salir);
        aviso.setCancelable(false);
        aviso.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
               System.exit(0);
            }
        });
        aviso.show();
        }
    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
             terminar();
            }
        }, milisegundos);
    }
}