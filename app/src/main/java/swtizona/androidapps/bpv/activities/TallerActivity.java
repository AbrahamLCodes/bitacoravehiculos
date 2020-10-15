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

import swtizona.androidapps.bpv.fragments.actionfragments.talleres.InfoTallerFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.talleres.NewTallerFragment;
import swtizona.androidapps.bpv.R;

public class TallerActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private TextView nuevo, buscar;
    private ListView lista;
    private ImageView back;
    private String[] talleres = {"Alfredo Armendariz", "Carlos el carpa", "Gil electrico"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);
        initComponents();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoTallerFragment infoTallerFragment = new InfoTallerFragment();
        infoTallerFragment.show(getSupportFragmentManager(), "Taller Info");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tallerNuevo:
                openNuevoDialog();
                break;
            case R.id.tallerBuscar:
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

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        back.setOnClickListener(this);

        lista.setAdapter(new ArrayAdapter<>(
                getApplicationContext()
                , android.R.layout.simple_list_item_1
                , talleres));
    }

}