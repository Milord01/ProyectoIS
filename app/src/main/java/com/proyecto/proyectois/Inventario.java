package com.proyecto.proyectois;

import java.io.*;
import java.util.ArrayList;

class Inventario {
    private ArrayList<Productos> productos;
    private ArrayList<Productos> productosEscasos;
    private final int LIMITE_ESCASEZ = 10; // Límite para considerar un producto como escaso
    public final String ARCHIVO_INVENTARIO = "inventario.txt"; // Nombre del archivo donde se guardará el inventario

    public Inventario() {
        productos = new ArrayList<>();
        productosEscasos = new ArrayList<>();
        cargarInventarioDesdeArchivo(); // Cargar inventario al iniciar
    }

    public void agregarProducto(Productos producto) {
        productos.add(producto);
        verificarEscasez(producto);
        guardarInventarioEnArchivo(); // Guardar cada vez que se agrega un producto
    }

    public void actualizarPrecio(String nombre, double nuevoPrecio) {
        Productos producto = buscarProducto(nombre);
        if (producto != null) {
            producto.setPrecio(nuevoPrecio);
            System.out.println("Precio actualizado.");
            guardarInventarioEnArchivo(); // Guardar los cambios
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void actualizarCantidad(String nombre, int nuevaCantidad) {
        Productos producto = buscarProducto(nombre);
        if (producto != null) {
            producto.setCantidad(nuevaCantidad);
            System.out.println("Cantidad actualizada.");
            verificarEscasez(producto);
            guardarInventarioEnArchivo(); // Guardar los cambios
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void verificarEscasez(Productos producto) {
        if (producto.getCantidad() < LIMITE_ESCASEZ) {
            if (!productosEscasos.contains(producto)) {
                productosEscasos.add(producto);
                System.out.println("El producto " + producto.getNombre() + " se ha añadido a la lista de productos escasos.");
            }
        } else {
            productosEscasos.remove(producto);
            System.out.println("El producto " + producto.getNombre() + " ya no es escaso y ha sido removido de la lista.");
        }
    }

    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Productos producto : productos) {
                System.out.println(producto);
            }
        }
    }

    public void mostrarProductosEscasos() {
        if (productosEscasos.isEmpty()) {
            System.out.println("No hay productos escasos.");
        } else {
            System.out.println("Productos escasos:");
            for (Productos producto : productosEscasos) {
                System.out.println(producto);
            }
        }
    }

    public Productos buscarProducto(String nombre) {
        for (Productos producto : productos) {
            if (producto.getNombre().equals(nombre)) { // Usar equals() en lugar de ==
                return producto;
            }
        }
        return null;
    }

    // Método para guardar el inventario en un archivo de texto
    private void guardarInventarioEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_INVENTARIO))) {
            for (Productos producto : productos) {
                writer.write(producto.getNombre() + "," + producto.getPrecio() + "," + producto.getCantidad());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    // Método para cargar el inventario desde un archivo de texto
    private void cargarInventarioDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_INVENTARIO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                double precio = Double.parseDouble(datos[1]);
                int cantidad = Integer.parseInt(datos[2]);
                Productos producto = new Productos(nombre, precio, cantidad);
                productos.add(producto);
                verificarEscasez(producto); // Verificar si el producto es escaso
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, comenzando con un inventario vacío.");
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
 }
}
}