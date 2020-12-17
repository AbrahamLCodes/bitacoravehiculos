package swtizona.androidapps.bpv.fragments.actionfragments.productos;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
import swtizona.androidapps.bpv.activities.ProductosActivity;
import swtizona.androidapps.bpv.database.DataBaseController;


public class NewProductoFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText campos[];
    private TextView cancelar, registrar;

    //Variables to identify if user wants to insert a new car or update it
    boolean insert;
    String[] values;

    public NewProductoFragment(boolean insert) {
        this.insert = insert;
    }

    public NewProductoFragment(boolean insert, String[] values) {
        this.insert = insert;
        this.values = values;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_new_producto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCampos(view);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.productoNewBack:
                dismiss();
                break;
            case R.id.productoNewOk:
                actionOk();
                break;
        }

    }

    private void initCampos(View v) {
        campos = new EditText[6];
        cancelar = v.findViewById(R.id.productoNewBack);
        registrar = v.findViewById(R.id.productoNewOk);
        initEditText(v);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void actionOk() {
        int i = 0;
        boolean flag = true;
        //Show input alert
        while (i < 5) {
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

        if (checkForeignKey(campos[1].getText().toString())) {
            if (insert) {
                try{
                    db.insert6Rows("PRODUCTOS", rows);
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error al insertar datos. Revisa bien tus datos.", Toast.LENGTH_SHORT).show();
                    Log.wtf("INSERT ERROR","ERROR: "+e.getMessage());
                }
            } else {
                try {
                    db.updateProducto(
                            "NOMBRE = '" + rows[0] + "', AUTO = '" + rows[1] + "', MODELO = '" + rows[2] + "', MARCA = '" + rows[3] + "', NSERIE = '" + rows[4] + "', COMENTARIO = '" + rows[5] + "'",
                            "'" + values[2] + "'",
                            "'" + values[4] + "'"
                    );
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error al actualizar. Revisa bien tus datos", Toast.LENGTH_SHORT).show();
                    Log.wtf("UPDATE ERROR" , "ERROR: "+ e.getMessage());
                }

            }
            updateRAM();
        } else {
            Toast.makeText(getContext(), "El auto no existe", Toast.LENGTH_SHORT).show();
            campos[1].requestFocus();
        }

    }

    private boolean checkForeignKey(String like) {
        DataBaseController db = new DataBaseController(getContext());
        return db.isForeignKey("AUTOS", "MATRICULA", like);
    }

    private void updateRAM() {
        //Updating RAM data
        ProductosActivity.updateUI();
        dismiss();
    }

    private void initEditText(View v) {
        int i = 0;
        while (i < 6) {
            int res = getResources().getIdentifier(
                    "productoIn" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = v.findViewById(res);
            if (!insert) {
                campos[i].setText(values[i]);
            }
            i++;
        }
    }
}
