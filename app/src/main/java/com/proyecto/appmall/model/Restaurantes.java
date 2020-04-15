package com.proyecto.appmall.model;

public class Restaurantes {

    private String id;
    private String nombre;
    private String descripcion;
    private String horario;
    private String telefono;
    private float rating;
    private String photoUrl;

    public Restaurantes(){}

    public Restaurantes(String nombre, String descripcion, String horario, String telefono, float rating, String photoUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.telefono = telefono;
        this.rating = rating;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
