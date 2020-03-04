package com.proyecto.appmall.response;

public class Inicio {

    private String nombreTienda;
    private String descripcion;
    private String photoUrl;

    public Inicio(String nombreTienda, String descripcion, String photoUrl) {
        this.nombreTienda = nombreTienda;
        this.descripcion = descripcion;
        this.photoUrl = photoUrl;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
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
