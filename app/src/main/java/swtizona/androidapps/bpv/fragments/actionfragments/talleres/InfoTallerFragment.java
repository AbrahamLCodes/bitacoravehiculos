package swtizona.androidapps.bpv.fragments.actionfragments.talleres;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.TallerActivity;
import swtizona.androidapps.bpv.activities.TallerDetalleActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;
import swtizona.androidapps.bpv.modeldata.Taller;

public class InfoTallerFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, editar, eliminar, titulo;
    private TextView[] campos;
    private int pos;
    private Taller taller;
    private ArrayList<Taller> li = new ArrayList<>();
    String[] values;

    public InfoTallerFragment(int pos) {
        this.pos = pos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_taller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        campos = new TextView[4];
        values = new String[8];
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tallerInfoRegresar:
                dismiss();
                break;
            case R.id.tallerInfoBuscar:
                Intent intent = new Intent(getActivity(), TallerDetalleActivity.class);
                intent.putExtra("id", taller.getTelefono());
                startActivity(intent);
                dismiss();
                break;
            case R.id.tallerInfoEditar:
                openNewDialog();
                dismiss();
                break;
            case R.id.tallerInfoEliminar:
                delete();
                dismiss();
                break;
        }
    }

    private void delete() {
        DataBaseController db = new DataBaseController(getContext());
        db.delete("TALLERES", "TELEFONO", taller.getTelefono());
        TallerActivity.updateUI();
        db.close();
    }

    private void openNewDialog() {
        NewTallerFragment newTallerFragment = new NewTallerFragment(false, values);
        newTallerFragment.show(getFragmentManager(), "Editar Taller");
    }

    private void initCampos(View view) {
        int i = 0;
        while (i < 4) {
            int res = getResources().getIdentifier(
                    "taller" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = view.findViewById(res);
            i++;
        }
    }

    private void setCampos() {
        titulo.setText(taller.getTaller());

        //Building address String
        String direccion = campos[2].getText().toString() + " "
                + taller.getCalle() + " #"
                + taller.getNcalle() + "\nColonia "
                + taller.getColonia() + ", "
                + taller.getCiudad() + " "
                + taller.getEstado();

        campos[0].setText(campos[0].getText() + " " + taller.getTelefono());
        campos[1].setText(campos[1].getText() + " " + direccion);
        campos[2].setText(campos[2].getText() + " " + taller.getComentario());

        values[0] = taller.getTaller();
        values[1] = taller.getTelefono();
        values[2] = taller.getCalle();
        values[3] = taller.getNcalle();
        values[4] = taller.getColonia();
        values[5] = taller.getCiudad();
        values[6] = taller.getEstado();
        values[7] = taller.getComentario();
    }

    private void initComponents(View v) {
        regresar = v.findViewById(R.id.tallerInfoRegresar);
        buscar = v.findViewById(R.id.tallerInfoBuscar);
        editar = v.findViewById(R.id.tallerInfoEditar);
        eliminar = v.findViewById(R.id.tallerInfoEliminar);
        titulo = v.findViewById(R.id.tituloInfoTaller);

        DataBaseController db = new DataBaseController(getContext());
        li = db.ultimateAllSelect("TALLERES", li);
        taller = li.get(pos);

        initCampos(v);
        setCampos();

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }
}
