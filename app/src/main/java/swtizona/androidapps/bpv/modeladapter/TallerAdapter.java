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
import swtizona.androidapps.bpv.modeldata.Taller;

public class TallerAdapter extends ArrayAdapter<Taller> {

    private Taller[] objects;
    private Context context;
    private int resource;


    public TallerAdapter(@NonNull Context context, int resource, @NonNull Taller[] objects) {
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
        TextView direccion;
        TextView ciudad;
        TextView telefono;

        //Recuperando componentes
        image = convertView.findViewById(R.id.servicioAdapterImage);

        nombre = convertView.findViewById(R.id.tallerAdapterName);
        direccion = convertView.findViewById(R.id.tallerAdapterDir);
        ciudad = convertView.findViewById(R.id.tallerAdapterCiudad);
        telefono = convertView.findViewById(R.id.tallerAdapterTel);

        String[] values = buildStrings(objects[position]);
        //Nombre = 0, Direccion = 1, Ciudad = 2, Telefono = 3

        nombre.setText(values[0]);
        direccion.setText(values[1]);
        ciudad.setText(values[2]);
        telefono.setText(values[3]);

        return convertView;
    }

    private String[] buildStrings(Taller taller) {

        /*
        Alfredo Armendariz
        Aldama 112 Col. Centro
        Camargo, Chihuahua
        6481227000
        */

        //Alfredo Armendariz
        String nombre = taller.getTaller();

        //Aldama #112 Col. centro
        String direccion = taller.getCalle() +" "
                +taller.getNcalle() + " Col." + taller.getColonia();

        //Camargo, Chihuahua
        String ciudad = taller.getCiudad() + " " + taller.getEstado();

        //6481227000
        String tel = taller.getTelefono();

        String[] values = {nombre, direccion, ciudad, tel};
        return values;
    }
}


