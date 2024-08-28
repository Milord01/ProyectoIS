package com.proyecto.proyectois;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductosEscasosActivity extends AppCompatActivity {  // Extiende AppCompatActivity
    private ListView listView;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productosescasos);  // Asegúrate de que activity_productosescasos.xml existe en res/layout

        listView = findViewById(R.id.listView);  // Verifica que el ID del ListView es correcto
        itemList = new ArrayList<>();

        // Cargar los datos del archivo de texto
        cargarProductosEscasosDesdeArchivo();

        // Configurar el adaptador para mostrar los datos en el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_inventario, R.id.texto_item, itemList);
        listView.setAdapter(adapter);

        // Log para verificar que el adaptador tiene datos
        Log.d("ProductosEscasos", "Número de elementos en el adaptador: " + adapter.getCount());
    }

    private void cargarProductosEscasosDesdeArchivo() {
        BufferedReader reader = null;

        try {
            // Obtener la ruta del archivo en la carpeta 'files' de la aplicación
            FileInputStream inputStream = new FileInputStream(new File(getFilesDir(), "inventario.txt"));
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Log para depuración
                Log.d("ProductosEscasos", "Línea leída: " + line);

                // Separar la línea en nombre, precio y cantidad
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String nombre = parts[0];
                    String precio = parts[1];
                    String cantidad = parts[2];

                    // Verificar si la cantidad es menor a 10
                    if (Double.parseDouble(cantidad) < 10) {
                        // Formatear la información para mostrarla en el ListView
                        String item = "Producto: " + nombre + "\nPrecio: " + precio + "\nCantidad: " + cantidad;
                        itemList.add(item);

                        // Log para depuración
                        Log.d("ProductosEscasos", "Producto escaso añadido: " + item);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
