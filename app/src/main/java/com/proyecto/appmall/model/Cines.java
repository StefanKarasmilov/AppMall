package com.proyecto.appmall.model;

public class Cines {

    private String nombre;
    private String horarios;
    private String photoUrl;

    public Cines(){}

    public Cines(String nombre, String horarios, String photoUrl) {
        this.nombre = nombre;
        this.horarios = horarios;
        this.photoUrl = photoUrl;
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

}
