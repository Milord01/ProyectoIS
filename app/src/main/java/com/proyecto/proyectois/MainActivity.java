package com.proyecto.proyectois;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregarProducto, btnActualizarPrecio, btnActualizarCantidad, btnRevisarInventario, btnProductosEscasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los botones
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        btnActualizarPrecio = findViewById(R.id.btnActualizarPrecio);
        btnActualizarCantidad = findViewById(R.id.btnActualizarCantidad);
        btnRevisarInventario = findViewById(R.id.btnRevisarInventario);
        btnProductosEscasos = findViewById(R.id.btnProductosEscasos);

        // Agregar listeners a los botones para que naveguen a las actividades correspondientes
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de AgregarProductoActivity
                Intent intent = new Intent(MainActivity.this, AgregarProductoActivity.class);
                startActivity(intent);
            }
        });

        btnActualizarPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de ActualizarPrecioActivity
                Intent intent = new Intent(MainActivity.this, ActualizarPrecioActivity.class);
                startActivity(intent);
            }
        });

        btnActualizarCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de ActualizarCantidadActivity
                Intent intent = new Intent(MainActivity.this, ActualizarCantidadActivity.class);
                startActivity(intent);
            }
        });

        btnRevisarInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de RevisarInventarioActivity
                Intent intent = new Intent(MainActivity.this, RevisarInventarioActivity.class);
                startActivity(intent);
            }
        });

        btnProductosEscasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de ProductosEscasosActivity
                Intent intent = new Intent(MainActivity.this, ProductosEscasosActivity.class);
                startActivity(intent);
            }
   });
}
}