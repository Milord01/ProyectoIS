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

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etCantidad;
    private Button btnAgregar;
    private static final String FILE_NAME = "inventario.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproducto);
        setUpView();
    }

    private void setUpView(){
        etNombre = findViewById(R.id.etNombreProducto);
        etPrecio = findViewById(R.id.etPrecioProducto);
        etCantidad = findViewById(R.id.etCantidadProducto);
        btnAgregar = findViewById(R.id.btnGuardarProducto);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile();
            }
        });
    }

    private void saveFile() {
        String nombre = etNombre.getText().toString().trim();
        String precio = etPrecio.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();

        if (nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty()) {
            Toast.makeText(AgregarProductoActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isProductExists(nombre)) {
            Toast.makeText(AgregarProductoActivity.this, "Producto ya existente", Toast.LENGTH_SHORT).show();
            return;
        }

        String textoAsalvar = nombre + "|" + precio + "|" + cantidad + "\n";
        FileOutputStream fileOutputStream = null;

        try {

            fileOutputStream = openFileOutput(FILE_NAME, MODE_APPEND);
            fileOutputStream.write(textoAsalvar.getBytes());
            Toast.makeText(AgregarProductoActivity.this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show();
            Log.d("TAG1", "Fichero salvado en: " + getFilesDir() + "/" + FILE_NAME);


            etNombre.setText("");
            etPrecio.setText("");
            etCantidad.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }}}}

    private boolean isProductExists(String nombre) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase(nombre)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }}}
        return false;}
}
