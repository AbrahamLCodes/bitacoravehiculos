package swtizona.androidapps.bpv.fragments.actionfragments.servicios;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.ServiciosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;

public class NewServicioFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText campos[];
    private TextView cancelar, registrar;
    private DatePicker datePicker;

    //Variables to identify if user wants to insert a new car or update it
    boolean insert;
    String[] values;

    public NewServicioFragment(boolean insert) {
        this.insert = insert;
    }

    public NewServicioFragment(boolean insert, String[] values) {
        this.insert = insert;
        this.values = values;
    }

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
        datePicker = v.findViewById(R.id.servicioIn2);
        initEditText(v);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void actionOk() {
        int i = 0;
        boolean flag = true;
        //Show input alert
        while (i < 6) {
            if (i != 2) {
                if (campos[i].getText().length() == 0) {
                    Toast.makeText(getContext(), "Introduce el/la " + campos[i].getHint(), Toast.LENGTH_SHORT).show();
                    flag = false;
                    break;
                }
            }
            i++;
        }
        if (flag) {
            actionInsert();
        }
    }

    private void actionInsert() {

        DataBaseController db = new DataBaseController(getContext());
        String[] rows = new String[9];
        //Get TextEdits values

        rows[0] = campos[0].getText().toString();
        rows[1] = campos[1].getText().toString();

        rows[2] = ""+datePicker.getDayOfMonth();
        rows[3] = ""+datePicker.getMonth();
        rows[4] = ""+datePicker.getYear();

        String d = rows[2];
        String m = rows[3];

        if(Integer.parseInt(d) < 10){
            d = "0"+datePicker.getDayOfMonth();
        }
        if(Integer.parseInt(m) < 10){
            m = "0"+datePicker.getMonth();
        }

        rows[5] = rows[4]+"-"+m+"-"+d;
        rows[6] = campos[3].getText().toString();
        rows[7] = campos[4].getText().toString();
        if (campos[5].getText().length() == 0) {
            rows[8] = " ";
        } else {
            rows[8] = campos[5].getText().toString();
        }

        if (checkForeignKey(
                campos[1].getText().toString()
                , campos[3].getText().toString()
                , campos[4].getText().toString())) {
            if (insert) {
                db.insert9Rows("SERVICIOS", rows);
            } else {
                db.update(
                        "SERVICIOS",
                        "SERVICIO = '" + rows[0] + "', " +
                                "AUTO = '" + rows[1] + "', " +
                                "DIA = '" + rows[2] + "', " +
                                "MES = '" + rows[3] + "', " +
                                "ANIO = '" + rows[4] + "', " +
                                "TALLER = '" + rows[6] + "', " +
                                "PRODUCTOS = '" + rows[7] + "', " +
                                "COMENTARIO = '" + rows[8] + "'",
                        "SERVICIO",
                        "'" + values[0] + "'");
            }
            updateRAM();
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

    private void updateRAM() {
        //Updating RAM data
        ServiciosActivity.updateUI();
        dismiss();
    }

    private void initEditText(View v) {
        int i = 0;
        while (i < 6) {
            //To avoid datePicker
            if (i != 2) {
                int res = getResources().getIdentifier(
                        "servicioIn" + (i)
                        , "id"
                        , getActivity().getPackageName());
                campos[i] = v.findViewById(res);
                //Set textview content
                if (!insert) {
                    if(i > 2){
                        campos[i].setText(values[i + 2]);
                    }else{
                        campos[i].setText(values[i]);
                    }
                }
            }else{
                if(!insert){
                    int dia = Integer.parseInt(values[2]);
                    int mes = Integer.parseInt(values[3]);
                    int anio = Integer.parseInt(values[4]);
                    datePicker.updateDate(anio, mes, dia);
                }
            }
            i++;
        }
    }
}
