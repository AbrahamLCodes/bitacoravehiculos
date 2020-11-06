package swtizona.androidapps.bpv.fragments.actionfragments.productos;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.ProductosActivity;
import swtizona.androidapps.bpv.activities.ProductosDetalleActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.database.Lists;

public class InfoProductoFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, editar, eliminar, titulo;
    private TextView[] campos;
    private int pos;
    String [] values;

    public InfoProductoFragment(int pos) {
        this.pos = pos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_info_producto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        campos = new TextView[5];
        values = new String[6];
        initComponents(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.productoInfoRegresar:
                dismiss();
                break;
            case R.id.productoInfoBuscar:
                startActivity(new Intent(getActivity(), ProductosDetalleActivity.class));
                break;
            case R.id.productoInfoEditar:
                openNewDialog();
                dismiss();
                break;
            case R.id.productoInfoEliminar:
                delete();
                dismiss();
                break;
        }

    }

    private void delete() {
        DataBaseController db = new DataBaseController(getContext());
        db.delete("PRODUCTOS", "NSERIE", Lists.getProductoList().get(pos).getNserie());
        updateRAM(db);
        db.close();
    }

    private void updateRAM(DataBaseController db) {
        Lists.initLists();
        db.updateLists();
        ProductosActivity.updateUI();
    }

    private void initComponents(View v) {
        regresar = v.findViewById(R.id.productoInfoRegresar);
        buscar = v.findViewById(R.id.productoInfoBuscar);
        editar = v.findViewById(R.id.productoInfoEditar);
        eliminar = v.findViewById(R.id.productoInfoEliminar);
        titulo = v.findViewById(R.id.tituloInfoProducto);

        initCampos(v);
        setCampos();

        regresar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
    }

    private void initCampos(View view) {
        int i = 0;
        while (i < 5) {
            int res = getResources().getIdentifier(
                    "producto" + (i)
                    , "id"
                    , getActivity().getPackageName());
            campos[i] = view.findViewById(res);
            i++;
        }
    }

    private void setCampos() {
        titulo.setText(Lists.getProductoList().get(pos).getNombre());

        campos[0].setText(campos[0].getText() + " " + Lists.getProductoList().get(pos).getAuto());
        campos[1].setText(campos[1].getText() + " " + Lists.getProductoList().get(pos).getModelo());
        campos[2].setText(campos[2].getText() + " " + Lists.getProductoList().get(pos).getMarca());
        campos[3].setText(campos[3].getText() + " " + Lists.getProductoList().get(pos).getNserie());
        campos[4].setText(campos[4].getText() + " " + Lists.getProductoList().get(pos).getComentario());

        values[0] = Lists.getProductoList().get(pos).getNombre();
        values[1] = Lists.getProductoList().get(pos).getAuto();
        values[2] = Lists.getProductoList().get(pos).getModelo();
        values[3] = Lists.getProductoList().get(pos).getMarca();
        values[4] = Lists.getProductoList().get(pos).getNserie();
        values[5] = Lists.getProductoList().get(pos).getComentario();

    }

    private void openNewDialog() {
        NewProductoFragment newProductoFragment = new NewProductoFragment(false, values);
        newProductoFragment.show(getFragmentManager(), "Editar producto");
    }
}
