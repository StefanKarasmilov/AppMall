package com.proyecto.appmall.model;

public class Tiendas {

    private String id;
    private String nombre;
    private String descripcion;
    private String horario;
    private String web;
    private String photoUrl;
    private String numeroTel;
    private String  fechaPublicacion;

    public Tiendas(){}

    public Tiendas(String nombre, String descripcion, String horario, String web, String photoUrl, String numeroTel, String fechaPublicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.web = web;
        this.photoUrl = photoUrl;
        this.numeroTel = numeroTel;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPhotoUrl(){
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

    public String getNumeroTel(){
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel){
        this.numeroTel = numeroTel;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
