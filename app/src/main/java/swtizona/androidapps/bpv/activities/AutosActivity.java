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
import swtizona.androidapps.bpv.fragments.actionfragments.autos.NewAutoFragment;
import swtizona.androidapps.bpv.fragments.actionfragments.autos.InfoAutoFragment;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.modeladapter.AutoAdapter;
import swtizona.androidapps.bpv.modeldata.Auto;

public class AutosActivity extends AppCompatActivity implements
        View.OnClickListener
        , AdapterView.OnItemClickListener {

    private TextView nuevo, buscar;
    private static ListView lista;
    private ImageView back;


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

    public static void updateUI() {
        initArray();
        lista.setAdapter(new AutoAdapter(
                context
                , R.layout.adapter_auto
                , autos));
    }

    private static void initArray() {
        DataBaseController db = new DataBaseController(context);
        ArrayList<Auto> li = new ArrayList<>();
        li = db.ultimateAllSelect("AUTOS", li);
        autos = new Auto[li.size()];

        for (int i = 0; i < li.size(); i++) {
            autos[i] = li.get(i);
        }
        db.close();
    }


    private void openDialogFragment() {
        NewAutoFragment newAutoFragment = new NewAutoFragment(true);
        newAutoFragment.show(getSupportFragmentManager(), "Registrar auto");
    }

    private void openInfoDialog(int position) {
        InfoAutoFragment infoAutoFragment = new InfoAutoFragment(position);
        infoAutoFragment.show(getSupportFragmentManager(), "Info Dialog");
    }

    private void initComponents() {
        back = findViewById(R.id.autosBack);
        lista = findViewById(R.id.listAutos);
        nuevo = findViewById(R.id.autoNuevo);
        buscar = findViewById(R.id.autoBuscar);

        updateUI();
        back.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }
}