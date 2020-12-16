package swtizona.androidapps.bpv.fragments.actionfragments.productos;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

import swtizona.androidapps.bpv.R;
import swtizona.androidapps.bpv.activities.ProductosActivity;
import swtizona.androidapps.bpv.activities.ProductosDetalleActivity;
import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.modeldata.Producto;

public class InfoProductoFragment extends AppCompatDialogFragment implements View.OnClickListener {

    private TextView regresar, buscar, editar, eliminar, titulo;
    private TextView[] campos;
    private Producto producto;
    private ArrayList<Producto> li = new ArrayList<>();
    private int pos;
    String[] values;

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
                Intent intent = new Intent(getActivity(), ProductosDetalleActivity.class);
                intent.putExtra("id", producto.getModelo());
                startActivity(intent);
                dismiss();
                break;
            case R.id.productoInfoEditar:
                NewProductoFragment newProductoFragment = new NewProductoFragment(false, values);
                newProductoFragment.show(getFragmentManager(), "Editar producto");
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
        db.delete("PRODUCTOS", "NSERIE", producto.getNserie());
        ProductosActivity.updateUI();
        db.close();
    }

    private void initComponents(View v) {
        regresar = v.findViewById(R.id.productoInfoRegresar);
        buscar = v.findViewById(R.id.productoInfoBuscar);
        editar = v.findViewById(R.id.productoInfoEditar);
        eliminar = v.findViewById(R.id.productoInfoEliminar);
        titulo = v.findViewById(R.id.tituloInfoProducto);

        DataBaseController db = new DataBaseController(getContext());
        li = db.ultimateAllSelect("PRODUCTOS", li);
        producto = li.get(pos);

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
        titulo.setText(producto.getNombre());

        campos[0].setText(campos[0].getText() + " " + producto.getAuto());
        campos[1].setText(campos[1].getText() + " " + producto.getModelo());
        campos[2].setText(campos[2].getText() + " " + producto.getMarca());
        campos[3].setText(campos[3].getText() + " " + producto.getNserie());
        campos[4].setText(campos[4].getText() + " " + producto.getComentario());

        values[0] = producto.getNombre();
        values[1] = producto.getAuto();
        values[2] = producto.getModelo();
        values[3] = producto.getMarca();
        values[4] = producto.getNserie();
        values[5] = producto.getComentario();

    }
}
