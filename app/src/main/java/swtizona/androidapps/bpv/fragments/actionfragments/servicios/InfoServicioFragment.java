package swtizona.androidapps.bpv.fragments.actionfragments.servicios;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import swtizona.androidapps.bpv.R;

public class InfoServicioFragment extends AppCompatDialogFragment implements View.OnClickListener{

    private TextView regresar, buscar, editar, eliminar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_servicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.servicioInfoRegresar:
                dismiss();
                break;
            case R.id.servicioInfoBuscar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.servicioInfoEditar:
                openNewDialog();
                dismiss();
                break;
            case R.id.servicioInfoEliminar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void initComponents(View v){
        regresar = v.findViewById(R.id.servicioInfoRegresar);
        buscar = v.findViewById(R.id.servicioInfoBuscar);
        editar = v.findViewById(R.id.servicioInfoEditar);
        eliminar = v.findViewById(R.id.servicioInfoEliminar);

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }

    private void openNewDialog(){
        NewServicioFragment newServicioFragment = new NewServicioFragment();
        newServicioFragment.show(getFragmentManager(), "Registrar Servicio");
    }
}
