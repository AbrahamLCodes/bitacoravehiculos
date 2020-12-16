package swtizona.androidapps.bpv.fragments.actionfragments.autos;

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
import swtizona.androidapps.bpv.activities.AutoDetallesActivity;
import swtizona.androidapps.bpv.activities.AutosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;
import swtizona.androidapps.bpv.modeldata.Auto;

public class InfoAutoFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, eliminar, editar, titulo;
    private TextView[] campos;
    private Auto auto;
    private ArrayList<Auto> li = new ArrayList<>();
    private int pos;
    String[] values;


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
        values = new String[6];
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autoInfoRegresar:
                dismiss();
                break;
            case R.id.autoInfoBuscar:
                Intent intent = new Intent(getActivity(), AutoDetallesActivity.class);
                intent.putExtra("id", auto.getMatricula());
                startActivity(intent);
                dismiss();
                break;
            case R.id.autoInfoEditar:
                //Pasamos valores para mostrar en el NewAutoFragment
                NewAutoFragment newAutoFragment = new NewAutoFragment(false, values);
                newAutoFragment.show(getFragmentManager(), " Aaaa");
                dismiss();
                break;
            case R.id.autoInfoEliminar:
                delete();
                dismiss();
                break;
        }
    }

    private void delete() {
        DataBaseController db = new DataBaseController(getContext());
        db.delete("AUTOS", "MATRICULA", auto.getMatricula());
        AutosActivity.updateUI();
        db.close();
    }

    private void initComponents(View v) {
        regresar = v.findViewById(R.id.autoInfoRegresar);
        buscar = v.findViewById(R.id.autoInfoBuscar);
        eliminar = v.findViewById(R.id.autoInfoEliminar);
        titulo = v.findViewById(R.id.tituloInfoAuto);
        editar = v.findViewById(R.id.autoInfoEditar);

        DataBaseController db = new DataBaseController(getContext());

        li = db.ultimateAllSelect("AUTOS", li);
        auto = li.get(pos);

        initCampos(v);
        setCampos(auto);

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

    private void setCampos(Auto auto) {


        titulo.setText(
                auto.getFabricante()
                        + " " +
                        auto.getModelo()
                        + " " +
                        auto.getAno());

        campos[0].setText(campos[0].getText() + " " + auto.getMotor());
        campos[1].setText(campos[1].getText() + " " + auto.getMatricula());
        campos[2].setText(campos[2].getText() + " " + auto.getComentario());

        values[0] = auto.getFabricante();
        values[1] = auto.getModelo();
        values[2] = auto.getAno();
        values[3] = auto.getMotor();
        values[4] = auto.getMatricula();
        values[5] = auto.getComentario();
    }

}
