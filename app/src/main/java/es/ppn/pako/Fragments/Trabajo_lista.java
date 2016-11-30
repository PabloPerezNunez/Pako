package es.ppn.pako.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.ppn.pako.Adapters.TrabajosAdapter;
import es.ppn.pako.MainActivity;
import es.ppn.pako.Negocio.Trabajo;
import es.ppn.pako.R;

public class Trabajo_lista extends Fragment {


    private static final String TAG = "PAKOAPP";
    private RecyclerView listaTrabajos;

    public Trabajo_lista() { }

    public static Trabajo_lista newInstance() {
        Trabajo_lista fragment = new Trabajo_lista();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.trabajo_lista, container, false);

        ArrayList<Trabajo> lTrabajos = new ArrayList<Trabajo>();

        setTrabajos(vista);

        FloatingActionButton fabCreaTrabajo = (FloatingActionButton) vista.findViewById(R.id.fabCreaTrabajo);
        fabCreaTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Trabajo_crear f = Trabajo_crear.newInstance();
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fragmentPlace,f,"trabajoCrear").addToBackStack(null).commit();

            }
        });

        return vista;

    }

    public void setTrabajos(final View vista)  {

        final CoordinatorLayout mRoot = (CoordinatorLayout) vista.findViewById(R.id.main_content);
        final Snackbar s = Snackbar.make(mRoot, "Descargando trabajos...", Snackbar.LENGTH_INDEFINITE);
        s.getView().setBackgroundColor(getResources().getColor(R.color.colorGreen));

        s.show();


        JSONObject filter = null;
        try {

            filter = new JSONObject("{'realizado':'0'}");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        String JSON_URL = "http://servicios.carpinteriapako.es/trabajos.php?q="+filter.toString();


        StringRequest stringRequest = new StringRequest(JSON_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray listado = new JSONArray(response);
                        ArrayList<Trabajo> lTrabajos = new ArrayList<Trabajo>();

                        for(int i=0; i<listado.length(); i++){
                            JSONObject item = listado.getJSONObject(i);
                            lTrabajos.add(new Trabajo(item));
                        }

                        TrabajosAdapter trabajosAdapter = new TrabajosAdapter(lTrabajos);

                        trabajosAdapter.notifyDataSetChanged();

                        listaTrabajos = (RecyclerView) vista.findViewById(R.id.listaTrabajos);
                        listaTrabajos.swapAdapter(trabajosAdapter,false);
                        listaTrabajos.setLayoutManager(new GridLayoutManager(getContext(), 1));



                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG,e.toString());

                    }

                    s.dismiss();

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    s.dismiss();

                    final Snackbar s = Snackbar.make(mRoot, "Error de conexión", Snackbar.LENGTH_LONG);
                    s.getView().setBackgroundColor(getResources().getColor(R.color.colorRed));
                    s.setActionTextColor(Color.BLACK);

                    s.setAction("Reintentar", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(Trabajo_lista.this).attach(Trabajo_lista.this).commit();
                        }
                    });

                    s.show();


                }
            });


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}
