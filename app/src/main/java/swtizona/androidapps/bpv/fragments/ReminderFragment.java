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
import swtizona.androidapps.bpv.modeladapter.RecordatorioAdapter;
import swtizona.androidapps.bpv.modeldata.Recordatorio;


public class ReminderFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static ListView listView;
    private static Context con;

    public ReminderFragment(Context context){
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
        updateUI();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
        }
    }

    public static void updateUI(){
        DataBaseController db = new DataBaseController(con);
        ArrayList<Recordatorio> li = new ArrayList<>();
        li = db.ultimateAllSelect("RECORDATORIOS", li);
        Recordatorio[] recordatorios = new Recordatorio[li.size()];

        for(int i = 0; i < recordatorios.length ; i++){
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