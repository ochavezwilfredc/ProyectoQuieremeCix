package com.uss.crist.quieremecix.Controlador.Denuncia.Comentario;

/**
 * Created by crist on 27/07/2017.
 */

public class Comentario {
    private String id;
    private String mensaje;
    private String fecha;
    private String id_persona;
    private String autor;
    private String imagen;

    public Comentario(String id, String mensaje, String fecha, String autor) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.autor = autor;
    }

    public Comentario(String id, String mensaje, String fecha, String autor, String imagen) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.autor = autor;
        this.imagen = imagen;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
