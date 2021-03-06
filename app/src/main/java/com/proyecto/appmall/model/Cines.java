package com.proyecto.appmall.model;

public class Cines {

    private String id;
    private String nombre;
    private String horarios;
    private String photoUrl;
    private String fechaPublicacion;

    public Cines(){}

    public Cines(String nombre, String horarios, String photoUrl, String fechaPublicacion) {
        this.nombre = nombre;
        this.horarios = horarios;
        this.photoUrl = photoUrl;
        this.fechaPublicacion = fechaPublicacion;
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

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
