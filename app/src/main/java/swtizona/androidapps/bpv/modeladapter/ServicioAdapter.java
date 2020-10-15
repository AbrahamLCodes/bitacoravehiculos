package swtizona.androidapps.bpv.modeladapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeldata.Servicio;

public class ServicioAdapter extends ArrayAdapter <Servicio> {

    private Servicio[] objects;
    private Context context;
    private int resource;

    public ServicioAdapter(@NonNull Context context, int resource, @NonNull Servicio[] objects){
        super(context, resource, objects);

        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {//Primera vez que se ejecuta la aplicación
            convertView = ((Activity) context).getLayoutInflater().inflate(resource, parent, false);
        }

        //Declarando componentes
        ImageView image;
        TextView nombre;
        TextView auto;
        TextView fecha;
        TextView taller;

        //Recuperando componentes
        image = convertView.findViewById(R.id.servicioAdapterImage);

        nombre = convertView.findViewById(R.id.servicioAdapterName);
        auto = convertView.findViewById(R.id.servicioAdapterAuto);
        fecha = convertView.findViewById(R.id.servicioAdapterFecha);
        taller = convertView.findViewById(R.id.servicioAdapterTaller);

        String [] values = buildStrings(objects[position]);
        //Nombre = 0, Auto = 1, fecha = 2, Taller = 3

        nombre.setText(values[0]);
        auto.setText(values[1]);
        fecha.setText(values[2]);
        taller.setText(values[3]);

        return convertView;
    }


    private String []buildStrings(Servicio servicio){

        /*
            Cambio de aceite
            Ford Ranger 2007
            27/ Septiembre / 2020
            Automotriz del norte
        */

        //Cambio de aciete
        String nombre = servicio.getServicio();

        //Auto
        String auto = servicio.getAutomovil();

        //Fecha
        String fecha = servicio.getFecha();

        //Taller
        String taller = servicio.getTaller();

        String [] values = {nombre, auto, fecha, taller};
        return values;
    }
}
