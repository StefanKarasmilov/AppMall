package com.proyecto.appmall.model;

public class Restaurantes {

    private String nombre;
    private String descripcion;
    private String horario;
    private int telefono;
    private String photoUrl;

    public Restaurantes(String nombre, String descripcion, String horario, int telefono, String photoUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.telefono = telefono;
        this.photoUrl = photoUrl;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
