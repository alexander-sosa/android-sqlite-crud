package com.example.androidfinal.models;

public class ModeloItem {
    private String nombre;
    private int artista;
    private long id;

    public ModeloItem(String nombre, int artista) {
        this.nombre = nombre;
        this.artista = artista;
    }

    public ModeloItem(String nombre, int artista, long id) {
        this.nombre = nombre;
        this.artista = artista;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getArtista() {
        return artista;
    }

    public void setArtista(int artista) {
        this.artista = artista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ModeloItem{" +
                "nombre='" + nombre + '\'' +
                ", artista=" + artista +
                ", id=" + id +
                '}';
    }
}