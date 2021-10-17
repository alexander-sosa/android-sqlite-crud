package com.example.androidfinal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinal.models.ModeloArtist;
import com.example.androidfinal.models.ModeloCancion;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCancion extends RecyclerView.Adapter<AdaptadorCancion.ViewDataHolder>{

    List<ModeloCancion> lista;

    public AdaptadorCancion(List<ModeloCancion> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos (ArrayList<ModeloCancion> listaDeProductos){
        this.lista = listaDeProductos;
    }

    @NonNull
    @Override
    public AdaptadorCancion.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cancion, parent, false);
        return new AdaptadorCancion.ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCancion.ViewDataHolder holder, int position) {
        holder.tvNombreArtista.setText(lista.get(position).getNombreArtista());
        holder.tvNombreCancion.setText(lista.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder{
        TextView tvNombreArtista, tvNombreCancion;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreArtista = itemView.findViewById(R.id.tvArtista);
            tvNombreCancion = itemView.findViewById(R.id.tvNombreCancion);
            Log.i("phx", String.valueOf(tvNombreArtista));
        }
    }
}
