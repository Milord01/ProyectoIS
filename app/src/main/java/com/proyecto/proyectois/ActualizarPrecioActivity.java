package com.proyecto.proyectois;

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

public class ActualizarPrecioActivity extends AppCompatActivity {

    private EditText etNombre, etNPrecio;
    private Button btnActualizarPrecio;
    private static final String FILE_NAME = "inventario.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizarprecio);

        // Initialize the EditTexts and Button
        etNombre = findViewById(R.id.etNombre);
        etNPrecio = findViewById(R.id.etNprecioProducto);
        btnActualizarPrecio = findViewById(R.id.btnActualizarPrecio);

        // Set onClickListener for the button
        btnActualizarPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPrecio();
            }
        });
    }

    private void actualizarPrecio() {
        String nombre = etNombre.getText().toString().trim();
        String nuevoPrecioStr = etNPrecio.getText().toString().trim();

        if (nombre.isEmpty() || nuevoPrecioStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double nuevoPrecio;
        try {
            nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
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
                    line = nombre + "|" + nuevoPrecio + "|" + parts[2];
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
            Log.e("ActualizarPrecio", "Error al actualizar el precio", e);
            Toast.makeText(this, "Ocurrió un error al actualizar el precio", Toast.LENGTH_LONG).show();
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
