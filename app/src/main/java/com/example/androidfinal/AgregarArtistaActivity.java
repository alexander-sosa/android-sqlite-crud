package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfinal.helpers.Controller;
import com.example.androidfinal.models.ModeloArtist;

public class AgregarArtistaActivity extends AppCompatActivity {

    EditText etNombre;
    Button btGuardar, btSalir;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_artista);

        etNombre = findViewById(R.id.etAgregarArtistaNombre);
        btGuardar = findViewById(R.id.btGuardar);
        btSalir = findViewById(R.id.btSalir);
        controller = new Controller(AgregarArtistaActivity.this);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();

                ModeloArtist nuevoProducto = new ModeloArtist(nombre);
                long res = controller.nuevoArtista(nuevoProducto);
                if(res == -1){
                    // hay error
                    Toast.makeText(getApplicationContext(), "ERROR: el registro no se ha guardado", Toast.LENGTH_SHORT).show();
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

    void limpiaCampos(){
        etNombre.setText(null);
        etNombre.requestFocus();
    }
}