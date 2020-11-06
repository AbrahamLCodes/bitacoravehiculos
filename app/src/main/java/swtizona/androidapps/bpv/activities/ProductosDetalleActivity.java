package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.modeladapter.AutoAdapter;
import swtizona.androidapps.bpv.modeladapter.ProductoAdapter;
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeldata.Auto;
import swtizona.androidapps.bpv.modeldata.Producto;
import swtizona.androidapps.bpv.modeldata.Servicio;

public class ProductosDetalleActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView autos, servicios;
    private ListView lista;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_detalle);

        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.productoDetalleAuto:
                setListaAuto();
                break;
            case R.id.productoDetalleServicio:
                setListaServicio();
                break;
            case R.id.detalleBack:
                onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void setListaAuto(){

        DataBaseController db = new DataBaseController(getApplicationContext());

        ArrayList<Auto> li = new ArrayList<>();
        li = db.customSelect("AUTOS", "MATRICULA", "A", li);
        Auto[] autos = new Auto[li.size()];

        for (int i = 0 ; i < autos.length ; i++){
            autos[i] = li.get(i);
        }

        lista.setAdapter(new AutoAdapter(
                this,
                R.layout.adapter_auto,
                autos
        ));
        db.close();
    }

    private void setListaServicio(){
        DataBaseController db = new DataBaseController(getApplicationContext());

        ArrayList<Servicio> li = new ArrayList<>();
        li = db.customSelect("SERVICIOS", "PRODUCTOS", "12b", li);
        Servicio[] servicios = new Servicio[li.size()];

        for (int i = 0 ; i < servicios.length ; i++){
            servicios[i] = li.get(i);
        }

        lista.setAdapter(new ServicioAdapter(
                this,
                R.layout.adapter_servicio,
                servicios
        ));
        db.close();
    }

    private void initComponents(){
        autos = findViewById(R.id.productoDetalleAuto);
        servicios = findViewById(R.id.productoDetalleServicio);
        lista = findViewById(R.id.productoDetalleList);
        back = findViewById(R.id.detalleBack);

        autos.setOnClickListener(this);
        servicios.setOnClickListener(this);
        back.setOnClickListener(this);
    }
}