package swtizona.androidapps.bpv.modeladapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeldata.Recordatorio;

public class RecordatorioAdapter extends ArrayAdapter<Recordatorio> {

    private Recordatorio[] objects;
    private Context context;
    private int resource;
    private String [] meses = {
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"};

    public RecordatorioAdapter(@NonNull Context context, int resource, @NonNull Recordatorio[] objects) {
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

        TextView servicio, fecha, hora, auto;

        //Recuperando componentes
        servicio = convertView.findViewById(R.id.recordatorioAdapterServicio);
        fecha = convertView.findViewById(R.id.recordatorioAdapterFecha);
        hora = convertView.findViewById(R.id.recordatorioAdapterHora);
        auto = convertView.findViewById(R.id.recordatorioAdapterAuto);

        servicio.setText(buildStrings(position)[0]);
        fecha.setText(buildStrings(position)[1]);
        hora.setText(buildStrings(position)[2]);
        auto.setText(buildStrings(position)[3]);

        return convertView;
    }

    private String [] buildStrings (int pos){
        String[] info = new String[4];
        String mes = meses[Integer.parseInt(objects[pos].getMes())];

        info[0]= objects[pos].getServicio();
        info[1] = objects[pos].getDia() + " de "+mes+ " del "+objects[pos].getAnio();
        info[2] = "Hora: " + objects[pos].getHora()+ ":"+ objects[pos].getMinuto()+""+objects[pos].getAmpm();
        info[3] = "Vehiculo: "+ objects[pos].getAuto();
        return info;
    }

}
