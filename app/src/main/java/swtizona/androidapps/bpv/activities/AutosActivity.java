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

import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;
import swtizona.androidapps.bpv.fragments.actionfragments.autos.NewAutoFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.autos.InfoAutoFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.AutoAdapter;
import swtizona.androidapps.bpv.modeldata.Auto;

public class AutosActivity extends AppCompatActivity implements
        View.OnClickListener
        , AdapterView.OnItemClickListener {

    private static ListView listAutos;
    private ImageView backButton;
    private TextView nuevo, buscar;

    private static Auto[] autos;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        context = this;
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
        openInfoDialog(position);
    }

    private void initComponents() {
        backButton = findViewById(R.id.autosBack);
        listAutos = findViewById(R.id.listAutos);
        nuevo = findViewById(R.id.autoNuevo);
        buscar = findViewById(R.id.autoBuscar);

        Lists.initLists();
        DataBaseController db = new DataBaseController(getApplicationContext());
        db.updateLists();
        updateUI();
        db.close();

        backButton.setOnClickListener(this);
        listAutos.setOnItemClickListener(this);
        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }

    public static void updateUI() {
        initArray();
        listAutos.setAdapter(new AutoAdapter(
                context
                , R.layout.adapter_auto
                , autos));
    }

    private static void initArray() {
        autos = new Auto[Lists.getAutoList().size()];
        for (int i = 0; i < Lists.getAutoList().size(); i++) {
            autos[i] = Lists.getAutoList().get(i);
        }
    }


    private void openDialogFragment() {
        NewAutoFragment newAutoFragment = new NewAutoFragment(true);
        newAutoFragment.show(getSupportFragmentManager(), "Registrar auto");
    }

    private void openInfoDialog(int position) {
        InfoAutoFragment infoAutoFragment = new InfoAutoFragment(position);
        infoAutoFragment.show(getSupportFragmentManager(), "Info Dialog");
    }
}