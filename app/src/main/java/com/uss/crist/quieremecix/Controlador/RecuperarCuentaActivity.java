package com.uss.crist.quieremecix.Controlador;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.aware.PublishConfig;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.uss.crist.quieremecix.Controlador.Denuncia.Denuncia;
import com.uss.crist.quieremecix.Controlador.Denuncia.Detalle_DenunciaActivity;
import com.uss.crist.quieremecix.Modelo.Persona;
import com.uss.crist.quieremecix.Modelo.Requesthandler;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class RecuperarCuentaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_correo;
    private  Button btn_buscar, btn_cancelar;
    public String id_perb, email_perb, codigo_rec;
    Servicios servicios;

    JsonObjectRequest jsArrayRequest;
    private static final String TAG = "Get persona email";
    Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);

        et_correo = (EditText)findViewById(R.id.et_email_rc);
        btn_buscar = (Button)findViewById(R.id.btn_buscar_rc);
        btn_buscar.setOnClickListener(this);
        btn_cancelar = (Button)findViewById(R.id.btn_cancelar_rc);
        btn_cancelar.setOnClickListener(this);

        servicios = new Servicios();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buscar_rc:
                if (validar()){
                    if(!(et_correo.getText().toString().trim().equals(email_perb))){
                        et_correo.setError("Error");
                        et_correo.requestFocus();
                    }else{
                        enviarCodigo();
                        if (codigo_rec.equals(et_correo.getText().toString().trim())){
                            Intent intent = new Intent(RecuperarCuentaActivity.this, VerificarCodigoActivity.class);
                            intent.putExtra(Constantes.KEY_ID,id_perb);
                            intent.putExtra(Constantes.KEY_EMAIL,email_perb);
                            startActivity(intent);
                        }else{
                            servicios.mensaje(RecuperarCuentaActivity.this,"Código ingresado es incorrecto");
                        }

                    }
                }else{
                    }
                break;
            case R.id.btn_cancelar_rc:
                salir().show();
                break;
        }
    }

    public boolean validar(){
        boolean ok = false;
        final String correo_b = et_correo.getText().toString().trim();
        if (correo_b.length()==0){
            et_correo.setError(getString(R.string.error_field_required));
            et_correo.requestFocus();
        }else{
            if (!servicios.validateEmail(correo_b)){
                et_correo.setError(getString(R.string.error_email));
                et_correo.requestFocus();
            }else{
                    getPersonaEmail();
                    ok=true;
            }
        }
        return ok;
    }




    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecuperarCuentaActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_warning)
                .setMessage("¿ Esta seguro ?")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setPositiveButton("CONFIRMAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        return builder.create();
    }

//******************* get email  *******
public void getPersonaEmail(){
    persona = new Persona();
    final String correo_buscar = et_correo.getText().toString().trim();
    // Nueva petición JSONObject
    jsArrayRequest = new JsonObjectRequest(
            Request.Method.GET,
            Constantes.GET_PER_EMAIL+correo_buscar,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    persona = parseJsonDenuncia(response);
                    id_perb=persona.getId();
                    email_perb=persona.getCorreo();
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
    VolleySingleton.getInstance(RecuperarCuentaActivity.this).addToRequestQueue(jsArrayRequest);
    }

    public Persona parseJsonDenuncia(JSONObject response) {
        Persona per = new Persona();
        JSONArray jsonArray= null;
        try {
            // Obtener el array del objeto
            jsonArray = response.getJSONArray("persona");


            try {
                JSONObject objeto= jsonArray.getJSONObject(0);
                Persona persona = new Persona(
                        objeto.getString("id"),
                        objeto.getString("email"));
                per=persona;

            } catch (JSONException e) {
                Log.e(TAG, "Error de parsing: "+ e.getMessage());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return per;
    }


    //********

    //--------------------------------- Enviar codigo ---------
    private void enviarCodigo(){

        //Mostrar el diálogo de progreso

        final ProgressDialog loading = ProgressDialog.show(this,"Enviando Código...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.SEND_EMAIL_PERSONA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(RecuperarCuentaActivity.this, s , Toast.LENGTH_LONG).show();
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
                        Toast.makeText(RecuperarCuentaActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                codigo_rec =  servicios.codigoAleatorio();

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(Constantes.KEY_ID, id_perb);
                params.put(Constantes.KEY_EMAIL, email_perb);
                params.put(Constantes.KEY_CODIGO, codigo_rec);

                //Parámetros de retorno
                return params;
            }
        };

        //Agregar solicitud a la cola
        VolleySingleton.getInstance(RecuperarCuentaActivity.this).addToRequestQueue(stringRequest);

    }


}
