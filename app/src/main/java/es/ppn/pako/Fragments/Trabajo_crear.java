package es.ppn.pako.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import es.ppn.pako.R;

public class Trabajo_crear extends Fragment {


    private static final String TAG = "PAKOAPP";

    public Trabajo_crear() {
        // Required empty public constructor
    }

    public static Trabajo_crear newInstance() {
        Trabajo_crear fragment = new Trabajo_crear();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.trabajo_crear, container, false);

        //////////////////////////////////////////////////////////////////////////////////////
        Spinner concejo = (Spinner) vista.findViewById(R.id.concejo);
        String concejos[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
        ArrayAdapter<String> concejosAdapter = new ArrayAdapter<String>(vista.getContext(),android.R.layout.simple_spinner_item, concejos);
        concejosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        concejo.setAdapter(concejosAdapter);
        //////////////////////////////////////////////////////////////////////////////////////
        Spinner prioridad = (Spinner) vista.findViewById(R.id.prioridad);
        String prioridades[] = {"Normal","Alta","Baja"};
        ArrayAdapter<String> prioridadAdapter = new ArrayAdapter<String>(vista.getContext(),android.R.layout.simple_spinner_item, prioridades);
        prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        prioridad.setAdapter(prioridadAdapter);
        //////////////////////////////////////////////////////////////////////////////////////
        Spinner categoria = (Spinner) vista.findViewById(R.id.categoria);
        String categorias[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
        ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(vista.getContext(),android.R.layout.simple_spinner_item, categorias);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        categoria.setAdapter(categoriaAdapter);
        //////////////////////////////////////////////////////////////////////////////////////
        return vista;
    }


}
