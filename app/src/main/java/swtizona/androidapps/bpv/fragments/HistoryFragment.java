package swtizona.androidapps.bpv.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.modeladapter.RecordatorioAdapter;
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeldata.Servicio;


public class HistoryFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView buscar;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buscar) {
            Toast.makeText(getContext(), "Construyendo accion", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setListaAdapter("AUTO","ASC");
                break;
            case 1:
                setListaAdapter("AUTO","DESC");
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setListaAdapter(String columna, String asc) {
        DataBaseController db = new DataBaseController(getContext());
        ArrayList<Servicio> arrayList = new ArrayList<>();

        arrayList = db.orderBySelect("SERVICIOS", columna, asc, arrayList);
        Servicio[] servicios = new Servicio[arrayList.size()];


        for (int i = 0; i < arrayList.size(); i++) {
            servicios[i] = arrayList.get(i);
        }

        lista.setAdapter(new ServicioAdapter(
                getContext(),
                R.layout.adapter_servicio,
                servicios
        ));
    }

    private void initSpinner() {
        List<String> spinnerArray = new ArrayList<>();


        spinnerArray.add("Por auto ascendente");
        spinnerArray.add("Por auto descendente");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                spinnerArray
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void initComponents(View v) {
        spinner = v.findViewById(R.id.spinner);
        buscar = v.findViewById(R.id.buscar);
        lista = v.findViewById(R.id.lista);

        buscar.setOnClickListener(this);
        lista.setOnItemClickListener(this);
        setListaAdapter("AUTO","ASC");
        initSpinner();
    }
}