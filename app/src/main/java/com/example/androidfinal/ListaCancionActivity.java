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
import com.example.androidfinal.models.ModeloCancion;

import java.util.ArrayList;
import java.util.List;

public class ListaCancionActivity extends AppCompatActivity {

    List<ModeloCancion> lista;
    AdaptadorCancion adaptador;
    RecyclerView rvCancion;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cancion);

        lista = new ArrayList<>();
        adaptador = new AdaptadorCancion(lista);
        rvCancion = findViewById(R.id.rvCancion);
        controller = new Controller(ListaCancionActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCancion.setLayoutManager(mLayoutManager);
        rvCancion.setItemAnimator(new DefaultItemAnimator());
        rvCancion.setAdapter(adaptador);
        refrescarListaDeProductos();

        rvCancion.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
        rvCancion.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvCancion, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"un toque", Toast.LENGTH_SHORT).show();
                ModeloCancion prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"Toque largo", Toast.LENGTH_SHORT).show();
                ModeloCancion prodElegido = lista.get(position);
                cambio(prodElegido);
            }
        }));
    }

    public void refrescarListaDeProductos() {
        if (adaptador == null) return;
        lista = controller.obtenerCanciones();
        adaptador.setListaDeProductos((ArrayList<ModeloCancion>) lista);
        adaptador.notifyDataSetChanged();
    }

    void baja(final ModeloCancion prodele){
        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCancionActivity.this);
        alerta.setTitle("Esta seguro de eliminar");
        alerta.setCancelable(false);
        alerta.setMessage("nombre: "+prodele.getNombre()+"\nId: "+prodele.getId());
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.bajaCancion(prodele);
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

    void cambio(final ModeloCancion prodele){
        LayoutInflater inflador = LayoutInflater.from(this);
        View subView = inflador.inflate(R.layout.layout_cambio_cancion, null);

        final EditText etnuevonombre = subView.findViewById(R.id.etNuevoNombreCancion);

        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCancionActivity.this);
        alerta.setCancelable(false);
        alerta.setTitle("Esta seguro de actualizar los siguientes campos");
        alerta.setMessage(prodele.toString());
        alerta.setView(subView);
        alerta.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ModeloCancion newObj = new ModeloCancion(etnuevonombre.getText().toString(), prodele.getId());
                int filasAfectadas = controller.cambioCancion(newObj);
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