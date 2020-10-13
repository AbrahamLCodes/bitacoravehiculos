package swtizona.androidapps.bpv.fragments.actionfragments.productos;

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

public class NewProductoFragment extends AppCompatDialogFragment implements View.OnClickListener{

    private EditText campos[];
    private TextView cancelar, registrar;

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

        switch (v.getId()){
            case R.id.productoNewBack:
                dismiss();
                break;
            case R.id.tallerNewOk:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void initCampos(View v){
        campos = new EditText[5];
        cancelar = v.findViewById(R.id.productoNewBack);
        registrar = v.findViewById(R.id.productoNewOk);
        initEditText(v);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void initEditText(View v){
        int i = 0;
        while (i < 5) {
            int res = getResources().getIdentifier(
                    "productoIn" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = v.findViewById(res);
            i++;
        }
    }
}
