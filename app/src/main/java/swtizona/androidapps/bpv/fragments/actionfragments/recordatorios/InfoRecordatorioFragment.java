package swtizona.androidapps.bpv.fragments.actionfragments.recordatorios;

import android.content.Intent;
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
import swtizona.androidapps.bpv.activities.RecordatorioActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;
import swtizona.androidapps.bpv.fragments.ReminderFragment;
import swtizona.androidapps.bpv.modeldata.Recordatorio;


public class InfoRecordatorioFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView titulo, fecha, hora, auto;
    private TextView regresar, editar, eliminar;
    private Recordatorio recordatorio;
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre"};
    String id,servicio,dia, me, anio, hor, minuto, aut, ap;


    public InfoRecordatorioFragment(Recordatorio recordatorio){
        this.recordatorio = recordatorio;
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
                editar();
                dismiss();
                break;
            case R.id.eliminar:
                eliminar();
                dismiss();
                break;
        }
    }

    private void editar(){
        Intent intent = new Intent(getActivity(), RecordatorioActivity.class);

        intent.putExtra("new", false);
        intent.putExtra("id",id);
        intent.putExtra("auto",aut);
        intent.putExtra("dia",dia);
        intent.putExtra("mes",me);
        intent.putExtra("anio",anio);
        intent.putExtra("hora",hor);
        intent.putExtra("min",minuto);
        intent.putExtra("ampm",ap);
        intent.putExtra("servicio",servicio);

        startActivity(intent);
    }

    private void eliminar(){
        DataBaseController db = new DataBaseController(getContext());
        db.delete("RECORDATORIOS","ID",id);
        Lists.initLists();
        db.updateLists();
        ReminderFragment.updateUI();
        db.close();
    }

    private void initComponents(View v){
        titulo = v.findViewById(R.id.infoRecordatorioTitulo);
        fecha = v.findViewById(R.id.infoRecordatorioFecha);
        hora = v.findViewById(R.id.infoRecordatorioHora);
        auto = v.findViewById(R.id.infoRecordatorioAuto);

        regresar = v.findViewById(R.id.regresar);
        editar = v.findViewById(R.id.editar);
        eliminar = v.findViewById(R.id.eliminar);

         id = recordatorio.getId();
         servicio = recordatorio.getServicio();
         dia = recordatorio.getDia();
         me = recordatorio.getMes();
         anio = recordatorio.getAnio();
         hor = recordatorio.getHora();
         minuto = recordatorio.getMinuto();
         aut = recordatorio.getAuto();
         ap = recordatorio.getAmpm();

        String t = servicio;
        String f =
                dia + " de "
                        + meses[Integer.parseInt(me)] + " del " +
                        anio;
        String h = hor+":"+minuto;
        String a = aut;

        titulo.setText(""+t);
        fecha.setText(""+f);
        hora.setText("Hora: "+h);
        auto.setText("Auto: "+a);

        regresar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }
}