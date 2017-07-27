package com.uss.crist.quieremecix.Servicios;

import java.math.BigInteger;

/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {

    private static final String IP = "192.168.1.35";

    private static final String SERVIDOR = "http://quieremeong.creating-solutions.net/v1/";

    /**
     * URLs del Web Service
     */

    /* Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = ":8000";

    public static final String URL_BASE = "http://" + IP + PUERTO_HOST + "/quieremeCix";
    public static final String INSERTAR_PERSONA = "http://" + SERVIDOR + "personas/crear/";

    public static final String GET_DENUNCIAS = SERVIDOR + "articulos/denuncias/";
    public static final String GET_PERSONA_EMAIL = SERVIDOR + "personas/consultaloginemail/?email=";
    public static final String UPDATE_UNETEID = SERVIDOR + "personas/actualizarunete/";
    public static final String GET_PERSONA_ID = SERVIDOR + "personas/consultapersonaid/?id=";
    public static final String UPDATE_PERFIL = SERVIDOR + "personas/actualizar/";

    public static final String UPDATE_PASSWORD = SERVIDOR + "personas/actualizarpass/";

    public static final String GET_DENUNCIAID= SERVIDOR + "articulos/denunciasid/?id=";
    public static final String INSERTAR_DENUNCIA = SERVIDOR + "articulos/creardenuncia/";
    public static final String GET_PER_EMAIL = SERVIDOR + "personas/consultaemail/?email=";

    public static final String SEND_EMAIL_PERSONA = SERVIDOR + "personas/enviaremail/";

    public static final String GET_ADOPCIONES = SERVIDOR + "articulos/adopciones/";
    public static final String GET_ADOPCIONID= SERVIDOR + "articulos/adopcionesid/?id=";

    public static final String INSERTAR_ADOPCION = SERVIDOR + "articulos/crearadopcion/";
    public static final String ELIMINAR_ARTICULOID = SERVIDOR + "articulos/eliminar/?id=";


    public static final String GET_COMENTARIOS = SERVIDOR + "comentarios/?id=";
    public static final String INSERTAR_COMENTARIO = SERVIDOR + "comentarios/crear/";

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
    public static final String KEY_RATINGBAR = "ratingBar";

    public static final String KEY_CODIGO = "codigo" ;
    public static final String KEY_TIPO_ARTICULO ="tipo_articulo" ;
    public static final String KEY_MENSAJE ="mensaje" ;
    public static final String KEY_ID_ARTICULO ="idarticulo" ;

    /*
    * public static final String URL_BASE = "http://" + IP + PUERTO_HOST + "/quieremeCix";
    public static final String INSERTAR_PERSONA = "http://" + SERVIDOR + "/quieremeCix/registrar_persona.php";

    public static final String GET_DENUNCIAS = "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_denuncias.php";
    public static final String GET_PERSONA_EMAIL = "http://" + IP + PUERTO_HOST + "/quieremeCix/cunsultar_loginEmail.php?email=";
    public static final String UPDATE_UNETEID = "http://" + IP + PUERTO_HOST + "/quieremeCix/actualizar_persona_unete.php";
    public static final String GET_PERSONA_ID = "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_personaID.php?id=";
    public static final String UPDATE_PERFIL = "http://" + IP + PUERTO_HOST + "/quieremeCix/actualizar_perfilID.php";

    public static final String UPDATE_PASSWORD = "http://" + IP + PUERTO_HOST + "/quieremeCix/actualizar_passwordID.php";

    public static final String GET_DENUNCIAID= "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_denunciaID.php?id=";
    public static final String INSERTAR_DENUNCIA = "http://" + IP + PUERTO_HOST + "/quieremeCix/registrar_denuncia.php";
    public static final String GET_PER_EMAIL = "http://" + IP + PUERTO_HOST + "/quieremeCix/consulta_email.php?email=";

    public static final String SEND_EMAIL_PERSONA = "http://" + SERVIDOR + "personas/enviaremail/enviarEmailID.php";

    public static final String GET_ADOPCIONES = "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_adopciones.php";
    public static final String GET_ADOPCIONID= "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_adopcionID.php?id=";

    public static final String INSERTAR_ADOPCION = "http://" + IP + PUERTO_HOST + "/quieremeCix/registrar_adopcion.php";
    public static final String ELIMINAR_ARTICULOID = "http://" + IP + PUERTO_HOST + "/quieremeCix/eliminar_articuloID.php?id=";


    public static final String GET_COMENTARIOS = "http://" + IP + PUERTO_HOST + "/quieremeCix/consultar_comentarios.php?id=";
    public static final String INSERTAR_COMENTARIO = "http://"+ IP + PUERTO_HOST + "/quieremeCix/registrar_comentario.php";
    * */

}
