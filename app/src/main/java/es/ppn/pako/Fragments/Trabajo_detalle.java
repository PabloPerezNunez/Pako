package es.ppn.pako.Fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import es.ppn.pako.Negocio.Trabajo;
import es.ppn.pako.R;

public class Trabajo_detalle extends Fragment {


    private static final String TAG = "PAKOAPP";
    private static final String TRABAJO = "Trabajo" ;
    private Trabajo trabajo;
    private CoordinatorLayout mRoot;


    public Trabajo_detalle() {
        // Required empty public constructor

    }

    public static Trabajo_detalle newInstance(Trabajo t) {
        Trabajo_detalle fragment = new Trabajo_detalle();

        Bundle args = new Bundle();
        args.putString(TRABAJO, t.getJson().toString());
        fragment.setArguments(args);

        Log.d(TAG,args.getString(TRABAJO));
        Log.d(TAG,t.getJson().toString());


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            try {
                JSONObject jso = new JSONObject(getArguments().getString(TRABAJO));
                trabajo = new Trabajo(jso);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.trabajo_detalle, container, false);

        mRoot = (CoordinatorLayout) vista.findViewById(R.id.main_content);

        TextView nombre = (TextView) vista.findViewById(R.id.txtNombre);
        nombre.setText(trabajo.getCliente());

        TextView lugar = (TextView) vista.findViewById(R.id.txtLugar);
        lugar.setText(trabajo.getLugar());

        TextView concejo = (TextView) vista.findViewById(R.id.txtConcejo);
        concejo.setText(trabajo.getConcejo());

        TextView categoria = (TextView) vista.findViewById(R.id.txtCategoria);
        categoria.setText(trabajo.getCategoria());

        TextView horario = (TextView) vista.findViewById(R.id.txtHorario);
        horario.setText(trabajo.getHorario());

        TextView trabajo_d = (TextView) vista.findViewById(R.id.txtTrabajo);
        trabajo_d.setText(trabajo.getTrabajo());

        /*PARÁMETROS OPCIONALES**/
        TextView telefono = (TextView) vista.findViewById(R.id.txtTelefono);
        if(trabajo.getTelefono1().compareTo("null")!=0){

            String telefonos = trabajo.getTelefono1();

            if(trabajo.getTelefono2().compareTo("null")!=0){
                telefonos+=" | "+trabajo.getTelefono2();
            }

            telefono.setText(telefonos);

        }
        else{
            telefono.setText("No disponible");

        }




        return vista;

    }


}
