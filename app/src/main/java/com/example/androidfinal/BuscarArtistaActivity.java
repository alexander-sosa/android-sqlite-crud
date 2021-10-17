package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfinal.helpers.Controller;
import com.example.androidfinal.models.ModeloArtist;

public class BuscarArtistaActivity extends AppCompatActivity {

    EditText etIDBuscaArtista;
    Button btBuscarArtista, btNuevoBuscarArtista;
    TextView tvRecuperadoArtista;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_artista);

        etIDBuscaArtista = findViewById(R.id.etIDBuscarArtista);
        btBuscarArtista = findViewById(R.id.btBuscarArtista);
        btNuevoBuscarArtista = findViewById(R.id.btNuevoBuscarArtista);
        tvRecuperadoArtista = findViewById(R.id.tvRecuperadoArtista);
        controller = new Controller(BuscarArtistaActivity.this);

        btBuscarArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModeloArtist objBus = new ModeloArtist("", Long.parseLong(etIDBuscaArtista.getText().toString()));
                ModeloArtist resultado = controller.buscaArtista(objBus);
                if(resultado != null){
                    Toast.makeText(getApplicationContext(),"Encontrado " + resultado.getNombre(), Toast.LENGTH_LONG).show();
                    tvRecuperadoArtista.setText(resultado.toString());
                }
                else
                    Toast.makeText(getApplicationContext(), "ERROR: id inexistente", Toast.LENGTH_SHORT).show();
            }
        });

        btNuevoBuscarArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIDBuscaArtista.setText(null);
                tvRecuperadoArtista.setText(null);
                etIDBuscaArtista.requestFocus();
            }
        });
    }
}