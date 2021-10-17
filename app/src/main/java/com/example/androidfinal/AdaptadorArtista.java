package com.example.androidfinal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinal.models.ModeloArtist;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorArtista extends RecyclerView.Adapter<AdaptadorArtista.ViewDataHolder>{
    List<ModeloArtist> lista;

    public AdaptadorArtista(List<ModeloArtist> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos (ArrayList<ModeloArtist> listaDeProductos){
        this.lista = listaDeProductos;
    }

    @NonNull
    @Override
    public AdaptadorArtista.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_artista, parent, false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorArtista.ViewDataHolder holder, int position) {
        holder.tvNombreArtista.setText(lista.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder{
        TextView tvNombreArtista;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreArtista = itemView.findViewById(R.id.tvNombreArtista);
            Log.i("phx", String.valueOf(tvNombreArtista));
        }
    }
}
