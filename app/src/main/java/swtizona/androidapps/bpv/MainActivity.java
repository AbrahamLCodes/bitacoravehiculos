package swtizona.androidapps.bpv;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Toast;

import swtizona.androidapps.bpv.database.DataBaseController;
import swtizona.androidapps.bpv.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private TabLayout tabs;
    private DataBaseController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        //Tabs para cambiar
        tabs = findViewById(R.id.tabs);
        //Floating button
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(this);

        db = new DataBaseController(getApplicationContext());
        Toast.makeText(this, "Autos: " + db.getColumnCount("AUTOS"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Productos: " + db.getColumnCount("PRODUCTOS"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Talleres: " + db.getColumnCount("TALLERES"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Servicios: " + db.getColumnCount("SERVICIOS"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Recordatorios: " + db.getColumnCount("RECORDATORIOS"), Toast.LENGTH_SHORT).show();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switchTab(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switchTab(tab.getPosition());
            }
        });
        tabs.setupWithViewPager(viewPager);
        //Para no mostrar el fab al inicio del programa
        initFab();
    }


    private void switchTab(int pos) {
        switch (pos) {
            case 0:
            case 2:
                if (fab.isShown()) {
                    fab.hide();
                }
                break;
            case 1:
                if (!fab.isShown()) {
                    fab.show();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                fabAction();
                break;
        }
    }

    private void fabAction() {
        switch (tabs.getSelectedTabPosition()) {
            case 1:
                fabRecordatorios();
                break;
        }
    }

    private void fabRecordatorios() {
        Toast.makeText(this, "fab Recordatorios en construcción", Toast.LENGTH_SHORT).show();
    }

    private void initFab() {
        switch (tabs.getSelectedTabPosition()) {
            case 0:
                fab.hide();
                break;
        }
    }
}