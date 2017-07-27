package com.uss.crist.quieremecix.Controlador.Denuncia.Comentario;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.uss.crist.quieremecix.Controlador.Denuncia.Denuncia;
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
 * Created by crist on 27/07/2017.
 */

public class ComentarioAdapter extends ArrayAdapter {
    // Atributos
    JsonObjectRequest jsArrayRequest;
    private static final String TAG = "PostAdapter";
    public static List<Comentario> lista_comentarios;
    Servicios servicios;


    public ComentarioAdapter(Context context, String id) {
        super(context,0);
        //Crear objeto servicios
        servicios = new Servicios();

        // Añadir petición GSON a la cola
        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                Constantes.GET_COMENTARIOS+id,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        lista_comentarios = parseJson(response);
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
        return lista_comentarios != null ? lista_comentarios.size() : 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.post_comentarios,
                parent,
                false) : convertView;


        // Obtener el item actual
        Comentario item = lista_comentarios.get(position);
        //servicios.mensaje((Activity) getContext(),item.getId());

        // Obtener Views
        TextView textoComentario = (TextView) listItemView.
                findViewById(R.id.texto_comentario);
        TextView textoAutor = (TextView) listItemView.
                findViewById(R.id.author_comentario);
        TextView textoFecha = (TextView) listItemView.
                findViewById(R.id.fecha_comentario);


        final ImageView imagenPost = (ImageView) listItemView.
                findViewById(R.id.image_comentario_perfil);

        // Actualizar los Views
        textoComentario.setText(item.getMensaje());
        textoAutor.setText(item.getAutor());
        textoFecha.setText(item.getFecha());

        // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                Constantes.URL_BASE + "/uploads/"+item.getImagen(),
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
    public Comentario obtenerComentario(int post){
        // Obtener el item actual
        Comentario item = lista_comentarios.get(post);

        return item;
    }

    public List<Comentario> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Comentario> posts = new ArrayList<>();
        JSONArray jsonArray= null;
        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("comentarios");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Comentario post = new Comentario(
                            objeto.getString("id"),
                            objeto.getString("mensaje"),
                            objeto.getString("fecha"),
                            objeto.getString("autor"),
                            objeto.getString("imagen"));

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

