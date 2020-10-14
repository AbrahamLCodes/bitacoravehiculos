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
import swtizona.androidapps.bpv.modeldata.Auto;

public class AutoAdapter extends ArrayAdapter<Auto> {

    private Auto[] objects;
    private Context context;
    private int resource;

    public AutoAdapter(@NonNull Context context, int resource, @NonNull Auto[] objects){
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
        TextView titulo;
        TextView motor;
        TextView matricula;

        //Recuperando componentes
        image = convertView.findViewById(R.id.autoAdapterImage);
        titulo = convertView.findViewById(R.id.autoAdapterTitle);
        motor = convertView.findViewById(R.id.autoAdapterEngine);
        matricula = convertView.findViewById(R.id.autoAdapterPlate);

        String [] values = buildStrings(objects[position]);
        //titulo = 0, motor = 1, matricula = 2

        titulo.setText(values[0]);
        motor.setText(values[1]);
        matricula.setText(values[2]);

        return convertView;
    }

    private String []buildStrings(Auto auto){

        //Ford Ranger 2007
        String titulo = auto.getFabricante() + " " + auto.getModelo() + " " + auto.getAno();
        // Motor: 2.3L
        String motor = "Motor: "+auto.getMotor();
        // Matricula: EB76787
        String matricula = "Matricula: "+auto.getMatricula();

        String [] values = {titulo, motor, matricula};
        return values;
    }

}
