package com.uss.crist.quieremecix.Controlador;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uss.crist.quieremecix.Modelo.Requesthandler;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_nom_ape,et_dni,et_mail, et_pass, et_pass2, et_fechaNac, et_tel;
    private Button btn_aceptar, btn_cancelar, btn_fechaNac;
    private RadioButton rb_hombre,rb_mujer;
    private String sexo;
    private  int dia,mes,ano;
    private CircleImageView imagen_perfil;
    private boolean ban=true;

    Servicios servicios;

    //Imagen de perfil
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        servicios = new Servicios();

        imagen_perfil = (CircleImageView)findViewById(R.id.imagen_perfil);
        imagen_perfil.setOnClickListener(this);
        btn_aceptar = (Button)findViewById(R.id.btn_aceptar);
        btn_aceptar.setOnClickListener(RegistrarActivity.this);
        btn_cancelar = (Button)findViewById(R.id.btn_cancelar_rc);
        btn_cancelar.setOnClickListener(RegistrarActivity.this);

        btn_fechaNac = (Button)findViewById(R.id.btn_fechaNac);
        btn_fechaNac.setOnClickListener(RegistrarActivity.this);

        et_mail =(EditText)findViewById(R.id.et_email);
        et_pass =(EditText)findViewById(R.id.et_clave);
        et_pass2 =(EditText)findViewById(R.id.et_clave2);
        et_fechaNac =(EditText)findViewById(R.id.et_fechaNac);
        et_nom_ape =(EditText)findViewById(R.id.et_nom_ape);
        et_dni =(EditText)findViewById(R.id.et_dni);
        et_tel =(EditText)findViewById(R.id.et_telefono);

        rb_hombre = (RadioButton)findViewById(R.id.rb_hombre);
        rb_mujer = (RadioButton)findViewById(R.id.rb_mujer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagen_perfil :
                RegistrarActivity.this.showFileChooser();
                break;
            case R.id.btn_aceptar:
                if(rb_hombre.isChecked()==true){
                    sexo ="1";
                }else if(rb_mujer.isChecked()==true){
                    sexo="2";
                }
                //Validar valores de entrada
                if (validar()){
                    addUsuario();
                   // RegistrarPersona();
                    finish();
                }else{
                    servicios.mensaje(this,"Error en los valores ingresados!");
                }

                break;
            case R.id.btn_cancelar_rc:
                salir().show();
                break;
            case R.id.btn_fechaNac:
                entradaFecha();
                break;
        }


    }


    private Boolean validar(){
        Boolean ok = false;
        if(!servicios.validarNombre(et_nom_ape.getText().toString().trim())){
            et_nom_ape.setError(getString(R.string.error_nombre));
            et_nom_ape.requestFocus();
            }else{
                if(et_fechaNac.getText().toString().length()==0){
                    et_fechaNac.setError(getString(R.string.error_fechaNac));
                    btn_fechaNac.requestFocus();
                }else{
                    if(!(et_dni.getText().toString().length()==8)){
                        et_dni.setError(getString(R.string.error_dni));
                        et_dni.requestFocus();
                    }else{
                        if((!servicios.validateEmail(et_mail.getText().toString().trim())) &&(!servicios.validateEmail(et_mail.getText().toString().trim()))){
                            et_mail.setError(getString(R.string.error_invalid_email));
                            et_mail.requestFocus();
                        }else{
                            if(!servicios.isPasswordValid(et_pass.getText().toString().trim())){
                                et_pass.setError(getString(R.string.error_incorrect_password));
                                et_pass.requestFocus();
                            }else{
                                if(!servicios.isPasswordValid(et_pass2.getText().toString().trim())){
                                    et_pass2.setError(getString(R.string.error_incorrect_password));
                                    et_pass2.requestFocus();
                                }else{
                                    if (!servicios.validarPasswordIguales(et_pass.getText().toString(),et_pass2.getText().toString())){
                                        et_pass.setError(getString(R.string.error_incorrect_password_iquals));
                                        et_pass.requestFocus();
                                    }else{
                                        if(ban){
                                            servicios.mensaje(RegistrarActivity.this,"Error - Seleccione una imgaen de perfil");
                                            imagen_perfil.requestFocus();
                                        }else {
                                            ok=true;
                                        }

                                    }
                                }


                            }
                        }
                    }
                }

        }
        return ok;


    }



    //*********************** Registrar una Persona  ***********************

    //*** Imagen de perfil ****

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    //***** --------------- Inicio del Registro de persona *******
/*
    private void RegistrarPersona(){

        //Mostrar el diálogo de progreso
        final ProgressDialog loading = new ProgressDialog(RegistrarActivity.this);
        loading.setTitle("Agregando");
        loading.setMessage("Espere...");
        loading.setIndeterminate(true);
        loading.show();
        //final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.INSERTAR_PERSONA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(RegistrarActivity.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(RegistrarActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                String nom_ape =  et_nom_ape.getText().toString().trim();
                String fechaN =  et_fechaNac.getText().toString().trim();
                String dni =  et_dni.getText().toString().trim();
                String email =  et_mail.getText().toString().trim();
                String pass =  et_pass.getText().toString().trim();
                String tel =  et_tel.getText().toString().trim();
                String nombre_foto =  servicios.getNombreImg(nom_ape);

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(Constantes.KEY_NOM_APE, nom_ape);
                params.put(Constantes.KEY_FECHA_NAC, fechaN);
                params.put(Constantes.KEY_DNI, dni);
                params.put(Constantes.KEY_SEXO, sexo);
                params.put(Constantes.KEY_EMAIL, email);
                params.put(Constantes.KEY_CLAVE, pass);
                params.put(Constantes.KEY_TELEFONO, tel);
                params.put(Constantes.KEY_NOMBRE_FOTO, nombre_foto);
                params.put(Constantes.KEY_IMAGEN, imagen);

                //Parámetros de retorno
                return params;
            }
        };

            //Agregar solicitud a la cola
        VolleySingleton.getInstance(RegistrarActivity.this).addToRequestQueue(stringRequest);

    }*/


    private void addUsuario(){
        //Convertir bits a cadena
        final String imagen = getStringImagen(bitmap);

        final String nom_ape =  et_nom_ape.getText().toString().trim();
        final String fechaN =  et_fechaNac.getText().toString().trim();
        final String dni =  et_dni.getText().toString().trim();
        final String email =  et_mail.getText().toString().trim();
        final String pass =  et_pass.getText().toString().trim();
        final String tel =  et_tel.getText().toString().trim();
        final String nombre_foto =  servicios.getNombreImg(nom_ape);


        //Ejecuta hilos secundarios o asincronos
        class addPersona extends AsyncTask<Void,Void,String> {

            ProgressDialog loaddin = new ProgressDialog(RegistrarActivity.this);


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin.setTitle("Agregando");
                loaddin.setMessage("Espere...");
                loaddin.setIndeterminate(true);
                loaddin.show();

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                Toast.makeText(RegistrarActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                //Agregando de parámetros
                params.put(Constantes.KEY_NOM_APE, nom_ape);
                params.put(Constantes.KEY_FECHA_NAC, fechaN);
                params.put(Constantes.KEY_DNI, dni);
                params.put(Constantes.KEY_SEXO, sexo);
                params.put(Constantes.KEY_EMAIL, email);
                params.put(Constantes.KEY_CLAVE, pass);
                params.put(Constantes.KEY_TELEFONO, tel);
                params.put(Constantes.KEY_NOMBRE_FOTO, nombre_foto);
                params.put(Constantes.KEY_IMAGEN, imagen);
                Requesthandler rh =  new Requesthandler();

                String res =  rh.sendPostRequest(Constantes.INSERTAR_PERSONA,params);
                return res;
            }
        }

        addPersona ae =  new addPersona();
        ae.execute();

    }




    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        ban=false;
    }



    //---------------------------- Extraer la URL de la imagen en nuestro dispositivo ----------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imagen_perfil.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //********************* Fin *******************************************


    public void entradaFecha(){
        int dia = 1990;
        int mes = 1;
        int ano = 1;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                et_fechaNac.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        }
                ,dia,mes,ano);
        datePickerDialog.show();
    }

    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_warning)
                .setMessage("¿ Esta seguro ?")

                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        return builder.create();
    }
}
