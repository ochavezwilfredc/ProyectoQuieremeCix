package com.uss.crist.quieremecix.Servicios;


import android.app.Activity;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by crist on 10/07/2017.
 */

public class Servicios {



    //----------------------------------------------------------------------------------------------
    //Comprobar si un email es valido o no
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate given email with regular expression.
     *
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }


    public boolean validarNombre(String nombre){
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(nombre).matches();
    }


    //----------------------------------------------------------------------------------------------
    //Comprobar si la contraseña ingresada cumple con restricciones establecidas
    public boolean isPasswordValid(String password) {
        /**Si la cadena supera los 4 caracteres es una contraseña valida*/
        return password.length() > 4;
    }

    public boolean validadTelefono(String telefono){
        return Patterns.PHONE.matcher(telefono).matches();
    }

    public void compartirCon(Activity activity){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Quiereme! https://quiereme.jimdo.com/");
        activity.startActivity(intent.createChooser(intent,"Compartir con"));
    }

    public boolean validarPasswordIguales(String p1, String p2){

        return p1.equals(p2);
    }

    public String getNombreImg(String n){
        String nombre = "";
        int i=0;
        while(n.charAt(i)!=32){
            nombre = nombre+n.charAt(i);
            i++;
        }
        return nombre+".png";
    }

    public void mensaje(Activity activity,String m){
        Toast.makeText(activity,m,Toast.LENGTH_LONG).show();

    }


    /**
     * Crear ID para google Maps
     * keytool -list -v -keystore "debug.keystore" -alias androiddebugkey -storepass android -keypass android
     */

}
