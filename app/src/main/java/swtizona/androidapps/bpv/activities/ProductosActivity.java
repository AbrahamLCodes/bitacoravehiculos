package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import swtizona.androidapps.bpv.fragments.actionfragments.productos.InfoProductoFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.productos.NewProductoFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.ProductoAdapter;
import swtizona.androidapps.bpv.modeldata.Producto;

public class ProductosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private ListView lista;
    private ImageView back;
    //private String[] productos = {"Filtro Gasolina Ranger", "Filtro aceite Ranger", "Bujias Ka"};

    private Producto[] productos = {
            new Producto(
                    "Filtro de gasolina",
                    "Ford Ranger 2007",
                    "15B789",
                    "GHONER",
                    "154678",
                    "Es de 1 aguja"),
            new Producto(
                    "Filtro de aceite",
                    "Ford Ka 2003",
                    "14B457",
                    "DURALAST",
                    "1234789",
                    "Es mejor en Autozone"),
            new Producto(
                    "Bujias",
                    "Ford Ranger 1988",
                    "457B23",
                    "GHONER",
                    "45987",
                    "No comprar en Refaccin!")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.productoNuevo:
                openNuevoDialog();
                break;
            case R.id.productoBuscar:
                break;
            case R.id.productosBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoProductoFragment infoProductoFragment = new InfoProductoFragment();
        infoProductoFragment.show(getSupportFragmentManager(), "Producto Info");
    }

    private void openNuevoDialog() {
        NewProductoFragment newProductoFragment = new NewProductoFragment();
        newProductoFragment.show(getSupportFragmentManager(), "Producto Nuevo");
    }

    private void initComponents() {
        nuevo = findViewById(R.id.productoNuevo);
        buscar = findViewById(R.id.productoBuscar);
        lista = findViewById(R.id.listaProductos);
        back = findViewById(R.id.productosBack);

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);

        lista.setAdapter(new ProductoAdapter(
                this
                , R.layout.adapter_producto
                , productos));
    }
}