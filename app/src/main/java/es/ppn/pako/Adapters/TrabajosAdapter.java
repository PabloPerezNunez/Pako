package es.ppn.pako.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;

import es.ppn.pako.Fragments.Trabajo_lista;
import es.ppn.pako.Negocio.Trabajo;
import es.ppn.pako.R;


/**
 * Created by pp_1_ on 17/02/2016.
 */
public class TrabajosAdapter extends RecyclerView.Adapter<TrabajosAdapter.ViewHolder> {

    public static ArrayList<Trabajo> trabajos;
    private onItemClick clicklistener;


    // Provide a suitable constructor (depends on the kind of dataset)
    public TrabajosAdapter(ArrayList<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrabajosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trabajos_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Trabajo t = trabajos.get(position);

        holder.categoria.setText(t.getCategoria());
        holder.cliente.setText(t.getCliente());
        holder.poblacion.setText(t.getConcejo());

        if(t.getPrioridad()==3){
            holder.prioridad.setVisibility(View.VISIBLE);
        }else{
            holder.prioridad.setVisibility(View.INVISIBLE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trabajos.size();
    }

    public void setOnItemClickListener(final onItemClick listener){
        clicklistener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView categoria,cliente,poblacion ;
        public ImageView prioridad;

        public ViewHolder(View v) {
            super(v);
            categoria = (TextView)v.findViewById(R.id.txtCategoria);
            cliente = (TextView)v.findViewById(R.id.txtCliente);
            poblacion = (TextView)v.findViewById(R.id.txtPoblacion);

            prioridad = (ImageView)v.findViewById(R.id.imgPrioridad);

            v.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {

            Trabajo t = trabajos.get(getAdapterPosition());

            if (clicklistener!=null){
                clicklistener.onItemClickListener(t,v);
            }
        }
    }

    public interface onItemClick {
        public void onItemClickListener(Trabajo t, View v);
    }


}