package com.example.androidfinal.models;

public class ModeloArtist {
    private String nombre;
    private long id;

    public ModeloArtist(String nombre) {
        this.nombre = nombre;
    }

    public ModeloArtist(String nombre, long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ModeloArtist{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}