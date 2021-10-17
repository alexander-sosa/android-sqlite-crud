package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidfinal.helpers.Controller;
import com.example.androidfinal.models.ModeloArtist;
import com.example.androidfinal.models.ModeloCancion;

import java.util.ArrayList;

public class AgregarCancionActivity extends AppCompatActivity {

    EditText etNombreCancion, etArtistaID;
    int idArtista;
    Button btGuardar, btSalir;
    Controller controller;

    Spinner spArtistaID;
    ArrayList<ModeloArtist> artistas;
    ArrayList<String> stringsArtistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cancion);

        etNombreCancion = findViewById(R.id.etAgregarCancionNombre);
        //etArtistaID = findViewById(R.id.etAgregarCancionArtistaID);
        btGuardar = findViewById(R.id.btGuardarNuevaCancion);
        btSalir = findViewById(R.id.btSalirNuevaCancion);
        controller = new Controller(AgregarCancionActivity.this);

        spArtistaID = findViewById(R.id.spnSelectArtista);
        stringsArtistas = new ArrayList<>();
        loadSpinner();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, stringsArtistas);
        spArtistaID.setAdapter(adaptador);
        spArtistaID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    idArtista = (int) artistas.get(position-1).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreCancion.getText().toString();
                //int idArtista = Integer.parseInt(etArtistaID.getText().toString());

                ModeloCancion nuevoProducto = new ModeloCancion(nombre, idArtista);
                Log.i("phx", nuevoProducto.toString());
                long res = controller.nuevaCancion(nuevoProducto);
                Log.i("phx", "Llega: " + res);
                if(res == -1){
                    // hay error
                    Toast.makeText(getApplicationContext(), "ERROR: el ID artista ingresado no existe", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    limpiaCampos();
                }
            }
        });

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SALIR", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    void loadSpinner(){
        artistas = controller.obtenerArtistas();
        stringsArtistas.add("Artista...");
        for(int i = 0; i<artistas.size(); i++){
            stringsArtistas.add(artistas.get(i).getNombre() + " (" + artistas.get(i).getId() + ")");
        }
    }

    void limpiaCampos(){
        etNombreCancion.setText(null);
        //etArtistaID.setText(null);
        idArtista = -1;
        etNombreCancion.requestFocus();
    }

}