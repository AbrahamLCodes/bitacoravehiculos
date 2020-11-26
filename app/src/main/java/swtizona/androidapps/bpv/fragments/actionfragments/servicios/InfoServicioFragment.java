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
import swtizona.androidapps.bpv.modeldata.Servicio;

public class InfoServicioFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, editar, eliminar, titulo;
    private TextView[] campos;
    private int pos;
    String values[];
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre"};

    public InfoServicioFragment(int pos) {
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
        values = new String[8];
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.servicioInfoRegresar:
                dismiss();
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

    private void openNewDialog() {
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

        Servicio servicio = Lists.getServicioList().get(pos);
        String fecha =
                servicio.getDia() + "/" +
                        meses[Integer.parseInt(servicio.getMes())]+"/"+
                        servicio.getAnio();

                campos[0].setText(campos[0].getText() + " " + servicio.getAutomovil());
        campos[1].setText(campos[1].getText() + " " + fecha);
        campos[2].setText(campos[2].getText() + " " + servicio.getTaller());
        campos[3].setText(campos[3].getText() + " " + servicio.getProductos());
        campos[4].setText(campos[4].getText() + " " + servicio.getComentario());

        values[0] = servicio.getServicio();
        values[1] = servicio.getAutomovil();
        values[2] = servicio.getDia();
        values[3] = servicio.getMes();
        values[4] = servicio.getAnio();
        values[5] = servicio.getTaller();
        values[6] = servicio.getProductos();
        values[7] = servicio.getComentario();
    }

    private void initComponents(View v) {
        regresar = v.findViewById(R.id.servicioInfoRegresar);
        editar = v.findViewById(R.id.servicioInfoEditar);
        eliminar = v.findViewById(R.id.servicioInfoEliminar);
        titulo = v.findViewById(R.id.servicioInfoTitulo);

        initCampos(v);
        setCampos();

        regresar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }
}
