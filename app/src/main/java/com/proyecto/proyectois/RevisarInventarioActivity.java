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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RevisarInventarioActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisarinventario);

        listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();


        cargarListaDesdeArchivo();

        // Configurar el adaptador para mostrar los datos en el ListView usando el diseño personalizado
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_inventario, R.id.texto_item, itemList);
        listView.setAdapter(adapter);

        Log.d("RevisarInventario", "Número de elementos en el adaptador: " + adapter.getCount());
    }

    private void cargarListaDesdeArchivo() {
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = new FileInputStream(new File(getFilesDir(), "inventario.txt"));
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                Log.d("RevisarInventario", "Línea leída: " + line);

                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String nombre = parts[0];
                    String precio = parts[1];
                    String cantidad = parts[2];

                    String item = "Producto: " + nombre + "\nPrecio: " + precio + "\nCantidad: " + cantidad;
                    itemList.add(item);

                    Log.d("RevisarInventario", "Item añadido: " + item);
                }}} catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}}}}
