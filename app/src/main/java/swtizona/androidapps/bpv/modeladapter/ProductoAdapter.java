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
import swtizona.androidapps.bpv.modeldata.Producto;

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private Producto[] objects;
    private Context context;
    private int resource;

    public ProductoAdapter(@NonNull Context context, int resource, @NonNull Producto[] objects){
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
        TextView marca;
        TextView modelo;
        TextView vehiculos;

        //Recuperando componentes
        image = convertView.findViewById(R.id.productoAdapterImage);
        nombre = convertView.findViewById(R.id.productoAdapterName);
        marca = convertView.findViewById(R.id.productoAdapterMarca);
        modelo = convertView.findViewById(R.id.productoAdapterModel);
        vehiculos = convertView.findViewById(R.id.productoAdapterVehicles);

        String [] values = buildStrings(objects[position]);
        //Nombre = 0, Marca = 1, Modelo = 2, Vehiculos = 3

        nombre.setText(values[0]);
        marca.setText(values[1]);
        modelo.setText(values[2]);
        vehiculos.setText(values[3]);

        return convertView;
    }

    private String []buildStrings(Producto producto){

        /*
            Filtro de gasolina
            Marca: Ghoner
            Modelo: 15B78
            vehiculo(s): Ranger 2007
        */

        //Filtro de gasolina
        String nombre = producto.getNombre();

        //Marca: Ghoner
        String marca = producto.getMarca();

        // Modelo: 15B78
        String modelo = "Modelo: "+producto.getModelo();

        // Vehiculo(s): EB76787
        String vehiculos = "Vehiculo(s): "+producto.getAuto();

        String [] values = {nombre, marca, modelo, vehiculos};
        return values;
    }
}
