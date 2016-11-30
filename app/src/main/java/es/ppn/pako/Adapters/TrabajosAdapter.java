package es.ppn.pako.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import es.ppn.pako.Negocio.Trabajo;
import es.ppn.pako.R;


/**
 * Created by pp_1_ on 17/02/2016.
 */
public class TrabajosAdapter extends RecyclerView.Adapter<TrabajosAdapter.ViewHolder> {

    public static ArrayList<Trabajo> trabajos;


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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView categoria,cliente,poblacion ;
        public ImageView prioridad;

        public ViewHolder(View v) {
            super(v);
            categoria = (TextView)itemView.findViewById(R.id.txtCategoria);
            cliente = (TextView)itemView.findViewById(R.id.txtCliente);
            poblacion = (TextView)itemView.findViewById(R.id.txtPoblacion);

            prioridad = (ImageView)itemView.findViewById(R.id.imgPrioridad);


        }
    }
}