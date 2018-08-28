package co.edu.unac.iotunac.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import co.edu.unac.iotunac.R;

/**
 * Created by Kevin Ortiz on 20/08/2018.
 */
public class LenguajeListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;

    public LenguajeListAdapter(Activity context, String[] itemname) {
        super(context, R.layout.list_item, itemname);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
    }

    public View getView(int posicion,View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item,null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.pregunta);;
        TextView si = (TextView) rowView.findViewById(R.id.si);
        Switch no = (Switch) rowView.findViewById(R.id.no);
        txtTitle.setText(itemname[posicion]);

        return rowView;
    }
}
