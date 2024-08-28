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

public class ProductosEscasosActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productosescasos);

        listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();

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

            FileInputStream inputStream = new FileInputStream(new File(getFilesDir(), "inventario.txt"));
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Log para depuración
                Log.d("ProductosEscasos", "Línea leída: " + line);


                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String nombre = parts[0];
                    String precio = parts[1];
                    String cantidad = parts[2];


                    if (Double.parseDouble(cantidad) < 10) {

                        String item = "Producto: " + nombre + "\nPrecio: " + precio + "\nCantidad: " + cantidad;
                        itemList.add(item);

                        // Log para depuración
                        Log.d("ProductosEscasos", "Producto escaso añadido: " + item);
                    }}}} catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}}}}
