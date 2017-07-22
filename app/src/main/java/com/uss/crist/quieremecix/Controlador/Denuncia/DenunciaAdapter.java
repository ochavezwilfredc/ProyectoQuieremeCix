package com.uss.crist.quieremecix.Controlador.Denuncia;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.uss.crist.quieremecix.Controlador.RegistrarActivity;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crist on 11/07/2017.
 */

public class DenunciaAdapter extends ArrayAdapter {

    // Atributos
    JsonObjectRequest jsArrayRequest;
    private static final String TAG = "PostAdapter";
   public static List<Denuncia> denuncias;
    Servicios servicios;


    public DenunciaAdapter(Context context) {
        super(context,0);
        //Crear objeto servicios
        servicios = new Servicios();

        // Añadir petición GSON a la cola
        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                Constantes.GET_DENUNCIAS,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        denuncias = parseJson(response);
                        notifyDataSetChanged();
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
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsArrayRequest);


    }


    @Override
    public int getCount() {
        return denuncias != null ? denuncias.size() : 0;
    }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            // Referencia del view procesado
            View listItemView;

            //Comprobando si el View no existe
            listItemView = null == convertView ? layoutInflater.inflate(
                    R.layout.post_denuncia,
                    parent,
                    false) : convertView;


            // Obtener el item actual
            Denuncia item = denuncias.get(position);
        //servicios.mensaje((Activity) getContext(),item.getId());

        // Obtener Views
        TextView textoTitulo = (TextView) listItemView.
                findViewById(R.id.textoTitulo);
        TextView textoAutor = (TextView) listItemView.
                findViewById(R.id.textoAutor);
        TextView textoRaza = (TextView) listItemView.
                findViewById(R.id.textoRaza);
        final ImageView imagenPost = (ImageView) listItemView.
                findViewById(R.id.imagenPost);

        // Actualizar los Views
        textoTitulo.setText(item.getTitulo());
        textoAutor.setText(item.getFecha());
        textoRaza.setText(item.getDescripcion());

        // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                Constantes.URL_BASE + item.getImagen(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagenPost.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imagenPost.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });

        // Añadir petición a la cola
        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);

        return listItemView;
    }
    public Denuncia obtenerDenuncia(int post){
        // Obtener el item actual
        Denuncia item = denuncias.get(post);

        return item;
    }

    public List<Denuncia> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Denuncia> posts = new ArrayList<>();
        JSONArray jsonArray= null;
        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("denuncias");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Denuncia post = new Denuncia(
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


                    posts.add(post);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return posts;
    }

}
