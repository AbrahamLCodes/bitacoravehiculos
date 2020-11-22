package swtizona.androidapps.bpv.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;
import swtizona.androidapps.bpv.fragments.ReminderFragment;
import swtizona.androidapps.bpv.modeldata.Auto;

public class RecordatorioActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView aceptar, cancelar, min, hour, am, pm;
    private ImageView back, plusHour, minusHour, plusMin, minusMin;
    private EditText servicio;
    private Spinner spinner;
    private boolean ampm;
    private DatePicker datePicker;
    private String [] meses = {"Enero", "Febrero", "Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre",
    "Octubre","Noviembre","Diciembre"};

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
                registrar();
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

    private void registrar(){
        int dd = datePicker.getDayOfMonth();
        int mm = datePicker.getMonth();
        int year = datePicker.getYear();
        DataBaseController db = new DataBaseController(getApplicationContext());

        String ap = "";

        //Construyendo IDs
        int id = 0;
        long count = db.getRowCount("RECORDATORIOS");
        if (count == 0) {
            id = 0;
        }else {
            int ultimo = Integer.parseInt(Lists.getRecordatorioList().get(Lists.getRecordatorioList().size() - 1).getId());
            id = ultimo + 1;
        }

        //Obteniendo el ID del vehiculo segun el combobox
        String matricula = "";
        int spinnerPos = spinner.getSelectedItemPosition();
        if(spinnerPos == 0){
            Toast.makeText(this, "Elige un vehiculo", Toast.LENGTH_SHORT).show();
            spinner.requestFocus();
        }else {
           matricula = Lists.getAutoList().get(spinnerPos - 1).getMatricula();
        }

        //Definiendo AM o PM
        if(ampm){
            ap = "AM";
        }else {
            ap = "PM";
        }

        String [] rows = {
                String.valueOf(id),
                matricula,
                dd+"",
                mm+"",
                year+"",
                hour.getText().toString(),
                min.getText().toString(),
                ap,
                servicio.getText().toString()
        };
        db.insertRecordatorio(rows);
        Lists.initLists();
        db.updateLists();
        ReminderFragment.updateUI();
        db.close();
    }

    private void plusM(){
        String m = min.getText().toString();
        int n = Integer.parseInt(m);

        if(n < 60){
            n++;
            if(n < 10){
                min.setText("0"+n);
            }else{
                min.setText(""+n);
            }
        }
    }

    private void minusM(){
        String m = min.getText().toString();
        int n = Integer.parseInt(m);

        if(n > 0){
            n--;
            if(n < 10){
                min.setText("0"+n);
            }else{
                min.setText(""+n);
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
                hour.setText(""+n);
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
                hour.setText(""+n);
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

    private void initSpinner(){
        List<String> spinnerArray = new ArrayList<String>();
        
        spinnerArray.add("Elige el auto");
        for(int i = 0 ; i < Lists.getAutoList().size(); i++){
            Auto auto = Lists.getAutoList().get(i);
            String autoItem = auto.getFabricante() +" " +auto.getModelo() +" " +auto.getAno() + " ID: "+auto.getMatricula();
            spinnerArray.add(autoItem);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item, 
                spinnerArray
        );
        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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

        servicio = findViewById(R.id.recordatorioServicio);
        
        spinner = findViewById(R.id.spinner);
        initSpinner();

        aceptar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        back.setOnClickListener(this);

        am.setOnClickListener(this);
        pm.setOnClickListener(this);
        plusHour.setOnClickListener(this);
        minusHour.setOnClickListener(this);
        plusMin.setOnClickListener(this);
        minusMin.setOnClickListener(this);

        isAm();
    }
}