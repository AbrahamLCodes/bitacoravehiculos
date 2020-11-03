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
import swtizona.androidapps.bpv.activities.ProductosActivity;
import swtizona.androidapps.bpv.activities.ServiciosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;

public class InfoServicioFragment extends AppCompatDialogFragment implements View.OnClickListener{

    private TextView regresar, buscar, editar, eliminar, titulo;
    private TextView [] campos;
    private int pos;
    String values[];

    public InfoServicioFragment(int pos){
        this.pos = pos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_servicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        campos = new TextView[5];
        values = new String[6];
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
                delete();
                dismiss();
                break;
        }

    }

    private void delete() {
        DataBaseController db = new DataBaseController(getContext());
        db.delete("SERVICIOS", "SERVICIO", Lists.getServicioList().get(pos).getServicio());
        updateRAM(db);
        db.close();
    }

    private void updateRAM(DataBaseController db) {
        Lists.initLists();
        db.updateLists();
        ServiciosActivity.updateUI();
    }

    private void openNewDialog(){
        NewServicioFragment newServicioFragment = new NewServicioFragment(false, values);
        newServicioFragment.show(getFragmentManager(), "Registrar Servicio");
    }

    private void initCampos(View view) {
        int i = 0;
        while (i < 5) {
            int res = getResources().getIdentifier(
                    "servicio" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = view.findViewById(res);
            i++;
        }
    }

    private void setCampos() {
        titulo.setText(Lists.getServicioList().get(pos).getServicio());

        campos[0].setText(campos[0].getText() + " " + Lists.getServicioList().get(pos).getAutomovil());
        campos[1].setText(campos[1].getText() + " " + Lists.getServicioList().get(pos).getFecha());
        campos[2].setText(campos[2].getText() + " " + Lists.getServicioList().get(pos).getTaller());
        campos[3].setText(campos[3].getText() + " " + Lists.getServicioList().get(pos).getProductos());
        campos[4].setText(campos[4].getText() + " " + Lists.getServicioList().get(pos).getComentario());

        values[0] = Lists.getServicioList().get(pos).getServicio();
        values[1] = Lists.getServicioList().get(pos).getAutomovil();
        values[2] = Lists.getServicioList().get(pos).getFecha();
        values[3] = Lists.getServicioList().get(pos).getTaller();
        values[4] = Lists.getServicioList().get(pos).getProductos();
        values[5] = Lists.getServicioList().get(pos).getComentario();
    }

    private void initComponents(View v){
        regresar = v.findViewById(R.id.servicioInfoRegresar);
        buscar = v.findViewById(R.id.servicioInfoBuscar);
        editar = v.findViewById(R.id.servicioInfoEditar);
        eliminar = v.findViewById(R.id.servicioInfoEliminar);
        titulo = v.findViewById(R.id.servicioInfoTitulo);

        initCampos(v);
        setCampos();

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }
}
