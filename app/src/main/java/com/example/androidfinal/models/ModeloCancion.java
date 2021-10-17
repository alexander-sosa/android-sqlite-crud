package com.example.androidfinal.models;

public class ModeloCancion {
    private String nombre;
    private String nombreArtista;
    private long idArtista;
    private long id;

    public ModeloCancion(String nombre, String nombreArtista) {
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
    }

    public ModeloCancion(String nombre, int idArtista) {
        this.nombre = nombre;
        this.idArtista = idArtista;
    }

    public ModeloCancion(String nombre, long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public ModeloCancion(String nombre, String nombreArtista, long id) {
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
        this.id = id;
    }

    public ModeloCancion(String nombre, long idArtista, long id) {
        this.nombre = nombre;
        this.idArtista = idArtista;
        this.id = id;
    }

    public ModeloCancion(String nombre, String nombreArtista, long idArtista, long id) {
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
        this.idArtista = idArtista;
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

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public long getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(long idArtista) {
        this.idArtista = idArtista;
    }

    @Override
    public String toString() {
        return "ModeloArtist{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", nombre artista=" + nombreArtista +
                ", id artista=" + idArtista +
                '}';
    }
}
