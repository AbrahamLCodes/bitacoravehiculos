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

import swtizona.androidapps.bpv.fragments.actionfragments.autos.NewAutoFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.autos.InfoAutoFragment;
import swtizona.androidapps.bpv.R;

public class AutosActivity extends AppCompatActivity implements
        View.OnClickListener
        , AdapterView.OnItemClickListener {

    private String[] autos = {"Ford Ranger 2007", "Dodge RAM 2003", "Mitsubishi Mirage 2017"};
    private ListView listAutos;
    private ImageView backButton;
    private TextView nuevo, buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);
        initComponents();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.autosBack:
                onBackPressed();
                break;
            case R.id.autoNuevo:
                openDialogFragment();
                break;
            case R.id.autoBuscar:
                Toast.makeText(this, "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + autos[position], Toast.LENGTH_SHORT).show();
        openInfoDialog();
    }

    private void initComponents() {
        backButton = findViewById(R.id.autosBack);

        listAutos = findViewById(R.id.listAutos);
        listAutos.setAdapter(new ArrayAdapter<>(
                getApplicationContext()
                , android.R.layout.simple_list_item_1
                , autos));

        backButton.setOnClickListener(this);
        listAutos.setOnItemClickListener(this);

        nuevo = findViewById(R.id.autoNuevo);
        buscar = findViewById(R.id.autoBuscar);


        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }

    private void openDialogFragment() {
        NewAutoFragment newAutoFragment = new NewAutoFragment();
        newAutoFragment.show(getSupportFragmentManager(), "Registrar auto");
    }

    private void openInfoDialog(){
        InfoAutoFragment infoAutoFragment = new InfoAutoFragment();
        infoAutoFragment.show(getSupportFragmentManager(), "Info Dialog");
    }
}