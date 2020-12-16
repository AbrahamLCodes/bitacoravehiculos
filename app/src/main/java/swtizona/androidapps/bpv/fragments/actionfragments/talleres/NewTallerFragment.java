package swtizona.androidapps.bpv.fragments.actionfragments.talleres;

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
import swtizona.androidapps.bpv.activities.TallerActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;

public class NewTallerFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText campos[];
    private TextView cancelar, registrar;

    //Variables to identify if user wants to insert a new car or update it
    boolean insert;
    String [] values;

    public NewTallerFragment(boolean insert){
        this.insert = insert;
    }

    public NewTallerFragment(boolean insert, String[] values){
        this.insert = insert;
        this.values = values;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_new_taller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCampos(view);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tallerNewBack:
                dismiss();
                break;
            case R.id.tallerNewOk:
                actionOk();
                break;
        }

    }

    private void initCampos(View v) {
        campos = new EditText[8];
        cancelar = v.findViewById(R.id.tallerNewBack);
        registrar = v.findViewById(R.id.tallerNewOk);
        initEditText(v);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void actionOk() {
        int i = 0;
        boolean flag = true;
        //Show input alert
        while (i < 8) {
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
        String[] rows = new String[8];
        //Get TextEdits values
        for (int i = 0; i < 8; i++) {
            if (i == 7) {
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

        if(insert){
            db.insert8Rows("TALLERES", rows);
        }else {
            db.update("TALLERES",
                    "NOMBRE = '"+rows[0]+"', TELEFONO = '"+rows[1]+"', CALLE = '"+rows[2]+"', NCALLE = '"+rows[3]+"', COLONIA = '"+rows[4]+"', CIUDAD = '"+rows[5]+"', ESTADO = '"+rows[6]+"', COMENTARIO = '"+rows[7]+"'",
                    "TELEFONO",
                    "'"+values[1]+"'");
        }

        updateRAM();
    }

    private void updateRAM() {
        //Updating RAM data
        TallerActivity.updateUI();
        dismiss();
    }

    private void initEditText(View v) {
        int i = 0;
        while (i < 8) {
            int res = getResources().getIdentifier(
                    "tallerIn" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = v.findViewById(res);
            if(!insert){
                campos[i].setText(values[i]);
            }
            i++;
        }
    }
}
