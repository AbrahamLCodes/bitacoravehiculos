package swtizona.androidapps.bpv.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import swtizona.androidapps.bpv.Fragments.ActionFragments.productos.InfoProductoFragment;
import swtizona.androidapps.bpv.Fragments.ActionFragments.productos.NewProductoFragment;
import swtizona.androidapps.bpv.R;

public class ProductosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private ListView lista;
    private ImageView back;
    private String[] productos = {"Filtro Gasolina Ranger", "Filtro aceite Ranger", "Bujias Ka"};

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
        Toast.makeText(this, ""+productos[position], Toast.LENGTH_SHORT).show();
        InfoProductoFragment infoProductoFragment = new InfoProductoFragment();
        infoProductoFragment.show(getSupportFragmentManager(), "Producto Info");
    }

    private void openNuevoDialog(){
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

        lista.setAdapter(new ArrayAdapter<>(
                getApplicationContext()
                , android.R.layout.simple_list_item_1
                , productos));
    }
}