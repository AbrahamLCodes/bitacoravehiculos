package swtizona.androidapps.bpv.fragments.actionfragments.servicios;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.ServiciosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;

public class NewServicioFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText campos[];
    private TextView cancelar, registrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_new_servicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCampos(view);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.servicioNewBack:
                dismiss();
                break;
            case R.id.servicioNewOk:
                actionOk();
                break;
        }

    }

    private void initCampos(View v) {
        campos = new EditText[6];
        cancelar = v.findViewById(R.id.servicioNewBack);
        registrar = v.findViewById(R.id.servicioNewOk);
        initEditText(v);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void actionOk() {
        int i = 0;
        boolean flag = true;
        //Show input alert
        while (i < 6) {

            if (campos[i].getText().length() == 0) {
                Toast.makeText(getContext(), "Introduce el/la " + campos[i].getHint(), Toast.LENGTH_SHORT).show();
                flag = false;
                break;
            }

            i++;
        }
        if (flag) {
            actionInsert();
        }
    }

    private void actionInsert() {

        DataBaseController db = new DataBaseController(getContext());
        String[] rows = new String[6];
        //Get TextEdits values
        for (int i = 0; i < 6; i++) {
            if (i == 5) {
                //Validate non extra coment
                if (campos[i].getText().length() == 0) {
                    rows[i] = " ";
                } else {
                    rows[i] = campos[i].getText().toString();
                }
            } else {
                rows[i] = campos[i].getText().toString();
            }
        }

        if (checkForeignKey(
                campos[1].getText().toString()
                , campos[3].getText().toString()
                , campos[4].getText().toString())) {
            db.insert6Rows("SERVICIOS", rows);
            updateRAM(db);
        }
    }

    private boolean checkForeignKey(String auto, String taller, String producto) {
        DataBaseController db = new DataBaseController(getContext());
        boolean keyAuto = db.isForeignKey("AUTOS", "MATRICULA", auto);
        boolean keyTaller = db.isForeignKey("TALLERES", "TELEFONO", taller);
        boolean keyProducto = db.isForeignKey("PRODUCTOS", "MODELO", producto);

        db.close();

        if (!keyAuto) {
            Toast.makeText(getContext(), "No existe el auto en los registros", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!keyTaller) {
            Toast.makeText(getContext(), "No existe el taller en los registros", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!keyProducto) {
            Toast.makeText(getContext(), "No existe el producto en los registros", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    private void updateRAM(DataBaseController db) {
        //Updating RAM data
        Lists.initLists();
        db.updateLists();
        db.close();
        ServiciosActivity.updateUI();
        dismiss();
    }

    private void initEditText(View v) {
        int i = 0;
        while (i < 6) {
            int res = getResources().getIdentifier(
                    "servicioIn" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = v.findViewById(res);
            i++;
        }
    }
}
