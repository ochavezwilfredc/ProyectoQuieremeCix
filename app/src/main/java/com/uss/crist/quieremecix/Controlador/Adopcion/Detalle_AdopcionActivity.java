package com.uss.crist.quieremecix.Controlador.Adopcion;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class Detalle_AdopcionActivity extends AppCompatActivity {
    String id_articulo, rating, tipo_articulo;
    Servicios servicios;

    private ImageView detalleImagen;
    private TextView detalleTitulo, detalleFecha, detalleDescripcion, detalleAutor;
    private FloatingActionButton fab_localizacion;
    private RatingBar ratingBar;

    // Atributos
    JsonObjectRequest jsArrayRequest;
    private static final String TAG = "PostDetalleAdopcion";
    Adopcion adopcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__adopcion);

        servicios = new Servicios();

        Bundle bundle = getIntent().getExtras();
        id_articulo =  bundle.getString(Constantes.KEY_ID);
        tipo_articulo = bundle.getString(Constantes.KEY_TIPO_ARTICULO);

        detalleTitulo =(TextView)findViewById(R.id.detalle_titulo_ado);
        detalleFecha =(TextView)findViewById(R.id.detalle_fecha_ado);
        detalleDescripcion =(TextView)findViewById(R.id.detalle_descripcion_ado);
        detalleAutor =(TextView)findViewById(R.id.detalle_autor_ado);
        detalleImagen = (ImageView)findViewById(R.id.detalle_imagen_ado);

        ratingBar =(RatingBar)findViewById(R.id.detalle_rating_ado);


        peticionDatos();



    }


    public void peticionDatos(){
        adopcion = new Adopcion();

        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                Constantes.GET_ADOPCIONID+id_articulo,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        adopcion = parseJsonAdopcion(response);
                        obtenerDatosAdopcion();
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
        VolleySingleton.getInstance(Detalle_AdopcionActivity.this).addToRequestQueue(jsArrayRequest);
    }

    public void obtenerDatosAdopcion(){
        // Actualizar los Views
        detalleTitulo.setText(adopcion.getTitulo()+" de Raza "+adopcion.getRaza());
        detalleFecha.setText(adopcion.getFecha());
        detalleDescripcion.setText(adopcion.getDescripcion());
        detalleAutor.setText("Autor: "+adopcion.getNom_ape());
        ratingBar.setRating(Float.parseFloat(adopcion.getRatingBar()));
        // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                Constantes.URL_BASE + adopcion.getImagen(),
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
        VolleySingleton.getInstance(Detalle_AdopcionActivity.this).addToRequestQueue(request);


    }

    public Adopcion parseJsonAdopcion(JSONObject response) {
        Adopcion art = new Adopcion();
        JSONArray jsonArray= null;
        try {
            // Obtener el array del objeto
            jsonArray = response.getJSONArray("adopcion");


            try {
                JSONObject objeto= jsonArray.getJSONObject(0);

                Adopcion adopcion = new Adopcion(
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
                        objeto.getString("nom_ape"),
                        objeto.getString("ratingBar"));


                art=adopcion;

            } catch (JSONException e) {
                Log.e(TAG, "Error de parsing: "+ e.getMessage());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return art;
    }
}
