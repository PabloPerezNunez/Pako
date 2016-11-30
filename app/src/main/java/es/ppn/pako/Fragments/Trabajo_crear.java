package es.ppn.pako.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ppn.pako.R;

public class Trabajo_crear extends Fragment {


    private static final String TAG = "PAKOAPP";
    private Spinner concejo,prioridad,categoria;
    private EditText nombre,direccion,telefono1,telefono2,horario,trabajo;
    private CoordinatorLayout mRoot;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.trabajo_crear, container, false);

        mRoot = (CoordinatorLayout) vista.findViewById(R.id.main_content);


        //////////////////////////////////////////////////////////////////////////////////////
        concejo = (Spinner) vista.findViewById(R.id.concejo);
        setConcejos(concejo);
        //////////////////////////////////////////////////////////////////////////////////////
        prioridad = (Spinner) vista.findViewById(R.id.prioridad);
        String prioridades[] = {"Normal","Alta","Baja"};
        ArrayAdapter<String> prioridadAdapter = new ArrayAdapter<String>(vista.getContext(),android.R.layout.simple_spinner_item, prioridades);
        prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        prioridad.setAdapter(prioridadAdapter);
        //////////////////////////////////////////////////////////////////////////////////////
        categoria = (Spinner) vista.findViewById(R.id.categoria);
        setCategorias(categoria);
        //////////////////////////////////////////////////////////////////////////////////////
        nombre = (EditText) vista.findViewById(R.id.nombre);
        direccion = (EditText) vista.findViewById(R.id.direccion);
        telefono1 = (EditText) vista.findViewById(R.id.telefono1);
        telefono2 = (EditText) vista.findViewById(R.id.telefono2);
        horario = (EditText) vista.findViewById(R.id.horario);
        trabajo = (EditText) vista.findViewById(R.id.trabajo);
        //////////////////////////////////////////////////////////////////////////////////////

        FloatingActionButton enviar = (FloatingActionButton) vista.findViewById(R.id.btnAceptar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTrabajo();
            }
        });

        return vista;

    }

    public void sendTrabajo(){

        if(valid()){

            final Snackbar s = Snackbar.make(mRoot, "Creando trabajo...", Snackbar.LENGTH_INDEFINITE);
            s.getView().setBackgroundColor(getResources().getColor(R.color.colorGreen));

            s.show();

            String url = "http://servicios.carpinteriapako.es/trabajos.php";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject result = new JSONObject(response);

                                if(result.getString("result").toString().compareTo("200")==0){
                                    Trabajo_lista f = Trabajo_lista.newInstance();
                                    getFragmentManager().beginTransaction().replace(R.id.fragmentPlace,f,"trabajoCrear").commit();

                                }else{
                                    Toast.makeText(getContext(),result.getString("message").toString(),Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            s.dismiss();

                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            s.dismiss();

                            final Snackbar s = Snackbar.make(mRoot, "Error de conexión", Snackbar.LENGTH_LONG);
                            s.getView().setBackgroundColor(getResources().getColor(R.color.colorRed));

                            s.show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("cliente",nombre.getText().toString());
                    params.put("lugar", direccion.getText().toString() );
                    params.put("poblacion", concejo.getSelectedItem().toString());
                    params.put("categoria", categoria.getSelectedItem().toString());
                    params.put("prioridad", getPrioridad());
                    params.put("trabajo", trabajo.getText().toString());

                    //OPCIONALES

                    if(horario.getText().toString().compareTo("")!=0){
                        params.put("horario",horario.getText().toString());
                    }
                    if(telefono1.getText().toString().compareTo("")!=0){
                        params.put("telefono",telefono1.getText().toString());
                    }
                    if(telefono2.getText().toString().compareTo("")!=0){
                        params.put("telefono2",telefono2.getText().toString());
                    }

                    Log.d(TAG,params.toString());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(postRequest);

        }
        else{
            final Snackbar s = Snackbar.make(mRoot, "Faltan campos por rellenar", Snackbar.LENGTH_LONG);
            s.getView().setBackgroundColor(getResources().getColor(R.color.colorRed));

            s.show();
        }

    }
    private String getPrioridad() {
        if (prioridad.getSelectedItem().toString().compareTo("Baja")==0){
            return "1";
        }
        if (prioridad.getSelectedItem().toString().compareTo("Normal")==0){
            return "2";
        }
        if (prioridad.getSelectedItem().toString().compareTo("Alta")==0){
            return "3";
        }
        return "";
    }
    public boolean valid(){

        if (nombre.getText().toString().compareTo("")==0){
            return false;
        }

        if (direccion.getText().toString().compareTo("")==0){
            return false;
        }

        if (trabajo.getText().toString().compareTo("")==0){
            return false;
        }

        if (concejo.getSelectedItem().toString().compareTo("Selecciona un concejo...")==0){
            return false;
        }

        if (categoria.getSelectedItem().toString().compareTo("Selecciona una categoría...")==0){
            return false;
        }

        return true;

    }
    public void setConcejos(final Spinner concejo) {

        try {
            InputStream is = getActivity().getAssets().open("concejos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            JSONArray jArray = new JSONArray(json);
            List<String> listaConcejos = new ArrayList<String>();


            for(int i=0; i<jArray.length(); i++){
                JSONObject item = jArray.getJSONObject(i);
                listaConcejos.add(item.getString("nombre"));
            }

            final ArrayAdapter<String> concejosAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaConcejos){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0){ return false;}
                    else{ return true;}
                }
                @Override
                public View getDropDownView(int position, View convertView,ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };

            concejosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            concejo.setAdapter(concejosAdapter);


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
    public void setCategorias(final Spinner categoria) {

        try {
            InputStream is = getActivity().getAssets().open("categorias.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jArray = new JSONArray(json);
            List<String> listaCategorias = new ArrayList<String>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject item = jArray.getJSONObject(i);
                listaCategorias.add(item.getString("nombre"));
            }


            final ArrayAdapter<String> categoriasAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaCategorias){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0){ return false;}
                    else{ return true;}
                }
                @Override
                public View getDropDownView(int position, View convertView,ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };


            categoriasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoria.setAdapter(categoriasAdapter);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
