package swtizona.androidapps.bpv.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import swtizona.androidapps.bpv.R;

public class AutosActivity extends AppCompatActivity implements
        View.OnClickListener
        , AdapterView.OnItemClickListener {

    private String [] autos = {"Ranger Roja"};
    private ListView listAutos;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        backButton = findViewById(R.id.autosBack);
        listAutos = findViewById(R.id.listAutos);
        listAutos.setAdapter(new ArrayAdapter<>(
                getApplicationContext()
                , android.R.layout.simple_list_item_1
                , autos));

        backButton.setOnClickListener(this);
        listAutos.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.autosBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, ""+autos[position], Toast.LENGTH_SHORT).show();
    }
}