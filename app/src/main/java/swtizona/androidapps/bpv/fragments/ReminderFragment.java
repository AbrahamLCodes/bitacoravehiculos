package swtizona.androidapps.bpv.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import swtizona.androidapps.bpv.MainActivity;
import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.fragments.actionfragments.recordatorios.InfoRecordatorioFragment;
import swtizona.androidapps.bpv.modeladapter.RecordatorioAdapter;
import swtizona.androidapps.bpv.modeldata.Recordatorio;


public class ReminderFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static ListView listView;
    private static Context con;
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre"};

    public ReminderFragment(Context context) {
        this.con = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.recordatorios);
        listView.setOnItemClickListener(this);
        updateUI();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DataBaseController db = new DataBaseController(con);
        ArrayList<Recordatorio> li = new ArrayList<>();
        li = db.ultimateAllSelect("RECORDATORIOS", li);
        Recordatorio recordatorio = li.get(position);

        String titulo = recordatorio.getServicio();
        String fecha =
                recordatorio.getDia() + " de "
                        + meses[Integer.parseInt(recordatorio.getMes())] + " del " +
                        recordatorio.getAnio();
        String hora = recordatorio.getHora()+":"+recordatorio.getMinuto();
        String auto = recordatorio.getAuto();

        InfoRecordatorioFragment ir = new InfoRecordatorioFragment(titulo, fecha, hora, auto);
        ir.show(getFragmentManager(), "Info");
    }

    public static void updateUI() {
        DataBaseController db = new DataBaseController(con);
        ArrayList<Recordatorio> li = new ArrayList<>();
        li = db.ultimateAllSelect("RECORDATORIOS", li);
        Recordatorio[] recordatorios = new Recordatorio[li.size()];

        for (int i = 0; i < recordatorios.length; i++) {
            recordatorios[i] = li.get(i);
        }

        listView.setAdapter(new RecordatorioAdapter(
                con,
                R.layout.adapter_recordatorio,
                recordatorios
        ));
        db.close();
    }
}