package com.bodega;


public class Producto {
    private static long nextId = 1;

    private Long id;
    private String nombre;
    private int stock;

    public Producto(String nombre, int stock) {
        this.id = nextId++;
        this.nombre = nombre;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

