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
import swtizona.androidapps.bpv.fragments.actionfragments.talleres.InfoTallerFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.talleres.NewTallerFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.TallerAdapter;
import swtizona.androidapps.bpv.modeldata.Taller;

public class TallerActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private static ListView lista;
    private ImageView back;
    //private String[] talleres = {"Alfredo Armendariz", "Carlos el carpa", "Gil electrico"};
    /*
    private Taller[] talleres = {
            new Taller(
                    "Alfredo Armendáriz"
                    , "Aldama"
                    , "112"
                    , "Centro"
                    , "Camargo"
                    , "Chihuahua"
                    , "(648)122-7066"
                    , "Es raterillos el wey"),
            new Taller(
                    "Carlos el carpa"
                    , "Pablo Guinter"
                    , "345"
                    , "Arbol Grande"
                    , "Camargo"
                    , "Chihuahua"
                    , "(648)109-0459"
                    , "Sí le sabe a las Rangers"),
            new Taller(
                    "Perea Clutch y Embrague"
                    , "José María Iglesias"
                    , "504"
                    , "Granjas"
                    , "Chihuahua"
                    , "Chihuahua"
                    , "(614)140-4794"
                    , "Es muy amable"),
    };
    */

    private static Taller[] talleres;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        context = this;
        initComponents();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoTallerFragment infoTallerFragment = new InfoTallerFragment(position);
        infoTallerFragment.show(getSupportFragmentManager(), "Taller Info");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tallerNuevo:
                openNuevoDialog();
                break;
            case R.id.tallerBuscar:
                Toast.makeText(context, "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.talleresBack:
                onBackPressed();
                break;
        }
    }

    private void openNuevoDialog() {
        NewTallerFragment newTallerFragment = new NewTallerFragment();
        newTallerFragment.show(getSupportFragmentManager(), "Nuevo Taller");

    }

    private void initComponents() {
        nuevo = findViewById(R.id.tallerNuevo);
        buscar = findViewById(R.id.tallerBuscar);
        lista = findViewById(R.id.listaTalleres);
        back = findViewById(R.id.talleresBack);

        Lists.initLists();
        DataBaseController db = new DataBaseController(getApplicationContext());
        db.updateLists();
        updateUI();
        db.close();

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);

        lista.setAdapter(new TallerAdapter(
                this
                , R.layout.adapter_taller
                , talleres));
    }

    public static void updateUI() {
        initArray();
        lista.setAdapter(new TallerAdapter(
                    context
                , R.layout.adapter_taller
                , talleres));
    }

    private static void initArray() {
        talleres = new Taller[Lists.getTallerList().size()];
        for (int i = 0; i < Lists.getTallerList().size(); i++) {
            talleres[i] = Lists.getTallerList().get(i);
        }
    }

}