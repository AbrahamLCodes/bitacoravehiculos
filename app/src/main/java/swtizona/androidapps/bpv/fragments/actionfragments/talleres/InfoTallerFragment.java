package swtizona.androidapps.bpv.fragments.actionfragments.talleres;

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

public class InfoTallerFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, editar, eliminar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_taller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tallerInfoRegresar:
                dismiss();
                break;
            case R.id.tallerInfoBuscar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tallerInfoEditar:
                openNewDialog();
                dismiss();
                break;
            case R.id.tallerInfoEliminar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void openNewDialog(){
        NewTallerFragment newTallerFragment = new NewTallerFragment();
        newTallerFragment.show(getFragmentManager(),"Editar Taller");
    }

    private void initComponents(View v){
        regresar = v.findViewById(R.id.tallerInfoRegresar);
        buscar = v.findViewById(R.id.tallerInfoBuscar);
        editar = v.findViewById(R.id.tallerInfoEditar);
        eliminar = v.findViewById(R.id.tallerInfoEliminar);

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }
}
