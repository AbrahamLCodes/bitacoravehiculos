package swtizona.androidapps.bpv.fragments.actionfragments.recordatorios;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import swtizona.androidapps.bpv.R;


public class InfoRecordatorioFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView titulo, fecha, hora, auto;
    private TextView regresar, editar, eliminar;
    private String t,f, h, a;

    public InfoRecordatorioFragment(String t, String f, String h, String a){
        this.t = t;
        this.f = f;
        this.h = h;
        this.a = a;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_recordatorio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regresar:
                dismiss();
                break;
            case R.id.editar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eliminar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initComponents(View v){
        titulo = v.findViewById(R.id.infoRecordatorioTitulo);
        fecha = v.findViewById(R.id.infoRecordatorioFecha);
        hora = v.findViewById(R.id.infoRecordatorioHora);
        auto = v.findViewById(R.id.infoRecordatorioAuto);

        regresar = v.findViewById(R.id.regresar);
        editar = v.findViewById(R.id.editar);
        eliminar = v.findViewById(R.id.eliminar);

        titulo.setText(""+t);
        fecha.setText(""+f);
        hora.setText("Hora: "+h);
        auto.setText("Auto: "+a);

        regresar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }
}