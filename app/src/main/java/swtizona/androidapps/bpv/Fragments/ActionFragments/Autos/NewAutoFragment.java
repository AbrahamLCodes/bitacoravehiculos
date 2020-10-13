package swtizona.androidapps.bpv.Fragments.ActionFragments.Autos;

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

public class NewAutoFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText campos[];
    private TextView cancelar, registrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_new_auto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        campos = new EditText[6];
        initCampos(view);

        cancelar = view.findViewById(R.id.autoNewBack);
        registrar = view.findViewById(R.id.autoNewOk);

        cancelar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autoNewBack:
                dismiss();
                break;
            case R.id.autoNewOk:
                Toast.makeText(getContext(), "Accion en construccion", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initCampos(View view) {
        int i = 0;
        while (i < 6) {
            int res = getResources().getIdentifier(
                    "autoIn" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = view.findViewById(res);
            i++;
        }
    }
}
