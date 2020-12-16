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
import swtizona.androidapps.bpv.fragments.actionfragments.servicios.InfoServicioFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.servicios.NewServicioFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeldata.Servicio;

public class ServiciosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private static ListView lista;
    private ImageView back;
    private static Servicio[] servicios;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        context = this;
        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.servicioNuevo:
                openNewDialog();
                break;
            case R.id.servicioBuscar:
                Toast.makeText(context, "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.servicioBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoServicioFragment infoServicioFragment = new InfoServicioFragment(position);
        infoServicioFragment.show(getSupportFragmentManager(), "Producto Info");
    }

    private void openNewDialog() {
        NewServicioFragment newServicioFragment = new NewServicioFragment(true);
        newServicioFragment.show(getSupportFragmentManager(), "Registar Servicio");
    }

    public static void updateUI() {
        initArray();
        lista.setAdapter(new ServicioAdapter(
                context
                , R.layout.adapter_servicio
                , servicios));
    }

    private static void initArray() {
        DataBaseController db = new DataBaseController(context);
        ArrayList<Servicio> li = new ArrayList<>();
        li = db.ultimateAllSelect("SERVICIOS", li);
        servicios = new Servicio[li.size()];

        for (int i = 0; i < li.size(); i++) {
            servicios[i] = li.get(i);
        }
    }

    private void initComponents() {
        nuevo = findViewById(R.id.servicioNuevo);
        buscar = findViewById(R.id.servicioBuscar);
        lista = findViewById(R.id.listaServicios);
        back = findViewById(R.id.servicioBack);

        DataBaseController db = new DataBaseController(getApplicationContext());
        updateUI();
        db.close();

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);
    }
}