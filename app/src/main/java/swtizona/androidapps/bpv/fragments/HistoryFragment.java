package swtizona.androidapps.bpv.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.modeladapter.ServicioAdapter;
import swtizona.androidapps.bpv.modeladapter.SpinnerDropAdapter;
import swtizona.androidapps.bpv.modeldata.Servicio;


public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ListView lista;
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd/MM/yyyy");
    private static final Date invalidDate = new Date(0);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) { }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setListaAdapter("AUTO", "ASC");
                break;
            case 1:
                setListaAdapter("AUTO", "DESC");
                break;
            case 2:
                setListaAdapter("FECHA", "DESC");
                break;
            case 3:
                setListaAdapter("FECHA", "ASC");
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

        String [] items = {
                "Por auto ascendente",
                "Por auto descendente",
                "Del más nuevo al maś antiguo",
                "Del más antiguo al maś nuevo"};

        SpinnerDropAdapter sda = new SpinnerDropAdapter(
                getActivity(),
                items,
                Color.parseColor("#28282C"));
        spinner.setAdapter(sda);

        spinner.setOnItemSelectedListener(this);
    }

    private void initComponents(View v) {
        spinner = v.findViewById(R.id.spinner);
        lista = v.findViewById(R.id.lista);

        lista.setOnItemClickListener(this);
        setListaAdapter("AUTO", "ASC");
        initSpinner();
    }
}