package com.uss.crist.quieremecix.Modelo;

/**
 * Created by crist on 12/07/2017.
 */

public class Persona {
    private String id, nom_ape, fecha_nac, sexo, dni, telefono, correo, foto, unido;

    public Persona() {
    }

    public Persona(String id, String nom_ape, String fecha_nac, String sexo, String dni, String telefono, String correo, String foto) {
        this.id = id;
        this.nom_ape = nom_ape;
        this.fecha_nac = fecha_nac;
        this.sexo = sexo;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.foto = foto;
    }


    public Persona(String nom_ape, String fecha_nac, String dni, String telefono, String correo, String foto, String unido) {
        this.nom_ape = nom_ape;
        this.fecha_nac = fecha_nac;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.foto = foto;
        this.unido = unido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_ape() {
        return nom_ape;
    }

    public void setNom_ape(String nom_ape) {
        this.nom_ape = nom_ape;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUnido() {
        return unido;
    }

    public void setUnido(String unido) {
        this.unido = unido;
    }

    @Override
    public String toString(){
        return nom_ape+" "+fecha_nac+" "+dni+" "+telefono+" "+correo+" "+unido+" "+foto;
    }
}
