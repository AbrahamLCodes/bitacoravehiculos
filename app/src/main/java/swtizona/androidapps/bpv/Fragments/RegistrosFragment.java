package swtizona.androidapps.bpv.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import swtizona.androidapps.bpv.Activities.AutosActivity;
import swtizona.androidapps.bpv.Activities.ProductosActivity;
import swtizona.androidapps.bpv.Activities.TallerActivity;
import swtizona.androidapps.bpv.R;

public class RegistrosFragment extends Fragment implements View.OnClickListener {

    private TextView autos, talleres, pp, servicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registros, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autos = getActivity().findViewById(R.id.autosOpcion);
        talleres = getActivity().findViewById(R.id.talleresOpcion);
        pp = getActivity().findViewById(R.id.ppOpcion);
        servicio =  getActivity().findViewById(R.id.servicioOpcion);

        autos.setOnClickListener(this);
        talleres.setOnClickListener(this);
        pp.setOnClickListener(this);
        servicio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autosOpcion:
                autos();
                break;
            case R.id.talleresOpcion:
                talleres();
                break;
            case R.id.ppOpcion:
                pp();
                break;
            case R.id.servicioOpcion:
                servicios();
                break;
        }
    }

    private void autos() {
        startActivity(new Intent(getActivity(), AutosActivity.class));
        //getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void talleres() {
        startActivity(new Intent(getActivity(), TallerActivity.class));
    }

    private void pp() {
        startActivity(new Intent(getActivity(), ProductosActivity.class));
    }

    private void servicios() {
        Toast.makeText(getContext(), "Activity Servicios en construcción", Toast.LENGTH_SHORT).show();
    }

}