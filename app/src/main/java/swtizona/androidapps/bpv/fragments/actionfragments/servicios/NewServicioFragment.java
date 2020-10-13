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

public class NewServicioFragment extends AppCompatDialogFragment implements View.OnClickListener{

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

        switch (v.getId()){
            case R.id.servicioNewBack:
                dismiss();
                break;
            case R.id.servicioNewOk:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void initCampos(View v){
        campos = new EditText[6];
        cancelar = v.findViewById(R.id.servicioNewBack);
        registrar = v.findViewById(R.id.servicioNewOk);
        initEditText(v);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void initEditText(View v){
        int i = 0;
        while (i < 6) {
            int res = getResources().getIdentifier(
                    "productoIn" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = v.findViewById(res);
            i++;
        }
    }
}
