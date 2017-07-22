package com.uss.crist.quieremecix.Servicios;

/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = ":8000";
    /**
     * Dirección IP AVD
     */
    private static final String IP = "192.168.0.12";

    /**
     * URLs del Web Service
     */
    public static final String URL_BASE = "http://" + IP + PUERTO_HOST + "/quieremeCix";
    public static final String INSERTAR_PERSONA = "http://" + IP + PUERTO_HOST + "/quieremeCix/registrar_persona.php";
    public static final String GET_DENUNCIAS = "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_denuncias.php";
    public static final String GET_PERSONA_EMAIL = "http://" + IP + PUERTO_HOST + "/quieremeCix/cunsultar_loginEmail.php?email=";
    public static final String UPDATE_UNETEID = "http://" + IP + PUERTO_HOST + "/quieremeCix/actualizar_persona_unete.php";
    public static final String GET_PERSONA_ID = "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_personaID.php?id=";
    public static final String UPDATE_PERFIL = "http://" + IP + PUERTO_HOST + "/quieremeCix/actualizar_perfilID.php";
    public static final String UPDATE_PASSWORD = "http://" + IP + PUERTO_HOST + "/quieremeCix/actualizar_passwordID.php";
    public static final String GET_DENUNCIAID= "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_denunciaID.php?id=";
    public static final String INSERTAR_DENUNCIA = "http://" + IP + PUERTO_HOST + "/quieremeCix/registrar_denuncia.php";
    public static final String GET_IMAGENES = "http://" + IP + PUERTO_HOST + "/quieremeCix/uploads/";
    /**
     * Constantes de envio de parametros
     *
     */
    public static final String KEY_ID = "id";
    public static final String KEY_NOM_APE = "nom_ape";
    public static final String KEY_FECHA_NAC = "fecha_nac";
    public static final String KEY_DNI = "dni";
    public static final String KEY_SEXO = "sexo";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CLAVE = "clave";
    public static final String KEY_TELEFONO = "telefono";
    public static final String KEY_NOMBRE_FOTO = "nombre_foto";
    public static final String KEY_IMAGEN = "foto";
    public static final String KEY_UNETE = "unete";
    public static final String KEY_PASS = "pass";

    //--------- Articulo --------
    public static final String KEY_TITULO = "titulo";
    public static final String KEY_LUGAR = "lugar";
    public static final String KEY_RAZA = "raza";
    public static final String KEY_COLOR = "color";
    public static final String KEY_TIPO_MASC = "tipo_masc";
    public static final String KEY_LATITUD = "latitud";
    public static final String KEY_LONGITUD = "longitud";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_ID_PERSONA = "id_persona";


}
