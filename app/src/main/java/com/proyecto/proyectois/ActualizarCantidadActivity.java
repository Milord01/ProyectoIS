package com.proyecto.proyectois;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActualizarCantidadActivity extends AppCompatActivity {

    private EditText etNombre, etNcantidad;
    private Button btnActualizarCantidad;
    private static final String FILE_NAME = "inventario.txt";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizarcantidad);

        // Initialize the EditTexts and Button
        etNombre = findViewById(R.id.etNombre);
        etNcantidad = findViewById(R.id.etNcantidadProducto);
        btnActualizarCantidad = findViewById(R.id.btnActualizarCantidad);

        // Set onClickListener for the button
        btnActualizarCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCantidad();
            }
        });
    }

    private void actualizarCantidad() {
        String nombre = etNombre.getText().toString().trim();
        String nuevoCantidadStr = etNcantidad.getText().toString().trim();

        if (nombre.isEmpty() || nuevoCantidadStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double nuevoCantidad; //formato 1.0
        try {
            nuevoCantidad = Double.parseDouble(nuevoCantidadStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, introduce un valor válido para el precio", Toast.LENGTH_SHORT).show();
            return;
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();
        boolean productoEncontrado = false;

        try {
            fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase(nombre)) {
                    line = nombre + "|" + parts[1] + "|" + nuevoCantidad;//pilas
                    productoEncontrado = true;
                }
                lines.add(line);
            }

            if (productoEncontrado) {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                for (String updatedLine : lines) {
                    fos.write((updatedLine + "\n").getBytes());
                }
                Toast.makeText(this, "Precio actualizado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("ActualizarCantidad", "Error al actualizar el cantidad", e);
            Toast.makeText(this, "Ocurrió un error al actualizar el cantidad", Toast.LENGTH_LONG).show();
        } finally {
            try {
                if (reader != null) reader.close();
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
