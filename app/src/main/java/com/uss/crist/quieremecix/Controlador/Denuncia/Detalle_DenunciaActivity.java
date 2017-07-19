package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Detalle_DenunciaActivity extends AppCompatActivity {
    String id_articulo;
    Servicios servicios;

    private ImageView detalleImagen;
    private TextView detalleTitulo, detalleFecha, detalleDescripcion, detalleAutor;
    private FloatingActionButton fab_localizacion;

    // Atributos
    JsonObjectRequest jsArrayRequest;
    private static final String TAG = "PostDetalleDenuncia";
    Denuncia denuncia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__denuncia);

        servicios = new Servicios();

        Bundle bundle = getIntent().getExtras();
        id_articulo =  bundle.getString(Constantes.KEY_ID);
        //servicios.mensaje(Detalle_DenunciaActivity.this,id_articulo);

        detalleTitulo =(TextView)findViewById(R.id.detalle_titulo);
        detalleFecha =(TextView)findViewById(R.id.detalle_fecha);
        detalleDescripcion =(TextView)findViewById(R.id.detalle_descripcion);
        detalleAutor =(TextView)findViewById(R.id.detalle_autor);
        detalleImagen = (ImageView)findViewById(R.id.detalle_imagen);
        fab_localizacion = (FloatingActionButton)findViewById(R.id.fab_localizacion);
        peticionDatos();

        fab_localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Aca va la localizacion de la denuncia", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

    }

    public void peticionDatos(){
        denuncia = new Denuncia();

        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                Constantes.GET_DENUNCIAID+id_articulo,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        denuncia = parseJsonDenuncia(response);
                        //Log.d("AYUDAAAAAAAAA: ",denuncia.toString());
                        obtenerDatosDenuncia();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                }
        );

        // Añadir petición a la cola
        VolleySingleton.getInstance(Detalle_DenunciaActivity.this).addToRequestQueue(jsArrayRequest);
    }

    public void obtenerDatosDenuncia(){
        // Actualizar los Views
        detalleTitulo.setText(denuncia.getTitulo()+" de Raza "+denuncia.getRaza());
        detalleFecha.setText(denuncia.getFecha());
        detalleDescripcion.setText(denuncia.getDescripcion());
        detalleAutor.setText("Autor: "+denuncia.getNom_ape());
        // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                Constantes.URL_BASE + denuncia.getImagen(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        detalleImagen.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        detalleImagen.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });

        // Añadir petición a la cola
        VolleySingleton.getInstance(Detalle_DenunciaActivity.this).addToRequestQueue(request);


    }

    public Denuncia parseJsonDenuncia(JSONObject response) {
        Denuncia art = new Denuncia();
        JSONArray jsonArray= null;
        try {
            // Obtener el array del objeto
            jsonArray = response.getJSONArray("denuncia");


                try {
                    JSONObject objeto= jsonArray.getJSONObject(0);

                    Denuncia denuncia = new Denuncia(
                            objeto.getString("id"),
                            objeto.getString("titulo"),
                            objeto.getString("fecha"),
                            objeto.getString("latitud"),
                            objeto.getString("longitud"),
                            objeto.getString("descripcion"),
                            objeto.getString("tipo_mas"),
                            objeto.getString("raza"),
                            objeto.getString("color"),
                            objeto.getString("lugar"),
                            objeto.getString("imagen"),
                            objeto.getString("nom_ape"));


                    art=denuncia;

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return art;
    }

}
