package com.uss.crist.quieremecix.Controlador.Denuncia;

import static android.R.string.no;

/**
 * Created by crist on 11/07/2017.
 */

public class Denuncia {
    // Atributos
    private String id;
    private String titulo;
    private String fecha;
    private String latitud;
    private String longitud;
    private String descripcion;
    private String tipo_mas;
    private String raza;
    private String color;
    private String lugar;
    private String imagen;
    private String nom_ape;

    public Denuncia() {
    }

    public Denuncia(String id, String titulo, String fecha, String latitud, String longitud, String descripcion, String tipo_mas, String raza, String color, String lugar, String imagen, String nom_ape) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.tipo_mas = tipo_mas;
        this.raza = raza;
        this.color = color;
        this.lugar = lugar;
        this.imagen = imagen;
        this.nom_ape = nom_ape;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTipo_mas() {
        return tipo_mas;
    }

    public void setTipo_mas(String tipo_mas) {
        this.tipo_mas = tipo_mas;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNom_ape() {
        return nom_ape;
    }

    public void setNom_ape(String nom_ape) {
        this.nom_ape = nom_ape;
    }

    public String toString(){
        return id+titulo+fecha+latitud+longitud+lugar+raza+color+descripcion+imagen+nom_ape;
    }


}

