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

import swtizona.androidapps.bpv.fragments.actionfragments.servicios.InfoServicioFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.servicios.NewServicioFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeldata.Servicio;

public class ServiciosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private ListView lista;
    private ImageView back;
    //private String[] servicios = {"Cambio de aceite a Ranger 2007", "Cambio de bujías a Ranger 1988", "Cambio de bomba de agua a Ford Ka"};

    private Servicio[] servicios = {
            new Servicio(
                    "Cambio de aceite",
                    "Ford Ranger 2007",
                    "27 / Septiembre / 2020",
                    "Automotriz del norte",
                    "Filtro de Aceite Ghonner, Aceite Castrol 20w-20",
                    "Sin comentarios"),
            new Servicio(
                    "Cambio de bujias",
                    "Dodge RAM 2003",
                    "15 / Agosto / 2019",
                    "Carlos",
                    "8 bujias duralast",
                    "Sin comentarios"),
            new Servicio(
                    "Reparación de bomba del agua",
                    "Ford Ka 2003",
                    "10 / Julio / 2018",
                    "Alfredo Armendariz",
                    "Bomba de agua Duralast",
                    "Es más fácil commprar en Autozone"),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.servicioNuevo:
                openNewDialog();
                break;
            case R.id.servicioBuscar:
                break;
            case R.id.servicioBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoServicioFragment infoServicioFragment = new InfoServicioFragment();
        infoServicioFragment.show(getSupportFragmentManager(), "Producto Info");
    }

    private void openNewDialog() {
        NewServicioFragment newServicioFragment = new NewServicioFragment();
        newServicioFragment.show(getSupportFragmentManager(), "Registar Servicio");
    }

    private void initComponents() {
        nuevo = findViewById(R.id.servicioNuevo);
        buscar = findViewById(R.id.servicioBuscar);
        lista = findViewById(R.id.listaServicios);
        back = findViewById(R.id.servicioBack);

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);

        lista.setAdapter(new ServicioAdapter(
                this
                , R.layout.adapter_servicio
                , servicios));
    }
}