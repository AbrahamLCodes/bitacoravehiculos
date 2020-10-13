package swtizona.androidapps.bpv.fragments.actionfragments.autos;

import android.app.Dialog;
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

public class InfoAutoFragment extends AppCompatDialogFragment implements View.OnClickListener{

    private TextView regresar, buscar, eliminar, editar;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_auto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.autoInfoRegresar:
                dismiss();
                break;
            case R.id.autoInfoBuscar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.autoInfoEliminar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.autoInfoEditar:
                NewAutoFragment newAutoFragment = new NewAutoFragment();
                newAutoFragment.show(getFragmentManager(), " Aaaa");
                dismiss();
                break;
        }
    }

    private void initComponents(View v){
        regresar = v.findViewById(R.id.autoInfoRegresar);
        buscar = v.findViewById(R.id.autoInfoBuscar);
        eliminar = v.findViewById(R.id.autoInfoEliminar);
        editar = v.findViewById(R.id.autoInfoEditar);

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        editar.setOnClickListener(this);
    }
}
