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
import swtizona.androidapps.bpv.activities.AutosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;

public class InfoAutoFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, eliminar, editar, titulo;
    private TextView[] campos;

    private int pos;

    public InfoAutoFragment(int position) {
        this.pos = position;
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
        campos = new TextView[3];
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autoInfoRegresar:
                dismiss();
                break;
            case R.id.autoInfoBuscar:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.autoInfoEliminar:
                delete();
                dismiss();
                break;
            case R.id.autoInfoEditar:
                NewAutoFragment newAutoFragment = new NewAutoFragment();
                newAutoFragment.show(getFragmentManager(), " Aaaa");
                dismiss();
                break;
        }
    }

    private void delete() {
        DataBaseController db = new DataBaseController(getContext());
        db.delete("AUTOS", "MATRICULA", Lists.getAutoList().get(pos).getMatricula());
        updateRAM(db);
        db.close();
    }

    private void updateRAM(DataBaseController db){
        //Updating RAM data
        Lists.initLists();
        db.updateLists();
        AutosActivity.updateUI();
    }

    private void initComponents(View v) {
        regresar = v.findViewById(R.id.autoInfoRegresar);
        buscar = v.findViewById(R.id.autoInfoBuscar);
        eliminar = v.findViewById(R.id.autoInfoEliminar);
        titulo = v.findViewById(R.id.tituloInfoAuto);
        editar = v.findViewById(R.id.autoInfoEditar);

        initCampos(v);
        setCampos();

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        editar.setOnClickListener(this);
    }

    private void initCampos(View view) {
        int i = 0;
        while (i < 3) {
            int res = getResources().getIdentifier(
                    "auto" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = view.findViewById(res);
            i++;
        }
    }

    private void setCampos() {
        titulo.setText(
                Lists.getAutoList().get(pos).getFabricante()
                        + " " +
                        Lists.getAutoList().get(pos).getModelo()
                        + " " +
                        Lists.getAutoList().get(pos).getAno());

        campos[0].setText(campos[0].getText() + " " + Lists.getAutoList().get(pos).getMotor());
        campos[1].setText(campos[1].getText() + " " + Lists.getAutoList().get(pos).getMatricula());
        campos[2].setText(campos[2].getText() + " " + Lists.getAutoList().get(pos).getComentario());

    }

}
