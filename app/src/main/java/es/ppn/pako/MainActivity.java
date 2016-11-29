package es.ppn.pako;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.ppn.pako.Fragments.Trabajo_crear;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Trabajo_crear f = Trabajo_crear.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPlace,f ).commit();


    }
}
