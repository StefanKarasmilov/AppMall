package com.proyecto.appmall.model;

public class Inicio {

    private String nombreOferta;
    private String descripcion;
    private String photoUrl;

    public Inicio(){}

    public Inicio(String nombreOferta, String descripcion, String photoUrl) {
        this.nombreOferta = nombreOferta;
        this.descripcion = descripcion;
        this.photoUrl = photoUrl;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
