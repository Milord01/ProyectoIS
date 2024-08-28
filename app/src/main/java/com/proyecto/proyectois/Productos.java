package com.proyecto.proyectois;

public class Productos {
    private String nombre;
    private double precio;
    private int cantidad;

    public Productos(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad;
}

}