package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;
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
    //private String[] servicios = {"Cambio de aceite a Ranger 2007", "Cambio de bujías a Ranger 1988", "Cambio de bomba de agua a Ford Ka"};

    /*
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
    */
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

    private void initComponents() {
        nuevo = findViewById(R.id.servicioNuevo);
        buscar = findViewById(R.id.servicioBuscar);
        lista = findViewById(R.id.listaServicios);
        back = findViewById(R.id.servicioBack);

        Lists.initLists();
        DataBaseController db = new DataBaseController(getApplicationContext());
        db.updateLists();
        updateUI();
        db.close();

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);

        lista.setAdapter(new ServicioAdapter(
                this
                , R.layout.adapter_servicio
                , servicios));
    }

    public static void updateUI() {
        initArray();
        lista.setAdapter(new ServicioAdapter(
                context
                , R.layout.adapter_servicio
                , servicios));
    }

    private static void initArray() {
        servicios = new Servicio[Lists.getServicioList().size()];
        for (int i = 0; i < Lists.getServicioList().size(); i++) {
            servicios[i] = Lists.getServicioList().get(i);
        }
    }

}