package com.example.androidfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfinal.helpers.Controller;
import com.example.androidfinal.models.ModeloArtist;

import java.util.ArrayList;
import java.util.List;

public class ListaArtistaActivity extends AppCompatActivity {

    List<ModeloArtist> lista;
    AdaptadorArtista adaptador;
    RecyclerView rvArtista;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_artista);

        lista = new ArrayList<>();
        adaptador = new AdaptadorArtista(lista);
        rvArtista = findViewById(R.id.rvArtista);
        controller = new Controller(ListaArtistaActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvArtista.setLayoutManager(mLayoutManager);
        rvArtista.setItemAnimator(new DefaultItemAnimator());
        rvArtista.setAdapter(adaptador);
        refrescarListaDeProductos();

        rvArtista.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        rvArtista.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvArtista, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"un toque", Toast.LENGTH_SHORT).show();
                ModeloArtist prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"Toque largo", Toast.LENGTH_SHORT).show();
                ModeloArtist prodElegido = lista.get(position);
                cambio(prodElegido);
            }
        }));
    }

    public void refrescarListaDeProductos() {
        if (adaptador == null) return;
        lista = controller.obtenerArtistas();
        adaptador.setListaDeProductos((ArrayList<ModeloArtist>) lista);
        adaptador.notifyDataSetChanged();
    }

    void baja(final ModeloArtist prodele){
        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaArtistaActivity.this);
        alerta.setTitle("Esta seguro de eliminar");
        alerta.setCancelable(false);
        alerta.setMessage("nombre: "+prodele.getNombre()+"\nId: "+prodele.getId());
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int res = controller.bajaProducto(prodele);
                if(res == -1){
                    Toast.makeText(getApplicationContext(),"No puede eliminar este artista", Toast.LENGTH_SHORT).show();
                }
                refrescarListaDeProductos();;
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //AlertDialog dialog  = builder.create();
        //dialog.show();
        alerta.create().show();

    }


    void cambio(final ModeloArtist prodele){
        LayoutInflater inflador = LayoutInflater.from(this);
        View subView = inflador.inflate(R.layout.layout_cambio_artista, null);

        final EditText etnuevonombre = subView.findViewById(R.id.etNuevoNombreArtista);

        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaArtistaActivity.this);
        alerta.setCancelable(false);
        alerta.setTitle("Esta seguro de actualizar los siguientes campos");
        alerta.setMessage(prodele.toString());
        alerta.setView(subView);
        alerta.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ModeloArtist newObj = new ModeloArtist(etnuevonombre.getText().toString(), prodele.getId());
                int filasAfectadas = controller.cambioArtista(newObj);
                if(filasAfectadas > 0){
                    refrescarListaDeProductos();;
                    Toast.makeText(getApplicationContext(),"Exito en el cambio", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Error no se realizo el cambio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerta.create().show();
    }

}