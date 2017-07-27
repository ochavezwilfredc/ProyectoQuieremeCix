package com.uss.crist.quieremecix.Controlador.Adopcion;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.uss.crist.quieremecix.Controlador.Denuncia.DenunciaAdapter;
import com.uss.crist.quieremecix.Controlador.Denuncia.DenunciasActivity;
import com.uss.crist.quieremecix.Controlador.Denuncia.Detalle_DenunciaActivity;
import com.uss.crist.quieremecix.Controlador.Denuncia.NuevaDenunciaActivity;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

public class AdopcionActivity extends AppCompatActivity {

    // Atributos
    ListView listView;
    AdopcionAdapter adapter;
    Servicios servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopcion);

        // Obtener instancia de la lista
        listView= (ListView) findViewById(R.id.listView_adopcion);

        Bundle bundle = getIntent().getExtras();
        final String id_persona = bundle.getString(Constantes.KEY_ID);

        // Crear adaptador y setear
        adapter = new AdopcionAdapter(this);
        listView.setAdapter(adapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Intent intent = new Intent(AdopcionActivity.this,Detalle_AdopcionActivity.class);
                intent.putExtra(Constantes.KEY_ID,adapter.obtenerAdopcion(position).getId());
                startActivity(intent);

            }
        });

        listView.setLongClickable(true);
        //You can Use this method
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                // TODO Auto-generated method stub
                alertDelet(adapter.obtenerAdopcion(pos).getId()).show();
                return true;
            }
        });

        //**************************************

        //**** Nueva Adopcion ****
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_nueva_adopcion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdopcionActivity.this,NuevaAdopcionActivity.class);
                intent.putExtra(Constantes.KEY_ID,id_persona);
                startActivity(intent);
            }
        });

    }

    public AlertDialog alertDelet(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdopcionActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_delete)
                .setMessage("¿ Esta seguro de eliminar la denuncia?")
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
                                eliminarAdopcion(id);
                            }
                        });

        return builder.create();
    }


    private void eliminarAdopcion(String id){
        //Mostrar el diálogo de progreso

        final ProgressDialog loading = ProgressDialog.show(this,"Eliminando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constantes.ELIMINAR_ARTICULOID+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        try {
                            if (s.equals(null)){
                                servicios.mensaje(AdopcionActivity.this,"Eliminada Correctamente");
                            }else {
                                servicios.mensaje(AdopcionActivity.this,s);
                            }
                        }catch (Exception e){
                            Log.e("Error ---",e.getMessage().toString());
                        }

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
                        servicios.mensaje(AdopcionActivity.this, volleyError.getMessage().toString());
                        finish();
                    }
                }){

        };

        //Agregar solicitud a la cola
        VolleySingleton.getInstance(AdopcionActivity.this).addToRequestQueue(stringRequest);

    }

}
