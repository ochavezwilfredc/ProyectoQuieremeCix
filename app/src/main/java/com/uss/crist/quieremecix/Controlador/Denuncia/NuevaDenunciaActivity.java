package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.uss.crist.quieremecix.Controlador.LoginActivity;
import com.uss.crist.quieremecix.Controlador.RegistrarActivity;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class NuevaDenunciaActivity extends AppCompatActivity implements View.OnClickListener{
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;

    private EditText textoTitulo, textoLugar, textoRaza, textoColor, textoDescripcion,textoTipoMasc;
    private Button btn_tipoMascota, btn_aceptar, btn_cancelar;
    private ImageView imageView;
    Servicios servicios;
    public String id_persona, tipo_m;
    boolean ban=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_denuncia);
        servicios = new Servicios();

        Bundle bundle = getIntent().getExtras();
         id_persona = bundle.getString(Constantes.KEY_ID);

        imageView = (ImageView)findViewById(R.id.imagen_nuevaD);
        imageView.setOnClickListener(this);

        textoTitulo = (EditText)findViewById(R.id.texto_tituloN);
        textoLugar = (EditText)findViewById(R.id.texto_LugarN);
        textoRaza = (EditText)findViewById(R.id.texto_RazaN);
        textoColor = (EditText)findViewById(R.id.texto_ColorN);
        textoTipoMasc = (EditText)findViewById(R.id.texto_TipoMascotaN);
        textoDescripcion = (EditText)findViewById(R.id.texto_DescripcionN);

        btn_tipoMascota = (Button)findViewById(R.id.btn_tipoMascotaN);
        btn_tipoMascota.setOnClickListener(this);

        btn_aceptar = (Button)findViewById(R.id.btn_aceptar_nuevaD);
        btn_aceptar.setOnClickListener(this);

        btn_cancelar = (Button)findViewById(R.id.btn_cancelar_nuevaD);
        btn_cancelar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imagen_nuevaD:
                showFileChooser();
                break;
            case R.id.btn_tipoMascotaN:
                //alertSimpleListView();
                getTipoMascota();
                break;

            case R.id.btn_aceptar_nuevaD:
                if (validar()){
                    RegistrarDenuncia();

                }
                break;

            case R.id.btn_cancelar_nuevaD:
                salir().show();
                break;
        }
    }


    public void getTipoMascota(){

        AlertDialog.Builder builder = new AlertDialog.Builder(NuevaDenunciaActivity.this);

        // Set the dialog title

        final CharSequence[] items = new CharSequence[3];

        items[0] = "Canino";
        items[1] = "Felino";
        items[2] = "Otro";

        builder.setTitle("Seleccione Tipo de Mascota");
        builder.setIcon(R.drawable.ic_tipomascota)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                textoTipoMasc.setText("Canino");
                                tipo_m="1";
                                break;
                            case 1:
                                textoTipoMasc.setText("Felino");
                                tipo_m="2";
                                break;
                            case 2:
                                textoTipoMasc.setText("Otro");
                                tipo_m="3";
                                break;
                        }
                        dialog.dismiss();
                    }
                });



        builder.show();

    }

    public boolean validar(){
        boolean ok=false;
        if (!servicios.validarNombre(textoTitulo.getText().toString())){
            textoTitulo.setError(getString(R.string.error_titulo));
            textoTitulo.findFocus();
        }else{
            if(!servicios.validarNombre(textoLugar.getText().toString())){
                textoLugar.setError(getString(R.string.error_lugar));
                textoLugar.findFocus();
            }else{
                if(!servicios.validarNombre(textoRaza.getText().toString())){
                    textoRaza.setError(getString(R.string.error_raza));
                    textoRaza.findFocus();
                }else{
                    if(!servicios.validarNombre(textoColor.getText().toString())){
                        textoColor.setError(getString(R.string.error_color));
                        textoColor.findFocus();
                    }else{
                        if(textoTipoMasc.getText().length()==0){
                            textoTipoMasc.setError(getString(R.string.error_tipo_mascota));
                            btn_tipoMascota.findFocus();
                        }else{
                            if(textoDescripcion.getText().length()==0){
                                textoDescripcion.setError(getString(R.string.error_descripcion_mascota));
                                textoDescripcion.findFocus();
                            }else{
                                if(ban){
                                    Toast toast = Toast.makeText(NuevaDenunciaActivity.this,"Error - Seleccione una imgaen!",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER| Gravity.CENTER,0,0);
                                    toast.show();
                                    imageView.requestFocus();
                                }else{
                                ok=true;}
                            }
                        }
                    }
                }
            }

        }
        return ok;
    }


    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NuevaDenunciaActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_warning)
                .setMessage("¿ Esta seguro ?")

                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(NuevaDenunciaActivity.this, LoginActivity.class));
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


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        ban=false;
    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------


    //--------------- Registrar Denuncia ------------------

    private void RegistrarDenuncia(){

        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(NuevaDenunciaActivity.this,"Registrando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.INSERTAR_DENUNCIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(NuevaDenunciaActivity.this, s , Toast.LENGTH_LONG).show();

                        //finish();
                        volver_list_denuncias();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();

                        //Showing toast
                        Toast.makeText(NuevaDenunciaActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        finish();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                String titulo =  textoTitulo.getText().toString().trim();
                String lugar =  textoLugar.getText().toString().trim();
                String raza =  textoRaza.getText().toString().trim();
                String color =  textoColor.getText().toString().trim();
                String tipo_mascota =  tipo_m;
                String longitud="-79.88615021109581";//ejemplo aca agregamos lat y long
                String latitud ="-6.795210094984799";
                String descripcion =  textoDescripcion.getText().toString().trim();
                String nombre_foto =  "animal_denuncia"+servicios.codigoAleatorio()+".png";

                //servicios.mensaje(NuevaDenunciaActivity.this,raza+color+tipo_mascota);
                //Log.d("Mascota:       ...x ",raza+color+tipo_mascota);

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(Constantes.KEY_TITULO, titulo );
                params.put(Constantes.KEY_LUGAR, lugar);
                params.put(Constantes.KEY_RAZA, raza);
                params.put(Constantes.KEY_COLOR, color);
                params.put(Constantes.KEY_TIPO_MASC, tipo_mascota);
                params.put(Constantes.KEY_LONGITUD, longitud);
                params.put(Constantes.KEY_LATITUD, latitud);
                params.put(Constantes.KEY_DESCRIPCION, descripcion);
                params.put(Constantes.KEY_ID_PERSONA, id_persona);

                params.put(Constantes.KEY_NOMBRE_FOTO, nombre_foto);
                params.put(Constantes.KEY_IMAGEN, imagen);

                //Parámetros de retorno
                return params;
            }
        };

        //Agregar solicitud a la cola
        VolleySingleton.getInstance(NuevaDenunciaActivity.this).addToRequestQueue(stringRequest);
    }

    //-----------------------------------------------------

    public void volver_list_denuncias(){
        Intent intent = new Intent(NuevaDenunciaActivity.this,DenunciasActivity.class);
        intent.putExtra(Constantes.KEY_ID,id_persona);
        startActivity(intent);

    }
}
