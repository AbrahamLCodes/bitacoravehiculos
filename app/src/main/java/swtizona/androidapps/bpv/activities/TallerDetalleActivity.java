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
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeldata.Servicio;

public class TallerDetalleActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener
        , View.OnClickListener {

    private ListView lista;
    private ImageView back;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller_detalle);
        initComponents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detalleBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void setListaServicio() {
        DataBaseController db = new DataBaseController(getApplicationContext());

        ArrayList<Servicio> li = new ArrayList<>();
        li = db.customSelect("SERVICIOS", "TALLER", id, li);
        Servicio[] servicios = new Servicio[li.size()];

        for (int i = 0; i < servicios.length; i++) {
            servicios[i] = li.get(i);
        }

        lista.setAdapter(new ServicioAdapter(
                this,
                R.layout.adapter_servicio,
                servicios
        ));
        db.close();
    }

    private void initComponents() {
        lista = findViewById(R.id.tallerDetalleList);
        back = findViewById(R.id.detalleBack);
        id = getIntent().getExtras().getString("id");
        back.setOnClickListener(this);
        setListaServicio();
    }
}