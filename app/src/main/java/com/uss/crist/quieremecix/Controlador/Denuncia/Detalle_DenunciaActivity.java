package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.uss.crist.quieremecix.Controlador.Denuncia.Comentario.ComentarioAdapter;
import com.uss.crist.quieremecix.Controlador.Ubicacion.UbicacionArticuloActivity;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Detalle_DenunciaActivity extends AppCompatActivity {
    String id_articulo, id_persona, rating, tipo_articulo;
    Servicios servicios;

    private ImageView detalleImagen;
    private TextView detalleTitulo, detalleFecha, detalleDescripcion, detalleAutor;
    private FloatingActionButton fab_localizacion;
    private RatingBar ratingBar;
    private EditText txt_comentario;
    private Button btn_enviar;

    // Atributos
    JsonObjectRequest jsArrayRequest;
    private static final String TAG = "PostDetalleDenuncia";
    Denuncia denuncia;

    ListView listView;
    ComentarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__denuncia);

        servicios = new Servicios();

        Bundle bundle = getIntent().getExtras();
        id_articulo =  bundle.getString(Constantes.KEY_ID);
        id_persona =  bundle.getString(Constantes.KEY_ID_PERSONA);
        tipo_articulo = bundle.getString(Constantes.KEY_TIPO_ARTICULO);

        detalleTitulo =(TextView)findViewById(R.id.detalle_titulo);
        detalleFecha =(TextView)findViewById(R.id.detalle_fecha);
        detalleDescripcion =(TextView)findViewById(R.id.detalle_descripcion);
        detalleAutor =(TextView)findViewById(R.id.detalle_autor);
        detalleImagen = (ImageView)findViewById(R.id.detalle_imagen);

        ratingBar =(RatingBar)findViewById(R.id.detalle_rating);

        fab_localizacion = (FloatingActionButton)findViewById(R.id.fab_localizacion);
        peticionDatos();


        fab_localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Detalle_DenunciaActivity.this, UbicacionArticuloActivity.class);
                intent.putExtra(Constantes.KEY_LATITUD,Double.parseDouble(denuncia.getLatitud()));
                intent.putExtra(Constantes.KEY_LONGITUD,Double.parseDouble(denuncia.getLongitud()));
                startActivity(intent);

            }
        });

        //Comentarios
        btn_enviar = (Button)findViewById(R.id.btn_enviar);
        txt_comentario = (EditText)findViewById(R.id.txtComentario);

        listView= (ListView) findViewById(R.id.listView_comentarios_denuncias);
        // Crear adaptador y setear
        adapter = new ComentarioAdapter(this,id_articulo);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //--------------------- COMENTARIOS ------------------

    public void RegistrarComentario(View view){

        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(Detalle_DenunciaActivity.this,"Registrando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.INSERTAR_COMENTARIO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(Detalle_DenunciaActivity.this, s , Toast.LENGTH_LONG).show();
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();

                        //Showing toast
                        Toast.makeText(Detalle_DenunciaActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        finish();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena

                String mensaje =  txt_comentario.getText().toString().trim();
                Map<String,String> params = new Hashtable<String, String>();
                //Agregando de parámetros
                params.put(Constantes.KEY_MENSAJE, mensaje );
                params.put(Constantes.KEY_ID_PERSONA, id_persona);
                params.put(Constantes.KEY_ID_ARTICULO, id_articulo);

                //Parámetros de retorno
                return params;
            }
        };

        //Agregar solicitud a la cola
        VolleySingleton.getInstance(Detalle_DenunciaActivity.this).addToRequestQueue(stringRequest);
        txt_comentario.setText("");
    }

    //--------------------------------------------




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
        ratingBar.setRating(Float.parseFloat(denuncia.getRatingBar()));
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
                            objeto.getString("nom_ape"),
                            objeto.getString("ratingBar"));


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
