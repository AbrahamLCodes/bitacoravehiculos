package swtizona.androidapps.bpv.fragments.actionfragments.servicios;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.List;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.ServiciosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.modeladapter.SpinnerDropAdapter;
import swtizona.androidapps.bpv.modeldata.Auto;

public class NewServicioFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText servicio, taller, productos, comentario;
    private TextView cancelar, registrar;
    private DatePicker datePicker;
    private Spinner spinner;

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
        initComponents(view);
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

    private void initSpinner(Boolean nuevo) {
        List<String> spinnerArray = new ArrayList<>();
        DataBaseController db = new DataBaseController(getContext());
        ArrayList<Auto> li = new ArrayList<>();
        li = db.ultimateAllSelect("AUTOS", li);
        db.close();

        int index = 0;
        spinnerArray.add("Elige el auto");

        for (int i = 0; i < li.size(); i++) {
            Auto auto = li.get(i);
            String autoItem = auto.getFabricante() + " " + auto.getModelo() + " " + auto.getAno() + " ID: " + auto.getMatricula();
            spinnerArray.add(autoItem);

            if (!nuevo) {
                //Configurar el Spinner para posicionarlo en el item del auto
                // guradado
                if (values[1].equals(auto.getMatricula())) {
                    index = i + 1;
                }
            }
        }

        //Hacer un arreglo de Autos para el SpinnerAdapter
        String[] items = new String[spinnerArray.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = spinnerArray.get(i);
        }

        SpinnerDropAdapter sda = new SpinnerDropAdapter(
                getActivity(),
                items,
                Color.parseColor("#28282C")
        );
        spinner.setAdapter(sda);

        //En caso de que halla que editar
        if (!nuevo) {
            spinner.setSelection(index);
        }
    }

    private void actionOk() {
        boolean flag = true;
        //Show input alert
        if (servicio.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Introduce el servicio", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (taller.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Introduce el taller", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (productos.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Introduce el producto", Toast.LENGTH_SHORT).show();
            flag = false;
        }

        if (flag) {
            actionInsert();
        }
    }

    private void actionInsert() {

        DataBaseController db = new DataBaseController(getContext());
        String[] rows = new String[9];
        //Get TextEdits values

        //Servicio
        rows[0] = servicio.getText().toString();

        //Obteniendo el ID del vehiculo segun el Spinner
        String matricula = "";
        ArrayList<Auto> li = new ArrayList<>();
        li = db.ultimateAllSelect("AUTOS", li);

        int spinnerPos = spinner.getSelectedItemPosition();
        if (spinnerPos == 0) {
            Toast.makeText(getContext(), "Elige un vehiculo", Toast.LENGTH_SHORT).show();
            spinner.requestFocus();
        } else {
            rows[1] = li.get(spinnerPos - 1).getMatricula();
        }

        rows[2] = "" + datePicker.getDayOfMonth();
        rows[3] = "" + datePicker.getMonth();
        rows[4] = "" + datePicker.getYear();

        String d = rows[2];
        String m = rows[3];

        if (Integer.parseInt(d) < 10) {
            d = "0" + datePicker.getDayOfMonth();
        }
        if (Integer.parseInt(m) < 10) {
            m = "0" + datePicker.getMonth();
        }

        rows[5] = rows[4] + "-" + m + "-" + d;

        //taller
        rows[6] = taller.getText().toString();
        //productos
        rows[7] = productos.getText().toString();
        if (comentario.getText().length() == 0) {
            rows[8] = " ";
        } else {
            rows[8] = comentario.getText().toString();
        }

        if (checkForeignKey(
                li.get(spinnerPos - 1).getMatricula()
                , taller.getText().toString()
                , productos.getText().toString())) {
            if (insert) {
                try {
                    db.insert9Rows("SERVICIOS", rows);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error al insertar datos. Checa bien tus datos", Toast.LENGTH_SHORT).show();
                    Log.wtf("INSERT ERROR", "ERROR: " + e.getMessage());
                }
            } else {
                try {
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
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error al insertar datos. Checa bien tus datos", Toast.LENGTH_SHORT).show();
                    Log.wtf("UPDATE ERROR", "ERROR: " + e.getMessage());
                }
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

    private void initComponents(View v) {
        cancelar = v.findViewById(R.id.servicioNewBack);
        registrar = v.findViewById(R.id.servicioNewOk);
        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);

        servicio = v.findViewById(R.id.servicio);
        spinner = v.findViewById(R.id.spinner);
        datePicker = v.findViewById(R.id.datepicker);
        taller = v.findViewById(R.id.taller);
        productos = v.findViewById(R.id.productos);
        comentario = v.findViewById(R.id.comentario);

        initSpinner(insert);

        if (!insert) {
            servicio.setText(values[0]);

            int dia = Integer.parseInt(values[2]);
            int mes = Integer.parseInt(values[3]);
            int anio = Integer.parseInt(values[4]);
            datePicker.updateDate(anio, mes, dia);

            taller.setText(values[5]);
            productos.setText(values[6]);
            comentario.setText(values[7]);
        }
    }
}
