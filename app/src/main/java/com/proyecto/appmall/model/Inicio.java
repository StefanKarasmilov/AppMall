package com.proyecto.appmall.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inicio {

    private String id;
    private String nombreOferta;
    private String descripcion;
    private String photoUrl;
    private String  fechaPublicacion;

    public Inicio(){}

    public Inicio(String nombreOferta, String descripcion, String photoUrl, String  fechaPublicacion) {
        this.nombreOferta = nombreOferta;
        this.descripcion = descripcion;
        this.photoUrl = photoUrl;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String  getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String  fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
