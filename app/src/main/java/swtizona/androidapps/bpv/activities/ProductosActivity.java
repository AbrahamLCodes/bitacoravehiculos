package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.fragments.actionfragments.productos.InfoProductoFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.productos.NewProductoFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.ProductoAdapter;
import swtizona.androidapps.bpv.modeldata.Producto;

public class ProductosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private static ListView lista;
    private ImageView back;

    private static Producto[] productos;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        context = this;
        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.productoNuevo:
                openNuevoDialog();
                break;
            case R.id.productoBuscar:
                Toast.makeText(context, "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.productosBack:
                onBackPressed();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openInfoDialog(position);
    }

    public static void updateUI() {
        initArray();
        lista.setAdapter(new ProductoAdapter(
                context
                , R.layout.adapter_producto
                , productos));
    }

    private static void initArray() {
        DataBaseController db = new DataBaseController(context);
        ArrayList<Producto> li = new ArrayList<>();
        li = db.ultimateAllSelect("PRODUCTOS", li);
        productos = new Producto[li.size()];

        for (int i = 0; i < li.size(); i++) {
            productos[i] = li.get(i);
        }
    }


    private void openNuevoDialog() {
        NewProductoFragment newProductoFragment = new NewProductoFragment(true);
        newProductoFragment.show(getSupportFragmentManager(), "Producto Nuevo");
    }

    private void openInfoDialog(int pos) {
        InfoProductoFragment infoProductoFragment = new InfoProductoFragment(pos);
        infoProductoFragment.show(getSupportFragmentManager(), "Producto Info");
    }

    private void initComponents() {
        nuevo = findViewById(R.id.productoNuevo);
        buscar = findViewById(R.id.productoBuscar);
        lista = findViewById(R.id.listaProductos);
        back = findViewById(R.id.productosBack);

        DataBaseController db = new DataBaseController(getApplicationContext());
        updateUI();
        db.close();

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);
    }
}