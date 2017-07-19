package com.uss.crist.quieremecix.Controlador;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.uss.crist.quieremecix.Modelo.Requesthandler;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import org.json.JSONArray;
import org.json.JSONException;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btn_actualizar_perfil, btn_actualizar_password;
    String idpersona;
    Servicios servicios;

    //******
    // Atributos
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private String foto_per ="/uploads/";
    private static final String TAG = "PostAdapter";

    //******-******
    private TextView nom_ape, dni, fecha_nac, correo, tel, est_unido;
    private CircleImageView foto_perfil;
    //*******-*****



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        servicios = new Servicios();
        Bundle bundle = getIntent().getExtras();
        idpersona=bundle.getString("id");


        nom_ape = (TextView)findViewById(R.id.tv_nom_ape_per);
        dni = (TextView)findViewById(R.id.tv_dni_per);
        fecha_nac = (TextView)findViewById(R.id.tv_fechaNac_per);
        correo = (TextView)findViewById(R.id.tv_correo_per);
        tel = (TextView)findViewById(R.id.tv_telefono_per);
        est_unido = (TextView)findViewById(R.id.tv_unete_per);
        foto_perfil = (CircleImageView)findViewById(R.id.img_perfil);



        btn_actualizar_perfil = (ImageButton)findViewById(R.id.btn_editar_perfil);
        btn_actualizar_perfil.setOnClickListener(this);
        btn_actualizar_password = (ImageButton)findViewById(R.id.btn_cambiar_password_perfil);
        btn_actualizar_password.setOnClickListener(this);

        PerfilActivity.this.getPerfil();


    }



    private void getPerfil(){

        //Ejecuta hilos secundarios o asincronos
        class getPerfilID extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSONArray per_array = null;
                try {
                    per_array = new JSONArray(s);

                    nom_ape.setText(per_array.getString(0));
                    fecha_nac.setText(per_array.getString(1));
                    dni.setText(per_array.getString(2));
                    correo.setText(per_array.getString(3));
                    tel.setText(per_array.getString(4));

                    if (per_array.getString(5).equals("1")){
                        est_unido.setText(R.string.info_estadoP);
                    }else{
                        est_unido.setText(R.string.info_estadoN);
                    }
                    foto_per = foto_per+per_array.getString(6);

                    PerfilActivity.this.descargarImagen();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                Requesthandler rh =  new Requesthandler();
                String res =  rh.sendGetRequestParam(Constantes.GET_PERSONA_ID,idpersona);
                return res;
            }
        }

        getPerfilID ae =  new  getPerfilID();
        ae.execute();

    }

    public void descargarImagen(){
        // Crear nueva cola de peticiones
        //requestQueue= Volley.newRequestQueue(PerfilActivity.this);
        // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                Constantes.URL_BASE + foto_per,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        foto_perfil.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        foto_perfil.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });

        // Añadir petición a la cola
        VolleySingleton.getInstance(PerfilActivity.this).addToRequestQueue(request);
        //requestQueue.add(request);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_editar_perfil){
            Intent intent = new Intent(PerfilActivity.this, ActualizarPerfilActivity.class);
            intent.putExtra(Constantes.KEY_ID,idpersona);
            intent.putExtra(Constantes.KEY_NOM_APE,nom_ape.getText().toString());
            intent.putExtra(Constantes.KEY_TELEFONO,tel.getText().toString());
            intent.putExtra(Constantes.KEY_EMAIL,correo.getText().toString());
            intent.putExtra(Constantes.KEY_UNETE,est_unido.getText().toString());
            intent.putExtra(Constantes.KEY_IMAGEN,foto_per);
            startActivity(intent);
        }else{
            if (view.getId() == R.id.btn_cambiar_password_perfil) {
                Intent intent = new Intent(PerfilActivity.this, ActulizarPasswordActivity.class);
                intent.putExtra(Constantes.KEY_ID,idpersona);
                startActivity(intent);
            }

        }
    }
}
