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

import java.util.List;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.TallerActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;

public class InfoTallerFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, editar, eliminar, titulo;
    private TextView[] campos;
    private int pos;

    public InfoTallerFragment(int pos){
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
                delete();
                dismiss();
                break;
        }
    }

    private void delete() {
        DataBaseController db = new DataBaseController(getContext());
        db.delete("TALLERES", "TELEFONO", Lists.getTallerList().get(pos).getTelefono());
        updateRAM(db);
        db.close();
    }

    private void updateRAM(DataBaseController db) {
        Lists.initLists();
        db.updateLists();
        TallerActivity.updateUI();
    }

    private void openNewDialog(){
        NewTallerFragment newTallerFragment = new NewTallerFragment();
        newTallerFragment.show(getFragmentManager(),"Editar Taller");
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
        titulo.setText(Lists.getTallerList().get(pos).getTaller());

        //Building address String
        String direccion = campos[2].getText().toString() + " "
                + Lists.getTallerList().get(pos).getCalle() +" #"
                + Lists.getTallerList().get(pos).getNcalle() + "\nColonia "
                + Lists.getTallerList().get(pos).getColonia()+", "
                + Lists.getTallerList().get(pos).getCiudad() + " "
                + Lists.getTallerList().get(pos).getEstado();

        campos[0].setText(campos[0].getText() + " " + Lists.getTallerList().get(pos).getTelefono());
        campos[1].setText(campos[1].getText() + " " + direccion);
        campos[2].setText(campos[2].getText() + " " + Lists.getTallerList().get(pos).getComentario());
    }

    private void initComponents(View v){
        regresar = v.findViewById(R.id.tallerInfoRegresar);
        buscar = v.findViewById(R.id.tallerInfoBuscar);
        editar = v.findViewById(R.id.tallerInfoEditar);
        eliminar = v.findViewById(R.id.tallerInfoEliminar);
        titulo = v.findViewById(R.id.tituloInfoTaller);

        initCampos(v);
        setCampos();

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }


}
