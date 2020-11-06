package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.modeladapter.ProductoAdapter;
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeldata.Producto;
import swtizona.androidapps.bpv.modeldata.Servicio;

public class AutoDetallesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView productos, servicios;
    private ListView lista;
    private ImageView back;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_detalles);

        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.autoDetalleProducto:
                setListaProducto();
                break;
            case R.id.autoDetalleServicio:
                setListaServicio();
                break;
            case R.id.detalleBack:
                onBackPressed();
        }
    }

    private void setListaProducto(){

        DataBaseController db = new DataBaseController(getApplicationContext());

        ArrayList<Producto> li = new ArrayList<>();
        li = db.customSelect("PRODUCTOS", "AUTO", id, li);
        Producto[] productos = new Producto[li.size()];

        for (int i = 0 ; i < productos.length ; i++){
            productos[i] = li.get(i);
        }

        lista.setAdapter(new ProductoAdapter(
                this,
                R.layout.adapter_producto,
                productos
        ));
        db.close();
    }

    private void setListaServicio(){
        DataBaseController db = new DataBaseController(getApplicationContext());

        ArrayList<Servicio> li = new ArrayList<>();
        li = db.customSelect("SERVICIOS", "AUTO", id, li);
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
        productos = findViewById(R.id.autoDetalleProducto);
        servicios = findViewById(R.id.autoDetalleServicio);
        lista = findViewById(R.id.autoDetalleList);
        back = findViewById(R.id.detalleBack);
        id = getIntent().getExtras().getString("id");

        productos.setOnClickListener(this);
        servicios.setOnClickListener(this);
        back.setOnClickListener(this);
    }
}