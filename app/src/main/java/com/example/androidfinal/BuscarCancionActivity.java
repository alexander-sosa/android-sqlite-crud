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
import com.example.androidfinal.models.ModeloCancion;

public class BuscarCancionActivity extends AppCompatActivity {

    EditText etIDBuscaCancion;
    Button btBuscarCancion, btNuevoBuscarCancion;
    TextView tvRecuperadoCancion;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cancion);

        etIDBuscaCancion = findViewById(R.id.etIDBuscarCancion);
        btBuscarCancion = findViewById(R.id.btBuscarCancion);
        btNuevoBuscarCancion = findViewById(R.id.btNuevoBuscarCancion);
        tvRecuperadoCancion = findViewById(R.id.tvRecuperadoCancion);
        controller = new Controller(BuscarCancionActivity.this);

        btBuscarCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModeloCancion objBus = new ModeloCancion("", Long.parseLong(etIDBuscaCancion.getText().toString()));
                ModeloCancion resultado = controller.buscaCancion(objBus);
                if(resultado != null){
                    Toast.makeText(getApplicationContext(),"Encontrado " + resultado.getNombre(), Toast.LENGTH_LONG).show();
                    tvRecuperadoCancion.setText(resultado.toString());
                }
                else
                    Toast.makeText(getApplicationContext(), "ERROR: id inexistente", Toast.LENGTH_SHORT).show();
            }
        });

        btNuevoBuscarCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIDBuscaCancion.setText(null);
                tvRecuperadoCancion.setText(null);
                etIDBuscaCancion.requestFocus();
            }
        });
    }
}