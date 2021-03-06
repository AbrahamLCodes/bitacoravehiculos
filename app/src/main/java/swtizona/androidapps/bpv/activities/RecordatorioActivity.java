package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.fragments.ReminderFragment;
import swtizona.androidapps.bpv.modeladapter.SpinnerDropAdapter;
import swtizona.androidapps.bpv.modeldata.Auto;
import swtizona.androidapps.bpv.modeldata.Recordatorio;

public class RecordatorioActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView aceptar, cancelar, min, hour, am, pm;
    private ImageView back, plusHour, minusHour, plusMin, minusMin;
    private EditText servicio;
    private Spinner spinner;
    private boolean ampm;
    private DatePicker datePicker;
    private Recordatorio recordatorio;
    private Bundle bundle;
    private ArrayList<Auto> li;
    private ArrayList<Recordatorio> li2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio);
        initComponents();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recordatorioAceptar:
                aceptar();
                onBackPressed();
                break;
            case R.id.recordatorioCancelar:
                onBackPressed();
                break;
            case R.id.back:
                onBackPressed();
                break;
            case R.id.am:
                ampm = true;
                isAm();
                break;
            case R.id.pm:
                ampm = false;
                isAm();
                break;
            case R.id.plusHour:
                plusH();
                break;
            case R.id.minusHour:
                minusH();
                break;
            case R.id.plusMin:
                plusM();
                break;
            case R.id.minusMin:
                minusM();
                break;
        }
    }

    private void aceptar() {
        int dd = datePicker.getDayOfMonth();
        int mm = datePicker.getMonth();
        int year = datePicker.getYear();
        DataBaseController db = new DataBaseController(getApplicationContext());


        String ap = "";

        //Obteniendo el ID del vehiculo segun el combobox
        String matricula = "";
        int spinnerPos = spinner.getSelectedItemPosition();
        if (spinnerPos == 0) {
            Toast.makeText(this, "Elige un vehiculo", Toast.LENGTH_SHORT).show();
            spinner.requestFocus();
        } else {
            matricula = li.get(spinnerPos - 1).getMatricula();
        }

        //Definiendo AM o PM
        if (ampm) {
            ap = "AM";
        } else {
            ap = "PM";
        }

        if (bundle.getBoolean("new")) {

            //Construyendo IDs
            int id = 0;
            long count = db.getRowCount("RECORDATORIOS");
            if (count == 0) {
                id = 0;
            } else {
                int ultimo = Integer.parseInt(li2.get(li2.size() - 1).getId());
                id = ultimo + 1;
            }

            String[] rows = {
                    String.valueOf(id),
                    matricula,
                    dd + "",
                    mm + "",
                    year + "",
                    hour.getText().toString(),
                    min.getText().toString(),
                    ap,
                    servicio.getText().toString()
            };

            try {
                db.insertRecordatorio(rows);
            } catch (Exception e) {
                Toast.makeText(this, "Error al insertar datos. Checa bien tus datos", Toast.LENGTH_SHORT).show();
                Log.wtf("INSERT ERROR", "ERROR:" + e.getMessage());
            }

        } else {

            try {
                String set = "AUTO ='" + matricula + "', DIA = '" + dd + "', MES = '" + mm + "', ANIO = '" + year +
                        "', HORA = '" + hour.getText().toString() + "', MINUTO = '" + min.getText().toString() + "', AMPM ='" + ap + "', SERVICIO = '" + servicio.getText().toString() + "'";
                Log.d("DEBUGEANDO STRING", set);
                db.update("RECORDATORIOS", set, "ID", recordatorio.getId());
            } catch (Exception e) {
                Toast.makeText(this, "Error al actualizar datos. Checa bien tus datos", Toast.LENGTH_SHORT).show();
                Log.wtf("UPDATE ERROR", "ERROR:" + e.getMessage());
            }
        }

        ReminderFragment.updateUI();
        db.close();
    }

    private void plusM() {
        String m = min.getText().toString();
        int n = Integer.parseInt(m);

        if (n < 50) {
            n += 10;
            if (n < 10) {
                min.setText("0" + n);
            } else {
                min.setText("" + n);
            }
        }
    }

    private void minusM() {
        String m = min.getText().toString();
        int n = Integer.parseInt(m);

        if (n > 0) {
            n -= 10;
            if (n < 10) {
                min.setText("0" + n);
            } else {
                min.setText("" + n);
            }
        }
    }

    private void plusH() {
        String h = hour.getText().toString();
        int n = Integer.parseInt(h);

        if (n < 12) {
            n++;
            if (n < 10) {
                hour.setText("0" + n);
            } else {
                hour.setText("" + n);
            }
        }
    }

    private void minusH() {
        String h = hour.getText().toString();
        int n = Integer.parseInt(h);

        if (n > 1) {
            n--;
            if (n < 10) {
                hour.setText("0" + n);
            } else {
                hour.setText("" + n);
            }
        }
    }

    private void isAm() {
        if (ampm) {
            am.setTextColor(Color.parseColor("#000000"));
            pm.setTextColor(Color.parseColor("#9C9494"));
        } else {
            pm.setTextColor(Color.parseColor("#000000"));
            am.setTextColor(Color.parseColor("#9C9494"));
        }
    }

    private void initSpinner(Boolean nuevo) {
        List<String> spinnerArray = new ArrayList<>();
        int index = 0;

        spinnerArray.add("Elige el auto");

        for (int i = 0; i < li.size(); i++) {

            Auto auto = li.get(i);
            String autoItem = auto.getFabricante() + " " + auto.getModelo() + " " + auto.getAno() + " ID: " + auto.getMatricula();
            spinnerArray.add(autoItem);

            if (!nuevo) {
                //Configurar el Spinner para posicionarlo en el item del auto
                // guradado
                if (recordatorio.getAuto().equals(auto.getMatricula())) {
                    index = i + 1;
                }
            }

        }

        String[] items = new String[spinnerArray.size()];

        for (int i = 0; i < items.length; i++) {
            items[i] = spinnerArray.get(i);
        }

        SpinnerDropAdapter sda = new SpinnerDropAdapter(
                this,
                items,
                Color.parseColor("#635B5B")
        );
        spinner.setAdapter(sda);

        //En caso de que halla que editar
        if (!nuevo) {
            spinner.setSelection(index);
        }

    }

    private void initComponents() {
        aceptar = findViewById(R.id.recordatorioAceptar);
        cancelar = findViewById(R.id.recordatorioCancelar);
        back = findViewById(R.id.back);

        min = findViewById(R.id.min);
        hour = findViewById(R.id.hour);

        am = findViewById(R.id.am);
        pm = findViewById(R.id.pm);
        ampm = true;

        plusHour = findViewById(R.id.plusHour);
        minusHour = findViewById(R.id.minusHour);
        plusMin = findViewById(R.id.plusMin);
        minusMin = findViewById(R.id.minusMin);

        datePicker = findViewById(R.id.datePicker);

        spinner = findViewById(R.id.spinner);

        servicio = findViewById(R.id.recordatorioServicio);

        DataBaseController db = new DataBaseController(getApplicationContext());
        li = new ArrayList<>();
        li2 = new ArrayList<>();
        li = db.ultimateAllSelect("AUTOS", li);
        li2 = db.ultimateAllSelect("RECORDATORIOS", li2);
        db.close();

        aceptar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        back.setOnClickListener(this);

        am.setOnClickListener(this);
        pm.setOnClickListener(this);
        plusHour.setOnClickListener(this);
        minusHour.setOnClickListener(this);
        plusMin.setOnClickListener(this);
        minusMin.setOnClickListener(this);

        bundle = getIntent().getExtras();
        if (!bundle.getBoolean("new")) {
            setEditText(bundle);
        }
        isAm();
        initSpinner(bundle.getBoolean("new"));

    }

    private void setEditText(Bundle bundle) {
        recordatorio = new Recordatorio(
                bundle.getString("id"),
                bundle.getString("auto"),
                bundle.getString("dia"),
                bundle.getString("mes"),
                bundle.getString("anio"),
                bundle.getString("hora"),
                bundle.getString("min"),
                bundle.getString("ampm"),
                bundle.getString("servicio")
        );
        int d = Integer.parseInt(recordatorio.getDia());
        int m = Integer.parseInt(recordatorio.getMes());
        int a = Integer.parseInt(recordatorio.getAnio());

        hour.setText(recordatorio.getHora());
        min.setText(recordatorio.getMinuto());
        datePicker.updateDate(a, m, d);
        if (recordatorio.getAmpm().equals("AM")) {
            ampm = true;
        } else {
            ampm = false;
        }
        servicio.setText(recordatorio.getServicio());
        isAm();
    }
}