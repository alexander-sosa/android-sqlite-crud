package com.example.androidfinal;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidfinal.helpers.Controller;
import com.example.androidfinal.models.ModeloArtist;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // aqui se controla los items del menu
        switch (item.getItemId()){
            case R.id.mnuListaArtistas:
                Intent invocarArtistas = new Intent(this, ListaArtistaActivity.class);
                startActivity(invocarArtistas);
                break;
            case R.id.mnuAgregarArtistas:
                Intent invocarAgregarArtista = new Intent(this, AgregarArtistaActivity.class);
                startActivity(invocarAgregarArtista);
                break;
            case R.id.mnuBuscarArtista:
                Intent invocarBuscarArtista = new Intent(this, BuscarArtistaActivity.class);
                startActivity(invocarBuscarArtista);
                break;

            case R.id.mnuListaCanciones:
                Intent invocarCanciones = new Intent(this, ListaCancionActivity.class);
                startActivity(invocarCanciones);
                break;
            case R.id.mnuAgregarCanciones:
                Intent invocarAgregarCancion = new Intent(this, AgregarCancionActivity.class);
                startActivity(invocarAgregarCancion);
                break;
            case R.id.mnuBuscarCancion:
                Intent invocarBuscarCancion = new Intent(this, BuscarCancionActivity.class);
                startActivity(invocarBuscarCancion);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}