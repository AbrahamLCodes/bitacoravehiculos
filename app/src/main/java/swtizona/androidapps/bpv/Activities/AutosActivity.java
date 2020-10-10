package swtizona.androidapps.bpv.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import swtizona.androidapps.bpv.Fragments.ActionFragments.FragmentNew;
import swtizona.androidapps.bpv.R;

public class AutosActivity extends AppCompatActivity implements
        View.OnClickListener
        , AdapterView.OnItemClickListener {

    private String[] autos = {"Ford Ranger 2007", "Dodge RAM 2003", "Mitsubishi Mirage 2017"};
    private ListView listAutos;
    private ImageView backButton;
    private TextView nuevo, buscar, editar, eliminar;

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
            case R.id.autoEditar:
                Toast.makeText(this, "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.autoEliminar:
                Toast.makeText(this, "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + autos[position], Toast.LENGTH_SHORT).show();
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
        editar = findViewById(R.id.autoEditar);
        eliminar = findViewById(R.id.autoEliminar);

        nuevo.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }

    private void openDialogFragment() {
        FragmentNew fragmentNew = new FragmentNew();
        fragmentNew.show(getSupportFragmentManager(), "Registrar auto");
    }
}